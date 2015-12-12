package com.codekun.weather.fragments;


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

import com.codekun.weather.ChooseAreaActivity;
import com.codekun.weather.R;
import com.codekun.weather.database.DataLoader;
import com.codekun.weather.models.City;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CityListFragment extends Fragment {

    public static final String TAG = "city";

    public static final String EXTRA_PROVINCE_NAME = "provinceName";
    public static final String EXTRA_PROVINCE_CODE = "provinceCode";

    private static CityListFragment instance;

    /**
     *
     * @param provinceCode 省代码
     * @return
     */
    public static CityListFragment getInstance(String provinceName, String provinceCode){
        if (instance == null){
            instance = new CityListFragment();
        }
        Bundle args = new Bundle();
        args.putString(EXTRA_PROVINCE_NAME, provinceName);
        args.putString(EXTRA_PROVINCE_CODE, provinceCode);
        instance.setArguments(args);
        return instance;
    }


    private String provinceCode = "01";
    private String provinceName = "Unknown";
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private List<City> mCities;
    private List<String> mDataList = new ArrayList<>();


    public CityListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            provinceCode = getArguments().getString(EXTRA_PROVINCE_CODE);
            provinceName = getArguments().getString(EXTRA_PROVINCE_NAME);
        }
        //这样会导致该fragment与ChooseAreaActivity发现联系
        ((ChooseAreaActivity)getActivity()).setTitle(provinceName);
        //CKLog.d("Weather", "省代码：" + provinceCode);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_area_list, container, false);
        mListView = (ListView)v.findViewById(R.id.weather_area_listView);
        mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mDataList);
        mListView.setAdapter(mAdapter);
        DataLoader.queryCities(getActivity(), DataLoader.getServerUrl(provinceCode), new DataLoader.ResultListener(){
            @Override
            public void onCompleteCity(List<City> resultList) {
                super.onCompleteCity(resultList);
                mCities = resultList;
                mDataList.clear();
                for (City p : resultList){
                    mDataList.add(p.getName());
                }
                mAdapter.notifyDataSetChanged();
                mListView.setSelection(0);
            }
        }, provinceCode);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cityName = mCities.get(position).getName();
                String cityCode = mCities.get(position).getCode();
                CountryListFragment fragment = CountryListFragment.getInstance(cityName, cityCode);
                FragmentManager fm = getActivity().getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ViewGroup parentView = (ViewGroup)fm.findFragmentByTag(TAG).getView().getParent();
                ft.replace(parentView.getId(), fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return v;
    }

}
