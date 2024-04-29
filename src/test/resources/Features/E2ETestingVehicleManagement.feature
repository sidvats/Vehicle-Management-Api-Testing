Feature: To check the functionality of VehicleManagement Application
Background: URL
Given The user is on URL
Scenario: To get the vehicles list
	When The user is on the endpoint1
	Then the user should get a list of vehicles.
@GET_Vehicle_By_Id
Scenario: To get an user by vehicleID
	Given The user is on the URL endpoint2
	When The user enters the  vehicleID
	Then the user should get the vehicle with that vehicleId.
@Post_New_Vehicle  
Scenario Outline: To add a new vehicle
	Given The user is on the URL endpoint3
	When The user enters the "<ID>" and "<model>" and "<cost>" and "<company>"
	And makes a POST request to the API 
	Then new vehicle should be created
Examples:
| ID   | model      | cost      | company     |
| 100  | Alto       | 650000    | Maruti      |
| 104  | Xylo       | 950000    | Honda        |
@Put_Vehicle    
Scenario: To update the details of an existing vehicle
	Given The user is on the URL endpoint4
	When The user enters the updated vehicle data
	| 104 | | challenger | 700000 | dodge
	And makes a PUT request to the API 
	Then the vehicle details should be updated.
@Delete_Vehicle_positive    
Scenario: To delete a vehicle by vehicleId
	Given The user is on the URL endpoint5
	When user makes a DELETE request to the API
	Then the particular vehicle should be deleted.
#@Delete_Vehicle_negative
#Scenario: To delete a Vehicle by vehicleId
#	Given The user is on the URL endpoint6
#	When users makes a DELETE request to the API
#	Then the particular vehicle should not be deleted.