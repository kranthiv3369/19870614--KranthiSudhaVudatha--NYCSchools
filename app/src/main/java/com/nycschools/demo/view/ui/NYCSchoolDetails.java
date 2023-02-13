package com.nycschools.demo.view.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.nycschools.demo.R;
import com.nycschools.demo.databinding.ActivityNycschoolDetailsBinding;
import com.nycschools.demo.service.model.NYCSchoolListDetails;
import com.nycschools.demo.utils.Utils;
import com.nycschools.demo.viewmodel.NYCSchoolDetailsViewModel;
import java.util.Objects;

public class NYCSchoolDetails extends AppCompatActivity {

    ActivityNycschoolDetailsBinding nycSchoolDetailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nycSchoolDetailsBinding = ActivityNycschoolDetailsBinding.inflate(getLayoutInflater());
        setContentView(nycSchoolDetailsBinding.getRoot());
        setSupportActionBar(nycSchoolDetailsBinding.toolBar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        nycSchoolDetailsBinding.prLoadingContent.setVisibility(View.VISIBLE);
        setNYCSchoolListDetailsObserver();
    }

     private void setNYCSchoolListDetailsObserver() {
         NYCSchoolDetailsViewModel nycSchoolDetailsViewModel = new ViewModelProvider(this).get(NYCSchoolDetailsViewModel.class);
         nycSchoolDetailsViewModel.getNYCSchoolListDetails(this, getIntent().getStringExtra("SCHOOL_NAME"));
         nycSchoolDetailsViewModel.getNYCSchoolDetails().observe(this, this::updateSchoolDetailsUI);
    }

    private void updateSchoolDetailsUI(NYCSchoolListDetails nycSchoolListDetails){

        nycSchoolDetailsBinding.prLoadingContent.setVisibility(View.GONE);
        if (nycSchoolListDetails == null){
            nycSchoolDetailsBinding.tvNoSchoolDetailsFound.setVisibility(View.VISIBLE);
            nycSchoolDetailsBinding.cardView.setVisibility(View.GONE);

        }else {

            nycSchoolDetailsBinding.tvNoSchoolDetailsFound.setVisibility(View.GONE);
            nycSchoolDetailsBinding.cardView.setVisibility(View.VISIBLE);
            nycSchoolDetailsBinding.tvSchoolName.setText(Utils.getSpannableString(getString(R.string.txt_school_name_details)
                    , nycSchoolListDetails.getSchoolName()));
            nycSchoolDetailsBinding.tvNumSATTestTaker.setText(Utils.getSpannableString(
                    getString(R.string.txt_num_sat_takers), nycSchoolListDetails.getNumOfSatTestTakers()));
            nycSchoolDetailsBinding.tvSATMathAvgScore.setText(Utils.getSpannableString(
                    getString(R.string.txt_sat_math_avg_score), nycSchoolListDetails.getSatMathAvgScore()));
            nycSchoolDetailsBinding.tvSATReadingAvgScore.setText(Utils.getSpannableString(
                    getString(R.string.txt_sat_reading_avg_score), nycSchoolListDetails.getSatCriticalReadingAvgScore()));
            nycSchoolDetailsBinding.tvSATWritingAvgScore.setText(Utils.getSpannableString(
                    getString(R.string.txt_sat_writing_avg_score), nycSchoolListDetails.getSatWritingAvgScore()));
        }

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return false;
    }
}