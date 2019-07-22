package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataService {
	@GET("/{data}")
	Call<List<PostUserComment>> getApiData(@Path("data") String data);
}
