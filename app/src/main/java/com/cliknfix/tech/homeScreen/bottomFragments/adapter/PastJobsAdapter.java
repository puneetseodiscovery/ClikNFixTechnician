package com.cliknfix.tech.homeScreen.bottomFragments.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cliknfix.tech.R;
import com.cliknfix.tech.customerProfile.PastCustomerProfileFragment;
import com.cliknfix.tech.homeScreen.HomeScreenActivity;
import com.cliknfix.tech.homeScreen.bottomFragments.model.BeanPastJobs;
import com.cliknfix.tech.responseModels.PastJobsResponseModel;
import com.cliknfix.tech.util.Utility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PastJobsAdapter extends RecyclerView.Adapter<PastJobsAdapter.viewHolder> {

    Context context;
    List<PastJobsResponseModel.Datum> list;
    //ArrayList<BeanPastJobs> list;

    public PastJobsAdapter(Context context, List<PastJobsResponseModel.Datum> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_past_jobs,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, final int position) {
        //holder.ivUserImg.setImageResource(list.get(position).getUserImg());
        holder.tvUserText.setText(list.get(position).getName());
        holder.tvDate.setText(list.get(position).getCreatedAt());
        holder.tvCategory.setText(list.get(position).getCategory());
        holder.btnMoreDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loadFragment(list.get(position).getCategory());
                Log.e("userId","" + list.get(position).getId());
                //((HomeScreenActivity)context).fetchUserData(list.get(position).getId());
                ((HomeScreenActivity)context).fetchPastUserData(list.get(position).getId(),
                        list.get(position).getCategory(),
                        list.get(position).getCreatedAt(),
                        list.get(position).getServicePrice());
            }
        });

        /*holder.btnMoreDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(list.get(position).getCategory());
            }
        });*/
    }

    public void loadFragment(String category) {
        FragmentTransaction transaction = ((HomeScreenActivity) context).getSupportFragmentManager().beginTransaction();
        PastCustomerProfileFragment fragment = new PastCustomerProfileFragment();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ll_past_jobs_view_item)
        LinearLayout llPastJobsItam;
        /*@BindView(R.id.iv_user_img)
        ImageView ivUserImg;*/
        @BindView(R.id.tv_status_text)
        TextView tvStatusText;
        @BindView(R.id.tv_user_text)
        TextView tvUserText;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_category)
        TextView tvCategory;
        @BindView(R.id.btn_more_details)
        Button btnMoreDetails;
        public viewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            init();
        }

        public void init(){
            tvUserText.setTypeface(Utility.typeFaceForText(context));
            tvStatusText.setTypeface(Utility.typeFaceForText(context));
            btnMoreDetails.setTypeface(Utility.typeFaceForText(context));
        }
    }

}
