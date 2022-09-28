package com.app.qa.cucumber.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
//import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.List;
import java.util.Map;

import org.apache.http.util.TextUtils;
import org.testng.Assert;

import com.app.qa.data.POJO.Posts;
import com.app.qa.util.RestAssuredExtension;

import static org.hamcrest.MatcherAssert.assertThat; 


public class GetPostStpDfntions extends CucumberEnvVars{
	
	@Given("I send GET request with path param as {string} and query param as {string}")
	public void i_send_get_request_with_path_param_and_query_param(String path, String query)
	{		
		response = RestAssuredExtension.GETRequest(path);
	}

	@Then("I should see author name as {string}")
	public void i_should_see_author_name_as(String authorName)
	{
		System.out.println("**--> " + response.getBody().jsonPath().get("author"));
		//Use this when directly parsing the JSON response 
		//Assert.assertTrue(response.getBody().jsonPath().get("author").equals(authorName));
		
		//DESERIALIZING - Use this when parsing the JSON response using POJO Class
		var posts = response.getBody().as(Posts.class);
		Assert.assertEquals(posts.getAuthor(), authorName);
	}

	@Then("I should see status code {string}")
	public void i_should_see_status_code(String StatusCode)
	{	
		System.out.println("**-->Status Code: " + response.getStatusCode());
		//I use TestNG assertions; the assertions of hamcrest is sucks!
	    Assert.assertEquals(response.getStatusCode(), Integer.parseInt(StatusCode));
	}
	
	@Given("I send POST request with path param as {string} and query param as {string}")
	public void i_send_post_request_with_path_param_as_and_query_param_as(String path, String query)
	{
		//response = RestAssuredExtension.POSTRequestWithBody(path,);
	}

	@Then("I should receive valid json schema")
	public void i_should_receive_valid_json_schema()
	{
		System.out.println("*-->START: I should receive valid json schema");
		//JSON Schema Validator file
		String PathToJsonSchema = "com/app/qa/data/POJO/Posts.json"; //These paths give error "src/main/java/com/app/qa/data/POJO/Posts.json" , System.getProperty("user.dir")+ "/src/main/java/com/app/qa/data/POJO/Posts.json";
		
		//Get response body as string
		var responseBody = response.getBody().asString();
		assertThat(responseBody, JsonSchemaValidator.matchesJsonSchemaInClasspath(PathToJsonSchema));
		
		//TestNG Assertions won't work
		//Assert.assertEquals(responseBody, JsonSchemaValidator.matchesJsonSchemaInClasspath(PathToJsonSchema));
		System.out.println("*-->END: I should receive valid json schema");
	}
	
	@Then("I should receive an ID number")
	public void i_should_recieve_id_number()
	{
		System.out.println("*-->START: I should recieve an ID number");
		
		//DESERIALIZING - Use this when parsing the JSON response using POJO Class
		var posts = response.getBody().as(Posts.class);
		System.out.println("*-->ID: "+ posts.getId());
		Assert.assertTrue(!TextUtils.isEmpty(String.valueOf(posts.getId())));
		
		//Without DESERIALIZING
		//System.out.println(String.valueOf(response.getBody().jsonPath().get("id")));
		//Assert.assertTrue(!TextUtils.isEmpty(String.valueOf(response.getBody().jsonPath().get("id"))));
		
		String PathToJsonSchema = "com/app/qa/data/POJO/Posts.json"; //These paths give error "src/main/java/com/app/qa/data/POJO/Posts.json" , System.getProperty("user.dir")+ "/src/main/java/com/app/qa/data/POJO/Posts.json";
		System.out.println("*-->PathToJsonSchema: "+ PathToJsonSchema);
		JsonSchemaValidator.matchesJsonSchemaInClasspath(PathToJsonSchema);
		System.out.println("*-->END: I should recieve an ID number");
	}
	
