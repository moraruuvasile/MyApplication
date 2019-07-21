package com.example.myapplication;

public class PostUserComment {
	private int userId;
	private int id;
	private int postId;

	private String title;
	private String body;
	private String name;

	public PostUserComment(int userId, int id, int postId, String title, String body, String name) {
		this.userId = userId;
		this.id = id;
		this.postId = postId;
		this.title = title;
		this.body = body;
		this.name = name;
	}

	public int getUserId() {
		return userId;
	}

	public int getId() {
		return id;
	}

	public int getPostId() {
		return postId;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return title;
	}
}
