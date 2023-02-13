package com.nycschools.demo.service.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nycschools.demo.NYCSchoolListData;
import com.nycschools.demo.service.model.NYCSchoolListDetails;
import com.nycschools.demo.utils.SSLCertificateValidate;

import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.CertificateException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NYCSchoolRepository {

    private final NYCSchoolNetworkService nycSchoolNetworkService;
    private static NYCSchoolRepository nycSchoolRepository;

    private NYCSchoolRepository() {

        OkHttpClient okHttpClient = SSLCertificateValidate.getUnsafeOkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NYCSchoolNetworkService.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        nycSchoolNetworkService = retrofit.create(NYCSchoolNetworkService.class);
    }

    public synchronized static NYCSchoolRepository getInstance() {

        if (nycSchoolRepository == null) {
            nycSchoolRepository = new NYCSchoolRepository();
        }
        return nycSchoolRepository;
    }

    public LiveData<List<NYCSchoolListData>> getSchoolList() {

        final MutableLiveData<List<NYCSchoolListData>> data = new MutableLiveData<>();

        nycSchoolNetworkService.getSchoolList().enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<List<NYCSchoolListData>> call,
                                   @NonNull Response<List<NYCSchoolListData>> response) {

                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<NYCSchoolListData>> call, @NonNull Throwable t) {

                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<List<NYCSchoolListDetails>> getSchoolListDetails() {

        final MutableLiveData<List<NYCSchoolListDetails>> data = new MutableLiveData<>();

        nycSchoolNetworkService.getSchoolListDetails().enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<List<NYCSchoolListDetails>> call,
                                   @NonNull Response<List<NYCSchoolListDetails>> response) {

                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<NYCSchoolListDetails>> call, @NonNull Throwable t) {

                data.setValue(null);
            }
        });

        return data;
    }

}
