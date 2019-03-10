package com.example.issresponsemvvmdemo.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.issresponsemvvmdemo.R;
import com.example.issresponsemvvmdemo.data.Response;

import java.util.ArrayList;
import java.util.List;

public class ISSAdapter extends RecyclerView.Adapter<ISSAdapter.itemViewHolder> {

    private final List<Response> responses =  new ArrayList<>();

    public  void setData(List<Response> newdata){
        responses.clear();
        responses.addAll(newdata);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootview = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.iss_location_list, viewGroup, false);
        return new itemViewHolder(rootview);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull itemViewHolder itemViewHolder, int position) {
        itemViewHolder.tvDuration.setText(responses.get(position).getDuration());
        itemViewHolder.tvRiseTime.setText(responses.get(position).getRisetime());


    }



    @Override
    public int getItemCount() {
        return responses.size();
    }

    static class itemViewHolder extends RecyclerView.ViewHolder{

        TextView tvDuration;
        TextView tvRiseTime;


        public itemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDuration = itemView.findViewById(R.id.tvDuration);
            tvRiseTime = itemView.findViewById(R.id.tvRiseTime);
        }

    }
}