	@Given("I send POST request as following")
	public void i_send_post_request_as_following(DataTable dataTable)
	{
		System.out.println("*-->START: I send POST request as following");
		postReqBodyMap.clear();
		//1st way of getting the dataTable
//		List<List<String>> data = dataTable.asLists(String.class);	    
//	    for (List<String> columns : data)
//	        System.out.println("*-->"+ columns.get(0)+" , "+ columns.get(1));
		
	    //2nd way of getting the dataTable
		System.out.println("*>--------------------Data Table-----------------");
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);	    
	    for (Map<String, String> columns : rows)
	    {
	    	System.out.println("*-->"+ columns.get("Fields")+" , "+ columns.get("Values"));
	    	//filling the Map for body
	    	//id field is not mandatory
	    	//if(columns.get("Fields").equals("title") | columns.get("Fields").equals("author"))
	    	if(columns.get("Fields").equals("title") | columns.get("Fields").equals("author") | columns.get("Fields").equals("id"))
	    		postReqBodyMap.put(columns.get("Fields"), columns.get("Values"));
	    	else if(columns.get("Fields").equals("path param"))
	    		pathParam = columns.get("Values");
	    }	    	
	    
	    //Hard coding the value to body  
	    //postReqBodyMap.put("title","QAEngineer");
	    //postReqBodyMap.put("author","MdF");
	    
