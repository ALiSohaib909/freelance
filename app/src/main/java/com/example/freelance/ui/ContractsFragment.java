package com.example.freelance.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.freelance.R;
import com.example.freelance.databinding.FragmentContractsBinding;
import com.example.freelance.databinding.FragmentProposalBinding;

public class ContractsFragment extends Fragment {

    private FragmentContractsBinding binding;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contracts, container, false);
            View view = binding.getRoot();
            return view;
        }

    }