package com.nycschools.demo.viewmodel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.nycschools.demo.service.model.NYCSchoolListDetails;
import com.nycschools.demo.service.repository.NYCSchoolRepository;

import java.util.List;

public class NYCSchoolDetailsViewModel extends AndroidViewModel {

    private final LiveData<List<NYCSchoolListDetails>> nYCSchoolListDetailsObservable;
    final MutableLiveData<NYCSchoolListDetails> nYCSchoolDetails = new MutableLiveData<>();

    public NYCSchoolDetailsViewModel(@NonNull Application application) {
        super(application);

        nYCSchoolListDetailsObservable = NYCSchoolRepository.getInstance().getSchoolListDetails();
    }

    public LiveData<List<NYCSchoolListDetails>> getNYCSchoolListDetailsObservable() {
        return nYCSchoolListDetailsObservable;
    }

    public MutableLiveData<NYCSchoolListDetails> getNYCSchoolDetails() {
        return nYCSchoolDetails;
    }

    public void getNYCSchoolListDetails(Context context, String strSchoolName){

        getNYCSchoolListDetailsObservable().observe((LifecycleOwner) context, new Observer<>() {
            @Override
            public void onChanged(List<NYCSchoolListDetails> nycSchoolListDetails) {

                for (NYCSchoolListDetails details : nycSchoolListDetails) {

                    if (details.getSchoolName() != null && details.getSchoolName().equalsIgnoreCase(strSchoolName)) {
                        nYCSchoolDetails.setValue(details);
                        break;
                    }
                }
            }
        });

    }
}