	    response = RestAssuredExtension.POSTRequestWithBody(pathParam, postReqBodyMap);
	    System.out.println("*-->END: I send POST request as following");
	}
	
	@Then("I should receive response with unique data as following")
	public void i_should_receive_response_with_unique_data_as_following(DataTable dataTable)
	{
		System.out.println("*-->START: I should receive response with unique data as following");
		//DESERIALIZING - Use this when parsing the JSON response using POJO Class
		var posts = response.getBody().as(Posts.class);
				
		System.out.println("*-->Response Body: "+ response.getBody().asString());
		//1st way of getting the dataTable
		//List<List<String>> data = dataTable.asLists(String.class);	    
	    //for (List<String> columns : data)
	    //    System.out.println("*-->"+ columns.get(0)+" , "+ columns.get(1));
		
	    //2nd way of getting the dataTable
		System.out.println("*>--------------------Data Table & Response--------------");
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);	    
	    for (Map<String, String> columns : rows)
	    {
	    	System.out.println("*-->"+ columns.get("Fields")+" , "+ columns.get("Values"));
	    	if(columns.get("Fields").equals("title") & columns.get("Values") != null)
	    	{
	    		//Without DESERIALIZING
	    		//Assert.assertTrue(response.getBody().jsonPath().get("[0].title").equals(columns.get("Values")));//If Response is in Array
	    		//Assert.assertTrue(response.getBody().jsonPath().get("title").equals(columns.get("Values")));//If Response is Not in Array
	    		//DESERIALIZING
	    		Assert.assertEquals(posts.getTitle(), columns.get("Values"));
	    	}
	    	else if (columns.get("Fields").equals("author") & columns.get("Values") != null)
	    	{
	    		//Without DESERIALIZING
	    		//Assert.assertTrue(response.getBody().jsonPath().get("[0].author").equals(columns.get("Values")));//If Response is in Array
	    		//Assert.assertTrue(response.getBody().jsonPath().get("author").equals(columns.get("Values")));//If Response is Not in Array
	    		//DESERIALIZING
	    		Assert.assertEquals(posts.getAuthor(), columns.get("Values"));
	    	}
	    	else if (columns.get("Fields").equals("id") & columns.get("Values") != null)
	    	{
	    		//Without DESERIALIZING
	    		//Assert.assertTrue(response.getBody().jsonPath().get("id").equals(columns.get("Values")));//If Response is in Array
	    		//Assert.assertTrue(response.getBody().jsonPath().get("[0].id").equals(columns.get("Values")));//If Response is Not in Array
	    		//DESERIALIZING
	    		Assert.assertEquals(String.valueOf(posts.getId()), columns.get("Values"));
	    	}    		
	    	else if (columns.get("Fields").equals("status code") & columns.get("Values") != null)
	    	{
	    		Assert.assertTrue(String.valueOf(response.statusCode()).equals(columns.get("Values")));
	    	}
	    }
	    System.out.println("*-->END: I should receive response with unique data as following");
	}
	
	@Then("I should receive response with some data as following")
	public void i_should_receive_response_with_some_data_as_following(DataTable dataTable)
	{
		System.out.println("*-->START: I should receive response with some data as following");
		//DESERIALIZING - Use this when parsing the JSON response using POJO Class
		var posts = response.getBody().as(Posts[].class);
		
		System.out.println("*-->Response Body: "+ response.getBody().asString());
		//1st way of getting the dataTable
		//List<List<String>> data = dataTable.asLists(String.class);	    
	    //for (List<String> columns : data)
	    //    System.out.println("*-->"+ columns.get(0)+" , "+ columns.get(1));
		
	    //2nd way of getting the dataTable
		System.out.println("*>--------------------Data Table & Response--------------");
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);	    
	    for (Map<String, String> columns : rows)
	    {
	    	System.out.println("*-->"+ columns.get("Fields")+" , "+ columns.get("Values"));
	    	if(columns.get("Fields").equals("title") & columns.get("Values") != null)
	    	{
	    		//Without DESERIALIZING
	    		//Use this if you directly parsing the JSON response 
	    		//Assert.assertTrue(response.getBody().jsonPath().get("[0].title").equals(columns.get("Values")));//If Response is in Array
	    		
	    		//DESERIALIZING -Use this when you're using POJO Class
	    		Assert.assertEquals(posts[0].getTitle(), columns.get("Values"));
	    		//We are just checking the first element in Response Body Array
	    	}
	    	else if (columns.get("Fields").equals("author") & columns.get("Values") != null)
	    	{	    		
	    		//Assert.assertTrue(response.getBody().jsonPath().get("[0].author").equals(columns.get("Values")));//If Response is in Array
	    		Assert.assertEquals(posts[0].getAuthor(), columns.get("Values"));
	    			    	}
	    	else if (columns.get("Fields").equals("id") & columns.get("Values") != null)
	    	{
	    		Assert.assertEquals(String.valueOf(posts[0].getId()), columns.get("Values"));
	    		//Assert.assertTrue(response.getBody().jsonPath().get("[0].id").equals(columns.get("Values")));//If Response is Not in Array
	    	}    		
	    	else if (columns.get("Fields").equals("status code") & columns.get("Values") != null)
	    	{
	    		Assert.assertTrue(String.valueOf(response.statusCode()).equals(columns.get("Values")));
	    	}
	    }
	    System.out.println("*-->END: I should receive response with some data as following");
	}

	@Given("I send GET request as following")
	public void i_send_get_request_as_following(DataTable dataTable)
	{
		System.out.println("*-->START: I send GET request as following");
		queryParamMap.clear();
		
		Posts bodyDataPOJO = new Posts();
		
		//2nd way of getting the dataTable
		System.out.println("*>--------------------Data Table------------------");
				List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);	    
			    for (Map<String, String> columns : rows)
			    {
			    	System.out.println("*-->"+ columns.get("Fields")+" , "+ columns.get("Values"));
			    	//Filling the Map for body
			    	//if(columns.get("Fields").equals("path param"))
			    	//	pathParam = columns.get("Values");
			    	//else if(columns.get("Fields").equals("title") | columns.get("Fields").equals("author") | columns.get("Fields").equals("id"))
			    	//	if(columns.get("Values") != null)
			    	//	{
			    	//		//Without SERIALIZING
			    	//		queryParamMap.put(columns.get("Fields"), columns.get("Values"));
			    	//	}
			    	switch(columns.get("Fields"))
			    	{
			    	case("id"):
			    		//Without SERIALIZING
			    		queryParamMap.put(columns.get("Fields"), columns.get("Values"));
			    		//SERIALIZING
			    		bodyDataPOJO.setId(columns.get("Values"));
			    		break;
			    	case ("title"):
			    		//Without SERIALIZING
			    		queryParamMap.put(columns.get("Fields"), columns.get("Values"));
			    		//SERIALIZING
			    		bodyDataPOJO.setTitle(columns.get("Values"));
			    		break;
			    	case("author"):
			    		//Without SERIALIZING
			    		queryParamMap.put(columns.get("Fields"), columns.get("Values"));
			    		//SERIALIZING
			    		bodyDataPOJO.setAuthor(columns.get("Values"));
			    		break;
			    	case("path param"):
			    		pathParam = columns.get("Values");			    		
			    	}	
			    			
			    }
			    System.out.println("*-->queryParamMap: "+queryParamMap);
		//Without SERIALIZING	    
		//response = RestAssuredExtension.GETRequestWithQueryParams(pathParam, queryParamMap);
		//SERIALIZING
		response = RestAssuredExtension.GETRequestWithQueryParams(pathParam, bodyDataPOJO);
		System.out.println("*???????????????????-->END: I send GET request as following");
	}
}
