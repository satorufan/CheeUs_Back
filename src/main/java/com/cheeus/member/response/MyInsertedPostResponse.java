package com.cheeus.member.response;

public class MyInsertedPostResponse {

	private int id;
	private String title;
	private String category;
	
	public MyInsertedPostResponse(int id, String title) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.title = title;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle (String title) {
		this.title = title;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory (String category) {
		this.category = category;
	}
}
