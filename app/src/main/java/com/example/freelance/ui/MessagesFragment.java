package com.example.freelance.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.freelance.R;
import com.example.freelance.databinding.FragmentJobsBinding;
import com.example.freelance.databinding.FragmentMessagesBinding;

public class MessagesFragment extends Fragment {

    private FragmentMessagesBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_messages, container, false);
        View view = binding.getRoot();
        binding.textDashboard.setText("Your chat rooms will appear here");
        return view;
    }

}