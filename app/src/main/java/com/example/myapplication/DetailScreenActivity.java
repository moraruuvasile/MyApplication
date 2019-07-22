package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailScreenActivity extends AppCompatActivity {
	private static final String TAG = "DetailScreenActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_screen);
		PostUserComment post = (PostUserComment) getIntent().getSerializableExtra("Post");
		if (post != null) {
			TextView postTitle = findViewById(R.id.post_title);
			postTitle.setText(post.getTitle());

			TextView postBody = findViewById(R.id.post_body);
			postBody.setText(post.getBody());

			GetDataService service = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
			Call<List<PostUserComment>> call = service.getAllPhotos();
			call.enqueue(new Callback<List<PostUserComment>>() {

				@Override
				public void onResponse(Call<List<PostUserComment>> call, Response<List<PostUserComment>> response) {
					authorName(response.body());
				}

				@Override
				public void onFailure(Call<List<PostUserComment>> call, Throwable t) {
					Toast.makeText(DetailScreenActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
				}
			});
		}
	}
	private void authorName(final List<PostUserComment> posts) {
		Log.d(TAG, "authorName: ");
	}
}
