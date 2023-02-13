package com.nycschools.demo.view.adapter;


import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nycschools.demo.NYCSchoolListData;
import com.nycschools.demo.R;
import com.nycschools.demo.databinding.AdapterItemViewBinding;
import com.nycschools.demo.utils.Utils;
import com.nycschools.demo.view.ui.NYCSchoolDetails;

import java.util.List;

public class NYCSchoolListAdapter extends RecyclerView.Adapter<NYCSchoolListAdapter.NYCSchoolListViewHolder> {

    List<NYCSchoolListData> nYCSchoolListData;
    private Context mContext;

    public NYCSchoolListAdapter(Context context, List<NYCSchoolListData> nYCSchoolListData){
        this.nYCSchoolListData = nYCSchoolListData;
        this.mContext = context;
    }

    @NonNull
    @Override
    public NYCSchoolListAdapter.NYCSchoolListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        AdapterItemViewBinding itemBinding = AdapterItemViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NYCSchoolListViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NYCSchoolListAdapter.NYCSchoolListViewHolder holder, int position) {

        NYCSchoolListData data = nYCSchoolListData.get(position);
        holder.itemBinding.tvSchoolName.setText(data.getSchoolName());
        holder.itemBinding.tvSchoolEmail.setText(
                Utils.getSpannableString(mContext.getString(R.string.txt_school_email), data.getSchoolEmail()));

        holder.itemBinding.getRoot().setOnClickListener(v -> {

            Intent intent = new Intent(mContext, NYCSchoolDetails.class);
            intent.putExtra("SCHOOL_NAME", data.getSchoolName());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return nYCSchoolListData.size();
    }

    public static class NYCSchoolListViewHolder extends RecyclerView.ViewHolder{

        AdapterItemViewBinding itemBinding;
        public NYCSchoolListViewHolder(@NonNull AdapterItemViewBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

    }
}
