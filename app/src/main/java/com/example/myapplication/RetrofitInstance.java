package com.example.myapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
	private static Retrofit retrofit;

	public static Retrofit getRetrofitInstance() {
		if (retrofit == null) {
			retrofit = new retrofit2.Retrofit.Builder()
					.baseUrl("http://jsonplaceholder.typicode.com")
					.addConverterFactory(GsonConverterFactory.create())
					.build();
		}
		return retrofit;
	}
}
