package com.example.freelance.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.freelance.Activities.UserAccountActivity;
import com.example.freelance.R;
import com.example.freelance.databinding.FragmentProposalBinding;

public class ProposalFragment extends Fragment {

    FragmentProposalBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_proposal, container, false);
        View view = binding.getRoot();

        binding.ivAccount.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), UserAccountActivity.class);
            startActivity(intent);
        });

        return view;
    }

}