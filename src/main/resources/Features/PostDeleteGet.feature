# Run the API Under Test (AUT):
# AUT is mock JSON Server
# Go to (C:\Tools\Json_Server_db)
# cmd > json-server --watch db.json
# Ref: https://github.com/typicode/json-server

@tag
Feature: Send POST, verify with GET, then DELETE and again verify with GET
  I'm using REST-assured library to test the Mock JSON Server (https://github.com/typicode/json-server)
  
  
  @tag1
  Scenario: Send POST request and check the response is match with the request
  #Send POST request : http://localhost:3000/posts
    Given I send POST request as following
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
    And I should receive an ID number
    And I should receive valid json schema
    
  @tag1
  Scenario: Send GET request with query path "id" to verify response is unique id
  #Send GET request : http://localhost:3000/posts/ID
  	Given I send GET request as following
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
  Scenario: Send DELETE request with query path "id" to verify response is unique id
  #Send DELETE request : http://localhost:3000/posts/ID
  # for JSON-Server API we can only sent Delete request with path parameter, and the query parameters will be ignored
  # For DELETE request, the response will be empty!
  	Given I send DELETE request as following
    |	Fields			|	Values			|
    |	path param	|	/posts/25		|
    |	title				|							|
    |	author			|	xxxx				|
    |	id					|	xxx					|
    Then I should receive response with unique data as following
    |	Fields			|	Values		|
    |	status code	|	200				|
    |	title				|						|
    |	author			|						|
    |	id					|						|

  @tag1
  Scenario: Send GET request with query path "id" to verify response is unique id
  #Send GET request : http://localhost:3000/posts/ID
  	Given I send GET request as following
    |	Fields			|	Values			|
    |	path param	|	/posts/25		|
    |	title				|	QAEngineer	|
    |	author			|	MdF3				|
    |	id					|							|
    Then I should receive response with unique data as following
    |	Fields			|	Values		|
    |	status code	|	404				|
    |	title				|						|
    |	author			|						|
    |	id					|						|

    