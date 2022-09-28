# Run the API Under Test (AUT):
# AUT is mock JSON Server
# Go to (C:\Tools\Json_Server_db)
# cmd > json-server --watch db.json
# Ref: https://github.com/typicode/json-server

@tag
Feature: Send POST and verigy with GET using query parameters and path parameter
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
  Scenario: Send GET request with query parameter "id" to verify response is Array[] with one entery with unique id
  #Send GET request : http://localhost:3000/posts/ID
  	Given I send GET request as following
    |	Fields			|	Values			|
    |	path param	|	/posts			|
    |	title				|							|
    |	author			|							|
    |	id					|	25					|
    Then I should receive response with some data as following
    |	Fields			|	Values		|
    |	status code	|	200				|
    |	title				|	QAEngineer|
    |	author			|	MdF3			|
    |	id					|	25				|
   
  @tag1
  Scenario: Send GET request with query parameter "title" to verify response is Array[] with all enteries with match title
  #Send GET request : http://localhost:3000/posts/ID
  	Given I send GET request as following
    |	Fields			|	Values			|
    |	path param	|	/posts			|
    |	title				|	QAEngineer	|
    |	author			|							|
    |	id					|							|
    Then I should receive response with some data as following
    |	Fields			|	Values		|
    |	status code	|	200				|
    |	title				|	QAEngineer|
    |	author			|						|
    |	id					|						|

  @tag1
  Scenario: Send GET request with query parameters "title" and "id" to verify response is Array[] with all enteries with match title
  #Send GET request : http://localhost:3000/posts/ID
  	Given I send GET request as following
    |	Fields			|	Values			|
    |	path param	|	/posts			|
    |	title				|	QAEngineer	|
    |	author			|							|
    |	id					|	25					|
    Then I should receive response with some data as following
    |	Fields			|	Values		|
    |	status code	|	200				|
    |	title				|	QAEngineer|
    |	author			|						|
    |	id					|	25				|  
      
#  @tag1
#  Scenario: Send POST request : 
#    Given I send POST request with path param as "/posts" and query param as ""
#    Then I should see status code "200"
#    And I should recieve ID number

#    @tag1
#  Scenario: Send GET request to verify the previous POST request
#  #Send GET request : http://localhost:3000/posts/ID
#    Given I send GET request with path param as "/posts/19" and query param as ""
#    Then I should see author name as "MdF2"
#    And I should see status code "200"
    