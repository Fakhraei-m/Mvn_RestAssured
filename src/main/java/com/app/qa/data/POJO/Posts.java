package com.app.qa.data.POJO;

//Here is the JSON for /posts
//{
//    "id": 1,
//    "title": "json-server",
//    "author": "typicode"
//}

public class Posts {
	private String id;
	private String title;
	private String author;
	
	public Posts() {}
	
	public Posts(String id, String title, String author) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
}
