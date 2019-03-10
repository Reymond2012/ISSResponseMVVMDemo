package com.example.issresponsemvvmdemo.repo;

import java.util.Observer;

public interface DataSource {

    void getResultForLocation(String latitude, String longitude);
    void setObserver(Observer observer);

    // void getCoordinatesForLocation(String latitude, String longtitude);
    //    void setObserver(Observer observer);
}
