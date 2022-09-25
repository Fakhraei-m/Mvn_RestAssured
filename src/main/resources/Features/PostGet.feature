# Run the API Under Test (AUT):
# Go to (C:\Tools\Json_Server_db)
# cmd > json-server --watch db.json
# Ref: https://github.com/typicode/json-server

@tag
Feature: Send POST and verigy with GET using query parameters
  I'm using REST-assured library to test the Mock JSON Server (https://github.com/typicode/json-server)

  @tag1
  Scenario: Send POST request and check the response is match with the request
  #Send POST request : http://localhost:3000/posts
    Given I send POST request as following
    |	Fields			|	Values		|
    |	path param	|	/posts		|
    |	title				|	QA Lead		|
    |	author			|	Mamal			|
    |	id					|						|
    Then I should receive response with unique data as following
    |	Fields			|	Values		|
    |	status code	|	201				|
    |	title				|	QA Lead		|
    |	author			|	Mamal			|
    |	id					|						|
    And I should recieve an ID number
    
  @tag1
  Scenario: Send GET request with query parameter "title" to verify response is Array[] with all enteries with match title
  #Send GET request : http://localhost:3000/posts/ID
  	Given I send GET request as following
    |	Fields			|	Values			|
    |	path param	|	/posts			|
    |	title				|	QA Lead			|
    |	author			|							|
    |	id					|							|
    Then I should receive response with some data as following
    |	Fields			|	Values		|
    |	status code	|	200				|
    |	title				|	QA Lead		|
    |	author			|	Mamal			|
    |	id					|						|

  @tag1
  Scenario: Send GET request with query parameters "title" and "author" to verify response is Array[] with all enteries with match title
  #Send GET request : http://localhost:3000/posts/ID
  	Given I send GET request as following
    |	Fields			|	Values			|
    |	path param	|	/posts			|
    |	title				|	QA Lead			|
    |	author			|	Mamal				|
    |	id					|							|
    Then I should receive response with some data as following
    |	Fields			|	Values		|
    |	status code	|	200				|
    |	title				|	QA Lead		|
    |	author			|	Mamal			|
    |	id					|						|  

    