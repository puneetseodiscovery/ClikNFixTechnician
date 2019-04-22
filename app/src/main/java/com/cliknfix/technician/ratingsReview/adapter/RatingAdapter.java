package com.cliknfix.technician.ratingsReview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cliknfix.technician.R;
import com.cliknfix.technician.ratingsReview.model.BeanRating;
import com.cliknfix.technician.util.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.viewHolder> {

    Context context;
    ArrayList<BeanRating> list = new ArrayList<>();

    public RatingAdapter(Context context, ArrayList<BeanRating> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_rating,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, final int position) {
        //holder.ivTechImg.setImageResource(list.get(position).getUserImg());
        holder.tvTechText.setText(list.get(position).getTechName());
        holder.tvRatingText.setText(list.get(position).getText());
        holder.ratingBar.setRating(list.get(position).getRating());
        /*holder.llPastJobsItam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "" +list.get(position).getStatus(), Toast.LENGTH_SHORT).show();
            }
        });*/
    }

   /* public void loadFragment(String category) {
        FragmentTransaction transaction = ((HomeScreenActivity) context).getSupportFragmentManager().beginTransaction();
        AcceptRejectJobFragment fragment = new AcceptRejectJobFragment();
        Bundle args = new Bundle();
        args.putString("category", category);
        fragment.setArguments(args);
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }*/

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        /*@BindView(R.id.iv_tech_img)
        ImageView ivTechImg;*/
        @BindView(R.id.tv_tech_text)
        TextView tvTechText;
        @BindView(R.id.rating)
        RatingBar ratingBar;
        @BindView(R.id.tv_rating_text)
        TextView tvRatingText;
        public viewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            init();
        }

        public void init(){
            tvTechText.setTypeface(Utility.typeFaceForText(context));
            tvRatingText.setTypeface(Utility.typeFaceForText(context));
        }
    }

}
