package com.app.qa.cucumber.stepDefinitions;

import java.util.List;
import java.util.Map;

import com.app.qa.util.RestAssuredExtension;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;

public class PostPutDeleteGetStpDfntions extends CucumberEnvVars{

	@Given("I send PUT request as following")
	public void i_send_put_request_as_following(DataTable dataTable)
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
	    
	    response = RestAssuredExtension.PUTRequestWithBody(pathParam, postReqBodyMap);
	}
}
