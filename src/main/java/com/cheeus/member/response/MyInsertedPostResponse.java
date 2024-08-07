package com.cheeus.member.response;

public class MyInsertedPostResponse {

	private int id;
	private String title;
	private String writeday;
	private int category;
	
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
	
	public String getWriteday() {
		return writeday;
	}
	
	public void setWriteday (String writeday) {
		this.writeday = writeday;
	}
	
	public int getCategory() {
		return category;
	}
	
	public void setCategory (int category) {
		this.category = category;
	}
}
