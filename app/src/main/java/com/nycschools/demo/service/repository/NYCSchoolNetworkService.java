package com.nycschools.demo.service.repository;

import com.nycschools.demo.NYCSchoolListData;
import com.nycschools.demo.service.model.NYCSchoolListDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NYCSchoolNetworkService {
    String BASE_URL = "https://data.cityofnewyork.us/resource/";

    @GET("s3k6-pzi2.json")
    Call<List<NYCSchoolListData>> getSchoolList();

    @GET("f9bf-2cp4.json")
    Call<List<NYCSchoolListDetails>> getSchoolListDetails();
}
