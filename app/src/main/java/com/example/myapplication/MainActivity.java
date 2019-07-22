package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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
				generateClickablePosts(response.body());
			}

			@Override
			public void onFailure(Call<List<PostUserComment>> call, Throwable t) {
				Toast.makeText(MainActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void generateClickablePosts(final List<PostUserComment> posts) {
		clickablePosts = findViewById(R.id.appsListView);
		ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,posts);
		clickablePosts.setAdapter(arrayAdapter);

		clickablePosts.setOnItemClickListener(
				new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//						Toast.makeText(MainActivity.this, String.valueOf(posts.get(i)), Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(MainActivity.this, DetailScreenActivity.class);
						intent.putExtra("Post", posts.get(i));
						startActivity(intent);
					}
				}
		);
	}
}
