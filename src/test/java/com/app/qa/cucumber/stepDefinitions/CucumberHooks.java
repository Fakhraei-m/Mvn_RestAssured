package com.app.qa.cucumber.stepDefinitions;

import io.cucumber.java.Before;
import com.app.qa.util.RestAssuredExtension;

public class CucumberHooks {

	@Before
	public void TestSetup()
	{
		System.out.println("**--->Here in CucumberHooks @Before");
		RestAssuredExtension restAssuredExtension = new RestAssuredExtension();
	}
}
