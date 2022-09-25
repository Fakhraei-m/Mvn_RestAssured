# Run the API Under Test (AUT):
# AUT is mock Authentication JSON Server
# Go to (C:\Tools\Json_Server_db)
# cmd > node server.js
# Ref: https://github.com/typicode/json-server

@tag
Feature: Send POST to login and receive authentication token, with this token Send POST, verify with GET, then PUT and verify with GET, then DELETE and again verify with GET
  I'm using REST-assured library to test the Mock Authentication JSON Server (https://github.com/typicode/json-server)

  @tag1
  Scenario: Send POST request with a valid user credential to receive a accsess token
  #Send POST request : http://localhost:3000/posts
    Given I send POST request for Authentication as following
    |	Fields			|	Values					|
    |	path param	|	/auth/login			|
    |	email				|	venus@gmail.com	|
    |	password		|	venus123				|
    Then I should receive an Access Token in response
    
  @tag1
  Scenario: Send POST request as Authorized user and check the response is match with the request
  #Send POST request : http://localhost:3000/posts
    Given I send POST request with authentication token as following
    |	Fields			|	Values		|
    |	path param	|	/posts		|
    |	title				|	QAEngineer|
    |	author			|	MdF3			|
    |	id					|	25				|
    Then I should receive response with unique data as following
    |	Fields			|	Values		|
    |	status code	|	201				|
    |	title				|	QAEngineer|
    |	author			|	MdF3			|
    |	id					|	25				|
    And I should recieve an ID number
    
  @tag1
  Scenario: Send GET request with query path "id" to verify response is unique id
  #Send GET request : http://localhost:3000/posts/ID
  	Given I send GET request with authentication token as following
    |	Fields			|	Values			|
    |	path param	|	/posts/25		|
    |	title				|							|
    |	author			|							|
    |	id					|							|
    Then I should receive response with unique data as following
    |	Fields			|	Values		|
    |	status code	|	200				|
    |	title				|	QAEngineer|
    |	author			|	MdF3			|
    |	id					|	25				|

  @tag1
  Scenario: Send PUT request with query path "id" to verify response is unique id
  #Send GET request : http://localhost:3000/posts/ID
  #the PUT response is the Updated record!
  	Given I send PUT request with authentication token as following
    |	Fields			|	Values			|
    |	path param	|	/posts/25		|
    |	title				|	Artist			|
    |	author			|	Mamal				|
    |	id					|							|
    Then I should receive response with unique data as following
    |	Fields			|	Values		|
    |	status code	|	200				|
    |	title				|	Artist		|
    |	author			|	Mamal			|
    |	id					|	25				|

  @tag1
  Scenario: Send GET request with query path "id" to verify response is unique id
  #Send GET request : http://localhost:3000/posts/ID
  	Given I send GET request with authentication token as following
    |	Fields			|	Values			|
    |	path param	|	/posts/25		|
    |	title				|							|
    |	author			|							|
    |	id					|							|
    Then I should receive response with unique data as following
    |	Fields			|	Values		|
    |	status code	|	200				|
    |	title				|	Artist		|
    |	author			|	Mamal			|
    |	id					|	25				| 
           
  @tag1
  Scenario: Send DELETE request with query path "id" to verify response is unique id
  #Send DELETE request : http://localhost:3000/posts/ID
  # for JSON-Server API we can only sent Delete request with path parameter, and the query parameters will be ignored
  # For DELETE request with authentication token, the response will be empty!
  	Given I send DELETE request with authentication token as following
    |	Fields			|	Values			|
    |	path param	|	/posts/25		|
    |	title				|							|
    |	author			|							|
    |	id					|							|
    Then I should receive response with unique data as following
    |	Fields			|	Values		|
    |	status code	|	200				|
    |	title				|						|
    |	author			|						|
    |	id					|						|

  @tag1
  Scenario: Send GET request with query path "id" to verify response is unique id
  #Send GET request : http://localhost:3000/posts/ID
  	Given I send GET request with authentication token as following
    |	Fields			|	Values			|
    |	path param	|	/posts/25		|
    |	title				|							|
    |	author			|							|
    |	id					|							|
    Then I should receive response with unique data as following
    |	Fields			|	Values		|
    |	status code	|	404				|
    |	title				|						|
    |	author			|						|
    |	id					|						|

