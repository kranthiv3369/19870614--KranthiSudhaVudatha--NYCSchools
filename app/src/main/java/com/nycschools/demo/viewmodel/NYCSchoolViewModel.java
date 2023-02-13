package com.nycschools.demo.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nycschools.demo.NYCSchoolListData;
import com.nycschools.demo.service.model.NYCSchoolListDetails;
import com.nycschools.demo.service.repository.NYCSchoolRepository;

import java.util.List;

public class NYCSchoolViewModel extends AndroidViewModel {

    private final LiveData<List<NYCSchoolListData>> nYCSchoolListDataObservable;
    private final LiveData<List<NYCSchoolListDetails>> nYCSchoolListDetailsObservable;

    public NYCSchoolViewModel(@NonNull Application application) {
        super(application);

        nYCSchoolListDataObservable = NYCSchoolRepository.getInstance().getSchoolList();
        nYCSchoolListDetailsObservable = NYCSchoolRepository.getInstance().getSchoolListDetails();
    }

    public LiveData<List<NYCSchoolListData>> getNYCSchoolListDataObservable() {
        return nYCSchoolListDataObservable;
    }

    public LiveData<List<NYCSchoolListDetails>> getNYCSchoolListDetailsObservable() {
        return nYCSchoolListDetailsObservable;
    }
}
