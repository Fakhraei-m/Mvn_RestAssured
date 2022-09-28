package com.app.qa.util;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.json.JSONObject;

//This is a class that implements GET POST PUT DELET requests using Rest_Assured
//The methods are implemented in non-BDD form of Rest-Assured
//This can be used in Step Definition of Cucumber
public class RestAssuredExtension 
{
	public static RequestSpecification Request;

    public RestAssuredExtension()
    {
        //Arrange
    	System.out.println("**-->Here in RestAssuredExtension constructor");
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("http://localhost:3000/");
        builder.setContentType(ContentType.JSON);
        //var requestSpec = builder.build();
        Request = RestAssured.given().spec(builder.build());
    }

    public static ResponseOptions<Response> GETRequest(String pathParams)
    {
    	System.out.println("**-->Here in RestAssuredExtension.GETRequest");
    	System.out.println("**-->pathParams: "+ pathParams);
        try {
            return Request.get(new URI(pathParams));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static ResponseOptions<Response> GETRequestWithQueryParameter(String pathParams, Map<String,String> queryParams)
//    {
//    	System.out.println("**-->Here in RestAssuredExtension.GETRequestWithPathParameter");
//        Request.pathParams(queryParams);
//        try {
//            Request.get(new URI(pathParams));
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static ResponseOptions<Response> GETRequestWithQueryParams(String pathParams, Map<String,String> queryParams)
    {
    	System.out.println("**-->Here in RestAssuredExtension.GETRequestWithQueryParams");
    	Request.queryParams(queryParams);//Request.pathParams(queryParams);
    	try {
    		return Request.get(new URI(pathParams));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    	return null;
    }
    public static ResponseOptions<Response> GETRequestWithQueryParams(String pathParams, Object bodyDataPOJO)
    {
    	System.out.println("**-->Here in RestAssuredExtension.GETRequestWithQueryParams");
    	Request.body(bodyDataPOJO);//Request.pathParams(queryParams);
    	try {
    		return Request.get(new URI(pathParams));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    	return null;
    }
    public static ResponseOptions<Response> GETRequestWithToken(String pathParams, String token)
    {
    	System.out.println("**-->Here in RestAssuredExtension.GETRequestWithToken");
        try {
            Request.header(new Header("Authorization", "Bearer " + token));
            return Request.get(new URI(pathParams));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static ResponseOptions<Response> GETRequestWithQueryParamsWithToken(String pathParams, Map<String,String> queryParams, String token)
    {
    	System.out.println("**-->Here in RestAssuredExtension.GETRequestWithQueryParamsWithToken");
    	Request.header(new Header("Authorization", "Bearer " + token));
        Request.queryParams(queryParams);
        return Request.get(pathParams);
    }
 
    public static ResponseOptions<Response> PUTRequestWithBodyAndQueryParams(String pathParams, Map<String,String> body, Map<String,String> queryParams)
    {
    	System.out.println("**-->Here in RestAssuredExtension.PUTRequestWithBodyAndQueryParams");
    	//Request.pathParams(queryParams);
    	//Request.body(body);
    	Request.queryParams(queryParams);
    	JSONObject postReqBodyJSON = new JSONObject(body);
        Request.body(postReqBodyJSON.toString());
        return Request.put(pathParams);
    }
    public static ResponseOptions<Response> PUTRequestWithBodyAndQueryParams(String pathParams, Object body, Map<String,String> queryParams)
    {
    	System.out.println("**-->Here in RestAssuredExtension.PUTRequestWithBodyAndQueryParams");
    	Request.body(body);
    	Request.queryParams(queryParams);
        return Request.put(pathParams);
    }
    public static ResponseOptions<Response> PUTRequestWithBody(String pathParams, Map<String,String> body)
    {
    	System.out.println("**-->Here in RestAssuredExtension.PUTRequestWithBodyAndQueryParams");
    	//Request.body(body);
    	JSONObject postReqBodyJSON = new JSONObject(body);
    	Request.body(postReqBodyJSON.toString());
        return Request.put(pathParams);
    }
    
    public static ResponseOptions<Response> PUTRequestWithBody(String pathParams, Object body)
    {
    	System.out.println("**-->Here in RestAssuredExtension.PUTRequestWithBodyAndQueryParams");
    	Request.body(body);
        return Request.put(pathParams);
    }
    
    public static ResponseOptions<Response> PUTRequestWithBodyWithToken(String pathParams, Map<String,String> body, String token)
    {
    	System.out.println("**-->Here in RestAssuredExtension.PUTRequestWithBodyWithToken");
    	Request.header(new Header("Authorization", "Bearer " + token));
    	//Request.body(body);
    	JSONObject postReqBodyJSON = new JSONObject(body);
    	Request.body(postReqBodyJSON.toString());
        return Request.put(pathParams);
    }
    public static ResponseOptions<Response> PUTRequestWithBodyWithToken(String pathParams, Object body, String token)
    {
    	System.out.println("**-->Here in RestAssuredExtension.PUTRequestWithBodyWithToken");
    	Request.header(new Header("Authorization", "Bearer " + token));
    	Request.body(body);
        return Request.put(pathParams);
    }
    
    public static ResponseOptions<Response> POSTRequestWithBody(String pathParams, Map<String,String> body)  
    {
    	System.out.println("**-->Here in RestAssuredExtension.POSTRequestWithBody");
    	JSONObject postReqBodyJSON = new JSONObject(body);
    	//Request.body(postReqBodyJSON.toString());
    	System.out.println("**-->>>>>>>>1");
    	Request.body(postReqBodyJSON.toString());
    	System.out.println("**-->>>>>>>>2");
        return Request.post(pathParams);
    }
    
    public static ResponseOptions<Response> POSTRequestWithBodyAndQueryParams(String pathParams, Map<String,String> queryParams, Map<String, String> body)
    {
    	System.out.println("**-->Here in RestAssuredExtension.POSTRequestWithBodyAndQueryParams");
    	//Request.pathParams(queryParams);
        //Request.body(body);
    	Request.queryParams(queryParams);
    	JSONObject postReqBodyJSON = new JSONObject(body);
    	Request.body(postReqBodyJSON.toString());
        return Request.post(pathParams);
    }
    public static ResponseOptions<Response> POSTRequestWithBodyAndQueryParams(String pathParams, Map<String,String> queryParams, Object body)
    {
    	System.out.println("**-->Here in RestAssuredExtension.POSTRequestWithBodyAndQueryParams");
        Request.body(body);
    	Request.queryParams(queryParams);
        return Request.post(pathParams);
    }
    public static ResponseOptions<Response> POSTRequestWithBodyWithToken(String pathParams, Map<String,String> body, String token)  
    {
    	System.out.println("**-->Here in RestAssuredExtension.POSTRequestWithBody");
    	Request.header(new Header("Authorization", "Bearer " + token));
    	JSONObject postReqBodyJSON = new JSONObject(body);
    	Request.body(postReqBodyJSON.toString());
        return Request.post(pathParams);
    }
    public static ResponseOptions<Response> POSTRequestWithBodyWithToken(String pathParams, Object body, String token)  
    {
    	System.out.println("**-->Here in RestAssuredExtension.POSTRequestWithBody");
    	Request.header(new Header("Authorization", "Bearer " + token));
    	Request.body(body);
        return Request.post(pathParams);
    }
    public static ResponseOptions<Response> DELETERequestWithQueryParams(String pathParams, Map<String,String> queryParams)
    {
    	System.out.println("**-->Here in RestAssuredExtension.DELETERequestWithQueryParams");
    	Request.queryParams(queryParams);
        return Request.delete(pathParams);
    }
    
    public static ResponseOptions<Response> DELETERequest(String pathParams)
    {
    	System.out.println("**-->Here in RestAssuredExtension.DELETERequest");
        return Request.delete(pathParams);
    }

    public static ResponseOptions<Response> DELETERequestWithToken(String pathParams, String token)
    {
    	System.out.println("**-->Here in RestAssuredExtension.DELETERequest");
    	Request.header(new Header("Authorization", "Bearer " + token));
        return Request.delete(pathParams);
    }



}
