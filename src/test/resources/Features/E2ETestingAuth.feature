Feature: To Ensure User Authenticates and Authorizes Sucessfully
Background:
    Given User is on the login page
    When User enter valid username and password 
    		| siddharth | Test@123 |

  Scenario: Generating a Bearer Token
    And User clicks the generate Token endpoint
    Then token is received successfully 

  Scenario: Authorizing a user
    And hits authorize endpoint with token
    Then User is authorised response is received as Boolean

	Scenario: Creating a user
		When user enters credentials
		Then user is created


 