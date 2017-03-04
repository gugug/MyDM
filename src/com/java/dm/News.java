package com.java.dm;

public class News {
	private int ID;
	private String type;
	private String title;
	private String content;
	
	public News() {
		super();
	}
	
	public News(int iD, String type, String title, String content) {
		super();
		ID = iD;
		this.type = type;
		this.title = title;
		this.content = content;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
