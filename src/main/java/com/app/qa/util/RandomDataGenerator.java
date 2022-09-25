package com.app.qa.util;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomDataGenerator {

	public static String getFirstName()
	{
		return ("John" + RandomStringUtils.randomAlphabetic(2));
	}
	
	public static String getLastName()
	{
		return ("Kenedy" + RandomStringUtils.randomAlphabetic(2));
	}
	
	public static String getUserName()
	{
		return ("John" + RandomStringUtils.randomAlphabetic(4));
	}
	
	public static String getPassword()
	{
		return ("John" + RandomStringUtils.randomAlphabetic(4));
	}
	
	public static String getEmail()
	{
		return (RandomStringUtils.randomAlphabetic(4)+"gmail.com");
	}
	
	public static String getAge()
	{
		return ("John" + RandomStringUtils.randomNumeric(2));
	}
}
