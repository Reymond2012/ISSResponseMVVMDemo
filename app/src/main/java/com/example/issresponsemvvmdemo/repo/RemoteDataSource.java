package com.example.issresponsemvvmdemo.repo;

import com.example.issresponsemvvmdemo.data.ISSRepo;
import com.example.issresponsemvvmdemo.net.Constants;
import com.example.issresponsemvvmdemo.net.ISSService;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource extends Observable implements DataSource {

    private final ISSService issservice;

    public RemoteDataSource(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().
                        setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        issservice = retrofit.create(ISSService.class);
    }

    @Override
    public void getResultForLocation(String latitude, String longitude) {

        final List<com.example.issresponsemvvmdemo.data.Response> list = new ArrayList<>();
        issservice.getResult(latitude, longitude).enqueue(new Callback<ISSRepo>() {
            @Override
            public void onResponse(Call<ISSRepo> call, Response<ISSRepo> response) {
                if(response.isSuccessful()&& response.body().getResponse() != null){
                    list.clear();
                    list.addAll(response.body().getResponse());
                    setChanged();
                    notifyObservers(list);
                }

            }

            @Override
            public void onFailure(Call<ISSRepo> call, Throwable throwable) {
                throwable.printStackTrace();

            }
        });

    }

    @Override
    public void setObserver(Observer observer) {
        addObserver(observer);

    }
}
