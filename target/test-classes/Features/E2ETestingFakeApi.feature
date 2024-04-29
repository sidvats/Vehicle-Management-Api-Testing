Feature: End to End Testing Activities API(https://fakerestapi.azurewebsites.net/index.html)
Background:
	Given user have access to the Activities API Url
	
@Get_Positive
	Scenario Outline: Retrieve Specific activity by valid ID
		When user sends request to get Endpoint with "<id>" 
    Then the response should contain details of the activity
    Examples:
    |  id  |
    |  1 |
    |  2 |
    |  3 |
    
    
@Post_Positive
   Scenario Outline: Post a new activity
    When user sends request to post url with "<id>" and "<title>" and "<dueDate>" and "<completed>"
    Then the response should contain details of the newly created activity
    Examples:
    | id          | title         | dueDate                     | completed |
    | 101         | Hiking        | 2024-11-17T06:43:15.379Z  	| true      |
    | 102         | Cycling       | 2022-10-15T06:43:15.379Z  	| false     |
    | 103         | Swimming      | 2023-12-20T06:43:15.379Z    | true      |
    
    
@Put_Positive
	Scenario: Update Existing Activity
	When user sends request to update Url with data
	| 101         | Skating        | 2024-04-19T06:43:15.379Z | false      |
	Then the response should contain updated Activity
  
@Delete_Positive  
 	 Scenario: Delete activity by ID
   When user send request to delete endpoint with id
   | 2 |
   Then the activity with that ID should no longer exist
 
@Delete_Negative
  Scenario: Delete activity by ID that is not present
  When user send request to delete endpoint with id that not exists
  | 1003 |
  Then response should contain an error message  
