package com.suresh.androidapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.suresh.androidapp.R;
import com.suresh.androidapp.asyncTasks.FlowerAsyncWebService;
import com.suresh.androidapp.serviceManagers.NetworkServiceManager;


public class FlowerListFragment extends Fragment {

    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Flowers");

        View view = inflater.inflate(R.layout.fragment_flower_list, container, false);

        progressBar = (ProgressBar) getActivity().findViewById(R.id.async_progress);

        if (!new NetworkServiceManager(getContext()).isNetworkAvailable()) {
            Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
        } else {
            FlowerAsyncWebService flowerAsyncWebService = new FlowerAsyncWebService(view, getContext() , progressBar);
            flowerAsyncWebService.execute();
        }

        return view;

    }





}
