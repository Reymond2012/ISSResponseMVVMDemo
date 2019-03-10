package com.example.issresponsemvvmdemo.net;

import com.example.issresponsemvvmdemo.data.ISSRepo;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.issresponsemvvmdemo.net.Constants.ENDPOINT;

public interface ISSService {

    @GET(ENDPOINT)
    Call<ISSRepo> getResult(@Query("lat") String latitude, @Query("lon") String longitude);

    // @GET(Constants.SHUTTLE_ENDPOINT)
    //    Call<ShuttleResponse> getShuttleLaunchesForDate(@Path("date") String date);
}
