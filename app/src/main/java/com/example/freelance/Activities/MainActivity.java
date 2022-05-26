package com.example.freelance.Activities;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.freelance.R;
import com.example.freelance.databinding.ActivityMainBinding;
import com.example.freelance.ui.AlertFragment;
import com.example.freelance.ui.ContractsFragment;
import com.example.freelance.ui.JobsFragment;
import com.example.freelance.ui.MessagesFragment;
import com.example.freelance.ui.ProposalFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;

    JobsFragment jobFragment;
    ProposalFragment proposalFragment;
    ContractsFragment contractsFragment;
    MessagesFragment messagesFragment;
    AlertFragment alertFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        jobFragment = new JobsFragment();
        proposalFragment = new ProposalFragment();
        contractsFragment = new ContractsFragment();
        messagesFragment = new MessagesFragment();
        alertFragment = new AlertFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.nav_host_fragment_activity_main, jobFragment);
        ft.add(R.id.nav_host_fragment_activity_main, proposalFragment);
        ft.add(R.id.nav_host_fragment_activity_main, contractsFragment);
        ft.add(R.id.nav_host_fragment_activity_main, messagesFragment);
        ft.add(R.id.nav_host_fragment_activity_main, alertFragment);
        ft.commit();
        replaceFragment(jobFragment);

        navView.setOnNavigationItemSelectedListener(MainActivity.this);
        navView.setSelectedItemId(R.id.nav_host_fragment_activity_main);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.hide(jobFragment);
        ft.hide(proposalFragment);
        ft.hide(contractsFragment);
        ft.hide(messagesFragment);
        ft.hide(alertFragment);

        ft.show(fragment);
        ft.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_jobs:
                replaceFragment(jobFragment);
                return true;

            case R.id.navigation_proposals:
                replaceFragment(proposalFragment);
                return true;

            case R.id.navigation_contracts:
                replaceFragment(contractsFragment);
                return true;

            case R.id.navigation_messages:
                replaceFragment(messagesFragment);
                return true;

            case R.id.navigation_alerts:
                replaceFragment(alertFragment);
                return true;
        }

        return false;
    }
}