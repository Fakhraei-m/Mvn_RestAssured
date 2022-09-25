package com.app.qa.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import com.app.qa.util.RandomDataGenerator;

import io.restassured.RestAssured;

import java.util.HashMap;

import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class POSTRequestTest {

	public HashMap<String, String> postReqBodyMap = new HashMap<>();
	public JSONObject postReqBodyJSON;
	
	@BeforeMethod
	public void PostData()
	{
		System.out.println("**-->Here in POSTRequestTest.PostData");
		/*
		Request Body Json:
		{
		  "id": "1", //is arbitrary
		  "title": "someTitle",
		  "author": "someName"
		}
		*/
		postReqBodyMap.put("title", "QAE");
		postReqBodyMap.put("author", RandomDataGenerator.getFirstName());		
		System.out.println("*-->"+ postReqBodyMap);
		
        postReqBodyJSON = new JSONObject(postReqBodyMap);
        System.out.println("*-->"+ postReqBodyJSON.toString());

		RestAssured.baseURI = "http://localhost:3000/posts";
		RestAssured.basePath = "";
		
		//According to JSON Server, POST request is : "http://localhost:3000/posts"
		//https://github.com/typicode/json-server
	}
	
	@Test
	public void PostRequest() 
	{
		given()
			.contentType("application/json")
			.body(postReqBodyJSON.toString())
		.when()
			.post()
		.then()
			.statusCode(201)
			//.body("SuccessCode", equalTo(""))
			.body("title", equalTo("QAE"));
			
	}
}
