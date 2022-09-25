package com.app.qa.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GETrqstTest {
	
	@BeforeMethod
	public void PostData()
	{		
		RestAssured.baseURI = "http://localhost:3000/posts/";
		RestAssured.basePath = "1";	
		// "http://localhost:3000/posts/1"
		//According to JSON Server, GET request is : "http://localhost:3000/posts/1"
		//https://github.com/typicode/json-server
	}
	
	@Test
	public void GETRequestTest()
	{
		given()
		.when()
			//.get("http://localhost:3000/posts/1")
			.get()
		.then()
			.statusCode(200)
			.statusLine("HTTP/1.1 200 OK")
			.assertThat().body("author", equalTo("typicode"))
			.header("content-type","application/json; charset=utf-8");
	}

}
