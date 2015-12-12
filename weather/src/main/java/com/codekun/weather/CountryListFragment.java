package com.codekun.weather;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codekun.weather.database.DataLoader;
import com.codekun.weather.models.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CountryListFragment extends Fragment {

    public static final String EXTRA_CITY_NAME = "cityName";
    public static final String EXTRA_CITY_CODE = "cityCode";

    private static CountryListFragment instance;
    public static CountryListFragment getInstance(String cityName, String cityCode){
        if (instance == null){
            instance = new CountryListFragment();
        }
        Bundle args = new Bundle();
        args.putString(EXTRA_CITY_NAME, cityName);
        args.putString(EXTRA_CITY_CODE, cityCode);
        instance.setArguments(args);
        return instance;
    }


    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private List<Country> mCountries;
    private List<String> mDataList = new ArrayList<>();
    private String mCityCode;
    private String mCityName = "Unknown";

    public CountryListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            mCityName = getArguments().getString(EXTRA_CITY_NAME);
            mCityCode = getArguments().getString(EXTRA_CITY_CODE);
        }
        ((ChooseAreaActivity)getActivity()).setTitle(mCityName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_area_list, container, false);
        mListView = (ListView)v.findViewById(R.id.weather_area_listView);
        mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mDataList);
        mListView.setAdapter(mAdapter);
        DataLoader.queryCountries(getActivity(), DataLoader.getServerUrl(mCityCode), new DataLoader.ResultListener(){
            @Override
            public void onCompleteCountry(List<Country> resultList) {
                super.onCompleteCountry(resultList);
                mCountries = resultList;
                mDataList.clear();
                for (Country p : mCountries){
                    mDataList.add(p.getName());
                }
                mAdapter.notifyDataSetChanged();
                mListView.setSelection(0);
            }
        }, mCityCode);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String countryName = mCountries.get(position).getName();
                String countryCode = mCountries.get(position).getCode();
                Intent intent = new Intent();
                intent.putExtra("name", countryName);
                intent.putExtra("code", countryCode);
                getActivity().setResult(getActivity().RESULT_OK, intent);
                getActivity().finish();
            }
        });
        return v;
    }

}
