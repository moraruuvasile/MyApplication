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
	private int authorID;
	private int postID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_screen);
		PostUserComment postTitleBody = (PostUserComment) getIntent().getSerializableExtra("Post");
		if (postTitleBody != null) {
			TextView postTitle = findViewById(R.id.post_title);
			postTitle.setText(postTitleBody.getTitle());

			TextView postBody = findViewById(R.id.post_body);
			postBody.setText(postTitleBody.getBody());

			authorID = postTitleBody.getUserId();
			postID = postTitleBody.getId();

			GetDataService service = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);

//			Define author name
			Call<List<PostUserComment>> call = service.getApiData("users");
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
//			Define nr of comments by calculating them
			call = service.getApiData("comments");
			call.enqueue(new Callback<List<PostUserComment>>() {

				@Override
				public void onResponse(Call<List<PostUserComment>> call, Response<List<PostUserComment>> response) {
					countComments(response.body());
				}

				@Override
				public void onFailure(Call<List<PostUserComment>> call, Throwable t) {
					Toast.makeText(DetailScreenActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
				}
			});
		}
	}
	private void authorName(final List<PostUserComment> authorName) {
		Log.d(TAG, "authorName: ");
		TextView postAuthor = findViewById(R.id.post_author);
		postAuthor.setText(authorName.get(authorID-1).getName());
	}

	private void countComments(final List<PostUserComment> authorName) {
		Log.d(TAG, "authorName: ");
		TextView postAuthor = findViewById(R.id.post_number_coments);

		int i = 0;
		int count = 0;

		while(i++ < authorName.size()){
			if(postID == authorName.get(i-1).getPostId()){
				count++;
			}
		}

		postAuthor.setText("Post received " + count + " commments.");
	}
}
