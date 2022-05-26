package com.example.freelance.ui;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.freelance.Activities.UserLoginActivity;
import com.example.freelance.Adapter.Jobs;
import com.example.freelance.Adapter.JobsAdapter;
import com.example.freelance.R;
import com.example.freelance.databinding.FragmentJobsBinding;
import com.example.freelance.db.DatabaseHelper;

import java.io.IOException;
import java.util.ArrayList;

import kotlinx.coroutines.Job;

public class JobsFragment extends Fragment {

    private FragmentJobsBinding binding;
    Jobs jobs;
    ArrayList<Jobs> arrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_jobs, container, false);
        View view = binding.getRoot();
        DatabaseHelper db = new DatabaseHelper(getActivity());
        try {
            db.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            db.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        Cursor cursor = db.get("tbl_jobs", null,
                null, null,
                null, null,
                null);
        cursor.moveToFirst();
        while (cursor.moveToNext()) {

            jobs = new Jobs(cursor.getString(1),
                            cursor.getString(2));
            arrayList.add(jobs);
        }
        JobsAdapter adapter = new JobsAdapter(arrayList);
        binding.rvJobs.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvJobs.setAdapter(adapter);
        return view;
    }

}