package com.nycschools.demo.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nycschools.demo.NYCSchoolListData;
import com.nycschools.demo.databinding.ActivityMainBinding;
import com.nycschools.demo.view.adapter.NYCSchoolListAdapter;
import com.nycschools.demo.viewmodel.NYCSchoolViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

         activityMainBinding.prLoadingContent.setVisibility(View.VISIBLE);
        setNYCSchoolListObserver();

    }


    private void setNYCSchoolListObserver() {

        NYCSchoolViewModel nycSchoolViewModel = new ViewModelProvider(this).get(NYCSchoolViewModel.class);
        nycSchoolViewModel.getNYCSchoolListDataObservable().observe(this, new Observer<>() {
            @Override
            public void onChanged(List<NYCSchoolListData> nycSchoolListData) {

                activityMainBinding.prLoadingContent.setVisibility(View.GONE);
                updateAdapter(nycSchoolListData);
            }
        });
    }

    private void updateAdapter(List<NYCSchoolListData> nycSchoolListData){

        if (nycSchoolListData == null){
            activityMainBinding.tvNoSchoolsFound.setVisibility(View.VISIBLE);
        }else {
            activityMainBinding.tvNoSchoolsFound.setVisibility(View.GONE);
            activityMainBinding.rvSchoolList.setLayoutManager(new LinearLayoutManager(this));
            activityMainBinding.tvNoSchoolsFound.setVisibility(View.GONE);
            NYCSchoolListAdapter nycSchoolListAdapter = new NYCSchoolListAdapter(this, nycSchoolListData);
            activityMainBinding.rvSchoolList.setAdapter(nycSchoolListAdapter);

        }

    }
}