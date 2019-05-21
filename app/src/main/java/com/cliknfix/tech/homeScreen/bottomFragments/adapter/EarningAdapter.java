package com.cliknfix.tech.homeScreen.bottomFragments.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cliknfix.tech.R;
import com.cliknfix.tech.customerProfile.PastCustomerProfileFragment;
import com.cliknfix.tech.homeScreen.HomeScreenActivity;
import com.cliknfix.tech.homeScreen.bottomFragments.model.BeanEarnings;
import com.cliknfix.tech.util.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EarningAdapter extends RecyclerView.Adapter<EarningAdapter.viewHolder> {

    Context context;
    ArrayList<BeanEarnings> list = new ArrayList<>();

    public EarningAdapter(Context context, ArrayList<BeanEarnings> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_earnings,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, final int position) {
        //holder.ivUserImg.setImageResource(list.get(position).getUserImg());
        holder.tvStatus.setText(list.get(position).getStatus());
        holder.tvDate.setText(list.get(position).getDate());
        holder.tvCategory.setText(list.get(position).getCategory());
        holder.tvEarning.setText(list.get(position).getEarning());
        holder.llEarningItam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = ((HomeScreenActivity) context).getSupportFragmentManager().beginTransaction();
                PastCustomerProfileFragment fragment = new PastCustomerProfileFragment();
                transaction.replace(R.id.frame_container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ll_earning_item)
        LinearLayout llEarningItam;
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
        @BindView(R.id.tv_earning)
        TextView tvEarning;

        public viewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            init();
        }

        public void init(){
            tvUserText.setTypeface(Utility.typeFaceForText(context));
            tvStatusText.setTypeface(Utility.typeFaceForText(context));
        }
    }

}
