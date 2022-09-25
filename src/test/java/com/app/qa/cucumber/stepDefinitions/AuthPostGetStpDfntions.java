package com.app.qa.cucumber.stepDefinitions;

import java.util.List;
import java.util.Map;

import org.apache.http.util.TextUtils;
import org.testng.Assert;

import com.app.qa.util.RestAssuredExtension;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AuthPostGetStpDfntions extends CucumberEnvVars{
	
	@Given("I send POST request for Authentication as following")
	public void i_send_a_post_request_for_auth_as_following(DataTable dataTable)
	{
		//clean the Env Vars
		postReqBodyMap.clear();
		pathParam = "";
		
	    //getting the dataTable
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);	    
	    for (Map<String, String> columns : rows)
	    {
	    	System.out.println("*-->"+ columns.get("Fields")+" , "+ columns.get("Values"));
	    	//filling the Map for body
	    	if(columns.get("Fields").equals("email") | columns.get("Fields").equals("password"))
	    		postReqBodyMap.put(columns.get("Fields"), columns.get("Values"));
	    	else if(columns.get("Fields").equals("path param"))
	    		pathParam = columns.get("Values");
	    }	    	
	    System.out.println("*-->postReqBodyMap: "+postReqBodyMap);
	    //Hard coding the value to body  
	    //postReqBodyMap.put("email","venus@gmail.com");
	    //postReqBodyMap.put("password","venus123");
	    
	    response = RestAssuredExtension.POSTRequestWithBody(pathParam, postReqBodyMap);
	}
	
	@Then("I should receive an Access Token in response")
	public void i_should_recieve_an_access_token_in_response()
	{
		System.out.println("*-->Here to get the access token");
		
		Assert.assertTrue(!TextUtils.isEmpty(String.valueOf(response.getBody().jsonPath().get("access_token"))));
		accessToken = response.getBody().jsonPath().get("access_token");
		System.out.println("*-->body with Access Token: "+ accessToken);
	}
	
	@Given("I send POST request with authentication token as following")
	public void i_send_post_request_with_auth_token_as_following(DataTable dataTable)
	{
		postReqBodyMap.clear();
		//1st way of getting the dataTable
//		List<List<String>> data = dataTable.asLists(String.class);	    
//	    for (List<String> columns : data)
//	        System.out.println("*-->"+ columns.get(0)+" , "+ columns.get(1));
		
	    //2nd way of getting the dataTable
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);	    
	    for (Map<String, String> columns : rows)
	    {
	    	System.out.println("*-->"+ columns.get("Fields")+" , "+ columns.get("Values"));
	    	//filling the Map for body
	    	//id firld is not mandatory
	    	//if(columns.get("Fields").equals("title") | columns.get("Fields").equals("author"))
	    	if(columns.get("Fields").equals("title") | columns.get("Fields").equals("author") | columns.get("Fields").equals("id"))
	    		postReqBodyMap.put(columns.get("Fields"), columns.get("Values"));
	    	else if(columns.get("Fields").equals("path param"))
	    		pathParam = columns.get("Values");
	    }	    	
	    
	    //Hard coding the value to body  
	    //postReqBodyMap.put("title","QAEngineer");
	    //postReqBodyMap.put("author","MdF");
	    
	    response = RestAssuredExtension.POSTRequestWithBodyWithToken(pathParam, postReqBodyMap, accessToken);
	}
	
	@Given("I send GET request with authentication token as following")
	public void i_send_get_request_with_auth_token_as_following(DataTable dataTable)
	{
		queryParamMap.clear();
		//2nd way of getting the dataTable
				List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);	    
			    for (Map<String, String> columns : rows)
			    {
			    	System.out.println("*-->"+ columns.get("Fields")+" , "+ columns.get("Values"));
			    	//filling the Map for body
			    	//id firld is not mandatory
			    	//if(columns.get("Fields").equals("title") | columns.get("Fields").equals("author"))
			    	if(columns.get("Fields").equals("path param"))
			    		pathParam = columns.get("Values");
			    	else if(columns.get("Fields").equals("title") | columns.get("Fields").equals("author") | columns.get("Fields").equals("id"))
			    		if(columns.get("Values") != null)
			    			queryParamMap.put(columns.get("Fields"), columns.get("Values"));
			    }
			    System.out.println("*-->queryParamMap: "+queryParamMap);
		response = RestAssuredExtension.GETRequestWithQueryParamsWithToken(pathParam, queryParamMap, accessToken);
	}
	
	@Given("I send PUT request with authentication token as following")
	public void i_send_put_request_with_auth_token_as_following(DataTable dataTable)
	{
		postReqBodyMap.clear();
		queryParamMap.clear();
		//1st way of getting the dataTable
//		List<List<String>> data = dataTable.asLists(String.class);	    
//	    for (List<String> columns : data)
//	        System.out.println("*-->"+ columns.get(0)+" , "+ columns.get(1));
		
	    //2nd way of getting the dataTable
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);	    
	    for (Map<String, String> columns : rows)
	    {
	    	System.out.println("*-->"+ columns.get("Fields")+" , "+ columns.get("Values"));

	    	if(columns.get("Fields").equals("title") | columns.get("Fields").equals("author") | columns.get("Fields").equals("id"))
	    		postReqBodyMap.put(columns.get("Fields"), columns.get("Values"));
	    	else if(columns.get("Fields").equals("path param"))
	    		pathParam = columns.get("Values");
	    }	    	
	    
	    //Hard coding the value to body  
	    //postReqBodyMap.put("title","QAEngineer");
	    //postReqBodyMap.put("author","MdF");
	    
	    response = RestAssuredExtension.PUTRequestWithBodyWithToken(pathParam, postReqBodyMap, accessToken);
	}
	
	@Given("I send DELETE request with authentication token as following")
	public void i_send_delete_request_with_auth_token_as_following(DataTable dataTable)
	{
		queryParamMap.clear();
		//1st way of getting the dataTable
//		List<List<String>> data = dataTable.asLists(String.class);	    
//	    for (List<String> columns : data)
//	        System.out.println("*-->"+ columns.get(0)+" , "+ columns.get(1));
		
	    //2nd way of getting the dataTable
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);	    
	    for (Map<String, String> columns : rows)
	    {
	    	System.out.println("*-->"+ columns.get("Fields")+" , "+ columns.get("Values"));

	    	if(columns.get("Fields").equals("path param"))
	    		pathParam = columns.get("Values");
	    	else if(columns.get("Fields").equals("title") | columns.get("Fields").equals("author") | columns.get("Fields").equals("id"))
	    		if(columns.get("Values") != null)
	    			queryParamMap.put(columns.get("Fields"), columns.get("Values"));
	    }	    	
	    
	    response = RestAssuredExtension.DELETERequestWithToken(pathParam, accessToken);
	    //In cases that API supports query parameters in DELETE request; theJSON-Server API ignores the query parameters
	    //response = RestAssuredExtension.DELETERequestWithQueryParams(pathParam, queryParamMap);
	}
}
