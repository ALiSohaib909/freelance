package com.example.freelance.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelance.R;

import java.util.ArrayList;

public class JobsAdapter  extends RecyclerView.Adapter<JobsAdapter.ViewHolder> {
    ArrayList<Jobs> jobs;

    public JobsAdapter(ArrayList<Jobs> jobHistory) {
        this.jobs = jobHistory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.job_item, parent, false);
        ViewHolder viewHolder = new JobsAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Jobs job = jobs.get(position);
        holder.tvTitle.setText(job.getTitle());
        holder.tvDesc.setText(job.getDesc());

    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);

        }
    }
}

