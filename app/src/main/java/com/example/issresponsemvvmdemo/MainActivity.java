package com.example.issresponsemvvmdemo;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.issresponsemvvmdemo.data.Response;
import com.example.issresponsemvvmdemo.home.HomeViewModel;
import com.example.issresponsemvvmdemo.home.ISSAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etLatitude;
    EditText etLongitude;
    Button btGetData;

    ISSAdapter issAdapter = new ISSAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLatitude = findViewById(R.id.etLatitude);
        etLongitude = findViewById(R.id.etLongitude);
        btGetData = findViewById(R.id.btGetData);

        RecyclerView recyclerView = findViewById(R.id.rvData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration divider = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(divider);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(issAdapter);

        final HomeViewModel homeViewModel = new HomeViewModel();
        homeViewModel.getResponseLiveData().observe(this, new Observer<List<Response>>() {
            @Override
            public void onChanged(@Nullable List<Response> responses) {
                issAdapter.setData(responses);
            }
        });

        btGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeViewModel.getResponses(etLatitude.getText().toString(),etLongitude.getText().toString());
            }
        });


    }
}
