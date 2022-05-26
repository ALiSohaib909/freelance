package com.example.freelance.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.freelance.R;
import com.example.freelance.databinding.FragmentAlertBinding;
import com.example.freelance.databinding.FragmentContractsBinding;

public class AlertFragment extends Fragment {

    private FragmentAlertBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_alert, container, false);
        View view = binding.getRoot();
        binding.textAlert.setText("You have no alerts at this time");
        return view;
    }

}