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

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.codekun.common.core.TitleBarActivity;
import com.codekun.common.utils.CKLog;

public class WeatherActivity extends TitleBarActivity {

    private WeatherInfoFragment mWeatherInfoFragment;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_weather;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getTitleBar().setLeftBtnVisibility(View.GONE);
        //getTitleBar().setRightBtnVisibility(View.GONE);
        setTitle("中国天气");

        mWeatherInfoFragment = new WeatherInfoFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.weather_info_fragment_layout, mWeatherInfoFragment);
        //ft.addToBackStack(null);
        ft.commit();

        getTitleBar().setRightBtnOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeatherActivity.this, ChooseAreaActivity.class);
               // startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == RESULT_OK){
                if (data != null){
                    String countryName = data.getStringExtra("name");
                    String countryCode = data.getStringExtra("code");
                    mWeatherInfoFragment.update(countryName, countryCode);
                }
            }
        }
    }
}
