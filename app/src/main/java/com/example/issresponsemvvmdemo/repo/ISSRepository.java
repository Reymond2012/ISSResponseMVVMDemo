package com.example.issresponsemvvmdemo.repo;

import java.util.Observable;
import java.util.Observer;

public class ISSRepository  extends Observable implements Observer, DataSource{

    private final DataSource localDataSource;
    private final DataSource remoteDataSource;

    public ISSRepository(DataSource localDataSource, DataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getResultForLocation(String latitude, String longitude) {
        remoteDataSource.setObserver(this);
        remoteDataSource.getResultForLocation(longitude, latitude);

    }

    @Override
    public void setObserver(Observer observer) {
        addObserver(observer);

    }

    @Override
    public void update(Observable observable, Object result) {
        setChanged();
        notifyObservers(result);

    }
}
