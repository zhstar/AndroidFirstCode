package com.codekun.weather;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.VideoView;

import com.codekun.weather.database.DataLoader;
import com.codekun.weather.models.Province;
import com.codekun.common.utils.CKLog;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProvinceListFragment extends Fragment {

    public static final String TAG = "province";

    private static ProvinceListFragment instance;
    public static ProvinceListFragment getInstance(){
        if (instance == null){
            instance = new ProvinceListFragment();
        }
        return instance;
    }


    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private List<Province> mProvinces;
    private List<String> mDataList = new ArrayList<>();

    public ProvinceListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_area_list, container, false);
        //填充中国所有省级名称
        mListView = (ListView)v.findViewById(R.id.weather_area_listView);
        mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mDataList);
        mListView.setAdapter(mAdapter);
        //加载数据
        DataLoader.queryProvinces(getActivity(), DataLoader.getServerUrl(), new DataLoader.ResultListener(){
            @Override
            public void onCompleteProvince(List<Province> resultList) {
                super.onCompleteProvince(resultList);
                mProvinces = resultList;
                mDataList.clear();
                for (Province p : resultList){
                    mDataList.add(p.getName());
                }
                mAdapter.notifyDataSetChanged();
                mListView.setSelection(0);
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String provinceName = mProvinces.get(position).getName();
                String provinceCode = mProvinces.get(position).getCode();
                FragmentManager fm = getActivity().getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                CityListFragment fragment = CityListFragment.getInstance(provinceName, provinceCode);
                //获取当前fragment所在的容器
                ViewGroup parentView = (ViewGroup) fm.findFragmentByTag(TAG).getView().getParent();
                ft.replace(parentView.getId(), fragment, CityListFragment.TAG);
                ft.addToBackStack(null);
                ft.commit();
                CKLog.d("Weather", "省:"+mProvinces.get(position).getName()+", 代码：" + provinceCode);
            }
        });
        return v;
    }

}
