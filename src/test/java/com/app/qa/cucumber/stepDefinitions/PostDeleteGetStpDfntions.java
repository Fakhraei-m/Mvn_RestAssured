package com.app.qa.cucumber.stepDefinitions;

import java.util.List;
import java.util.Map;

import com.app.qa.util.RestAssuredExtension;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;

public class PostDeleteGetStpDfntions extends CucumberEnvVars{

	@Given("I send DELETE request as following")
	public void i_send_delete_request_as_following(DataTable dataTable)
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
	    
	    response = RestAssuredExtension.DELETERequest(pathParam);
	    //In cases that API supports query parameters in DELETE request; theJSON-Server API ignores the query parameters
	    //response = RestAssuredExtension.DELETERequestWithQueryParams(pathParam, queryParamMap);
	}


}

