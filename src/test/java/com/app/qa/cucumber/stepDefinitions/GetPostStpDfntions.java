package com.app.qa.cucumber.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

//import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import java.util.Map;

import org.apache.http.util.TextUtils;
import org.testng.Assert;

import com.app.qa.util.RestAssuredExtension;

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
		Assert.assertTrue(response.getBody().jsonPath().get("author").equals(authorName));
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

	@Then("I should recieve an ID number")
	public void i_should_recieve_id_number()
	{
		System.out.println(String.valueOf(response.getBody().jsonPath().get("id")));
		Assert.assertTrue(!TextUtils.isEmpty(String.valueOf(response.getBody().jsonPath().get("id"))));
	}
	
	@Given("I send POST request as following")
	public void i_send_post_request_as_following(DataTable dataTable)
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
	    
	    response = RestAssuredExtension.POSTRequestWithBody(pathParam, postReqBodyMap);
	}
	
	@Then("I should receive response with unique data as following")
	public void i_should_receive_response_with_unique_data_as_following(DataTable dataTable)
	{
		System.out.println("*-->Response Body: "+ response.getBody().asString());
		//1st way of getting the dataTable
		//List<List<String>> data = dataTable.asLists(String.class);	    
	    //for (List<String> columns : data)
	    //    System.out.println("*-->"+ columns.get(0)+" , "+ columns.get(1));
		
	    //2nd way of getting the dataTable
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);	    
	    for (Map<String, String> columns : rows)
	    {
	    	System.out.println("*-->"+ columns.get("Fields")+" , "+ columns.get("Values"));
	    	if(columns.get("Fields").equals("title") & columns.get("Values") != null)
	    	{
	    		//Assert.assertTrue(response.getBody().jsonPath().get("[0].title").equals(columns.get("Values")));//If Response is in Array
	    		Assert.assertTrue(response.getBody().jsonPath().get("title").equals(columns.get("Values")));//If Response is Not in Array
	    	}
	    	else if (columns.get("Fields").equals("author") & columns.get("Values") != null)
	    	{
	    		//Assert.assertTrue(response.getBody().jsonPath().get("[0].author").equals(columns.get("Values")));//If Response is in Array
	    		Assert.assertTrue(response.getBody().jsonPath().get("author").equals(columns.get("Values")));//If Response is Not in Array
	    	}
	    	else if (columns.get("Fields").equals("id") & columns.get("Values") != null)
	    	{
	    		Assert.assertTrue(response.getBody().jsonPath().get("id").equals(columns.get("Values")));//If Response is in Array
	    		//Assert.assertTrue(response.getBody().jsonPath().get("[0].id").equals(columns.get("Values")));//If Response is Not in Array
	    	}    		
	    	else if (columns.get("Fields").equals("status code") & columns.get("Values") != null)
	    	{
	    		Assert.assertTrue(String.valueOf(response.statusCode()).equals(columns.get("Values")));
	    	}
	    }
	}
	
	@Then("I should receive response with some data as following")
	public void i_should_receive_response_with_some_data_as_following(DataTable dataTable)
	{
		System.out.println("*-->Response Body: "+ response.getBody().asString());
		//1st way of getting the dataTable
		//List<List<String>> data = dataTable.asLists(String.class);	    
	    //for (List<String> columns : data)
	    //    System.out.println("*-->"+ columns.get(0)+" , "+ columns.get(1));
		
	    //2nd way of getting the dataTable
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);	    
	    for (Map<String, String> columns : rows)
	    {
	    	System.out.println("*-->"+ columns.get("Fields")+" , "+ columns.get("Values"));
	    	if(columns.get("Fields").equals("title") & columns.get("Values") != null)
	    	{
	    		Assert.assertTrue(response.getBody().jsonPath().get("[0].title").equals(columns.get("Values")));//If Response is in Array
	    		//Assert.assertTrue(response.getBody().jsonPath().get("title").equals(columns.get("Values")));//If Response is Not in Array
	    	}
	    	else if (columns.get("Fields").equals("author") & columns.get("Values") != null)
	    	{
	    		Assert.assertTrue(response.getBody().jsonPath().get("[0].author").equals(columns.get("Values")));//If Response is in Array
	    		//Assert.assertTrue(response.getBody().jsonPath().get("author").equals(columns.get("Values")));//If Response is Not in Array
	    	}
	    	else if (columns.get("Fields").equals("id") & columns.get("Values") != null)
	    	{
	    		//Assert.assertTrue(response.getBody().jsonPath().get("id").equals(columns.get("Values")));//If Response is in Array
	    		Assert.assertTrue(response.getBody().jsonPath().get("[0].id").equals(columns.get("Values")));//If Response is Not in Array
	    	}    		
	    	else if (columns.get("Fields").equals("status code") & columns.get("Values") != null)
	    	{
	    		Assert.assertTrue(String.valueOf(response.statusCode()).equals(columns.get("Values")));
	    	}
	    }
	}

	@Given("I send GET request as following")
	public void i_send_get_request_as_following(DataTable dataTable)
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
		response = RestAssuredExtension.GETRequestWithQueryParams(pathParam, queryParamMap);
	}
}
