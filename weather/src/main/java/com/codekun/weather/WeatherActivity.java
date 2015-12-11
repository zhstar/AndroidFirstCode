/*
http://www.weather.com.cn/data/list3/city.xml
服务器会返回我们一段文本信息，其中包含了中国所有的省份名称以及省级代号，如下所示：
01|北京,02|上海,03|天津,04|重庆,05|黑龙江,06|吉林,07|辽宁,08|内蒙古,09|河北,10|山西,11|陕西,12|山东,
13|新疆,14|西藏,15|青海,16|甘肃,17|宁夏,18|河南,19|江苏,20|湖北,21|浙江,22|安徽,23|福建,24|江西,
25|湖南,26|贵州,27|四川,28|广东,29|云南,30|广西,31|海南,32|香港,33|澳门,34|台湾

http://www.weather.com.cn/data/list3/city19.xml
服务器返回江苏的数据如下：
1901|南京,1902|无锡,1903|镇江,1904|苏州,1905|南通,1906|扬州,1907|盐城,1908|徐州,1909|淮安,1910|连云港,1911|常州,1912|泰州,1913|宿迁

http://www.weather.com.cn/data/list3/city1904.xml
服务器返回苏州的数据如下：
190401|苏州,190402|常熟,190403|张家港,190404|昆山,190405|吴县东山,190406|吴县,190407|吴江,190408|太仓

http://www.weather.com.cn/data/list3/city190404.xml
服务器返回昆山的数据非常简短：
190404|101190404
后半部分的101190404就是昆山所对应的天气代号

http://www.weather.com.cn/data/cityinfo/101190404.html
服务器就会把昆山当前的天气信息以JSON格式返回给我们了，如下所示：
{"weatherinfo":
{"city":"昆山","cityid":"101190404","temp1":"21℃","temp2":"9℃", "weather":"多云转小雨","img1":"d1.gif","img2":"n7.gif","ptime":"11:00"}
}
其中city表示城市名，cityid表示城市对应的天气代号，temp1和temp2表示气温是几度到几度，weather表示今日天气信息的描述，
img1和 img2表示今日天气对应的图片，ptime表示天气发布的时间
 */
package com.codekun.weather;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.codekun.weather.database.DataLoader;
import com.codekun.weather.database.DatabaseManager;
import com.codekun.weather.models.City;
import com.codekun.weather.models.Country;
import com.codekun.weather.models.Province;
import com.codekun.weather.utils.CKLog;

import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity {

    private static final int LEVEL_PROVINCE = 0;
    private static final int LEVEL_CITY = 1;
    private static final int LEVEL_COUNTRY = 2;

    private int currentLevel = LEVEL_PROVINCE;

    private ListView areaListView;
    private List<String> dataList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    private List<Province> provinces = null;
    private List<City> cities = null;
    private List<Country> countries = null;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        context = this;
        areaListView = (ListView)findViewById(R.id.weather_area_listView);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
        areaListView.setAdapter(adapter);
        areaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (currentLevel == LEVEL_PROVINCE){
                    Province p = provinces.get(position);
                    queryCities(p.getCode());
                    currentLevel = LEVEL_CITY;
                }else if (currentLevel == LEVEL_CITY){
                    City c = cities.get(position);
                    queryCountries(c.getCode());
                    currentLevel = LEVEL_COUNTRY;
                }else if (currentLevel == LEVEL_COUNTRY){
                    //Toast.makeText(context, "开始加载 " + countries.get(position).getName() + " 天气数据", Toast.LENGTH_SHORT).show();
                    queryWeatherInfo(countries.get(position).getCode());
                }
            }
        });

        queryProvinces();


    }

    private void queryProvinces(){
        DataLoader.queryProvinces(this, DataLoader.getServerUrl(), new DataLoader.ResultListener(){
            @Override
            public void onCompleteProvince(List<Province> resultList) {
                provinces = resultList;
                List<String> list = new ArrayList<String>();
                dataList.clear();
                for (Province p : resultList){
                    dataList.add(p.getName());
                }
                adapter.notifyDataSetChanged();
                areaListView.setSelection(0);
            }
        });
    }

    private void queryCities(String provinceCode){
        DataLoader.queryCities(this, DataLoader.getServerUrl(provinceCode), new DataLoader.ResultListener(){
            @Override
            public void onCompleteCity(List<City> resultList) {
                cities = resultList;
                List<String> list = new ArrayList<String>();
                dataList.clear();
                for (City p : resultList){
                    dataList.add(p.getName());
                }
                adapter.notifyDataSetChanged();
                areaListView.setSelection(0);
            }
        }, provinceCode);
    }

    private void queryCountries(String cityCode){
        DataLoader.queryCountries(this, DataLoader.getServerUrl(cityCode), new DataLoader.ResultListener(){
            @Override
            public void onCompleteCountry(List<Country> resultList) {
                countries = resultList;
                List<String> list = new ArrayList<String>();
                dataList.clear();
                for (Country p : resultList){
                    dataList.add(p.getName());
                }
                adapter.notifyDataSetChanged();
                areaListView.setSelection(0);
            }
        }, cityCode);
    }

    private void queryWeatherInfo(String countryCode){
        DataLoader.queryWeatherInfo(this, countryCode, new DataLoader.ResultListener(){
            @Override
            public void onComplete(Object obj) {
                Toast.makeText(context, "天气信息:" + (String)obj, Toast.LENGTH_SHORT).show();
                CKLog.d("Weather", (String)obj);
            }
        });
    }



}
