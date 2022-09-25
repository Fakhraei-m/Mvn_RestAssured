package com.app.qa.cucumber.stepDefinitions;

import java.util.HashMap;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

public class CucumberEnvVars {

	static ResponseOptions<Response> response;
	static HashMap<String, String> postReqBodyMap = new HashMap<>();
	static HashMap<String, String> queryParamMap = new HashMap<>();
	static String pathParam;
	static String accessToken;

}
