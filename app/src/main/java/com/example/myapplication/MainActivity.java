package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
	private ListView clickablePosts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		GetDataService service = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);

		Call<List<PostUserComment>> call = service.getAllPhotos();
		call.enqueue(new Callback<List<PostUserComment>>() {

			@Override
			public void onResponse(Call<List<PostUserComment>> call, Response<List<PostUserComment>> response) {
				generateDataList(response.body());
			}

			@Override
			public void onFailure(Call<List<PostUserComment>> call, Throwable t) {
				Toast.makeText(MainActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void generateDataList(List<PostUserComment> posts) {
		clickablePosts = findViewById(R.id.appsListView);
		ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,posts);
		clickablePosts.setAdapter(arrayAdapter);
	}
}
