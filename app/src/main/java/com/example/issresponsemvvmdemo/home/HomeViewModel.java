package com.example.issresponsemvvmdemo.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.issresponsemvvmdemo.data.Response;
import com.example.issresponsemvvmdemo.repo.DataSource;
import com.example.issresponsemvvmdemo.repo.ISSRepository;
import com.example.issresponsemvvmdemo.repo.LocalDataSource;
import com.example.issresponsemvvmdemo.repo.RemoteDataSource;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class HomeViewModel implements Observer {

    private final DataSource issRepository;

    private final MutableLiveData<List<Response>> responseLiveData = new MutableLiveData<>();



    public HomeViewModel (){
        issRepository = new ISSRepository(new LocalDataSource(), new RemoteDataSource());
    }

    public LiveData<List<Response>> getResponseLiveData(){
        return responseLiveData;
    }

    public void getResponses(String latitude, String longtitude){
        issRepository.setObserver(this);
        issRepository.getResultForLocation(latitude,longtitude);
    }




    @Override
    public void update(Observable observable, Object result) {
        List<Response> responses = (List<Response>) result;
        responseLiveData.setValue(responses);

    }
}
