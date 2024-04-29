package StepDefinition;
 
import java.util.List;
 
import org.json.simple.JSONObject;
 
import org.testng.Assert;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
 
import io.cucumber.java.en.Then;
 
import io.cucumber.java.en.When;
 
import io.restassured.RestAssured;
 
import io.restassured.path.json.JsonPath;
 
import io.restassured.response.Response;
 
import io.restassured.specification.RequestSpecification;
 
public class VehicleManagemenSteps
 
{
 
	RequestSpecification req;
 
	Response res;
 
	JSONObject emp;
 
	JsonPath jp;
 
	String expected;
 
	Response getRes;
 
	String vehicleId;
 
	/*-----------------------------------Background------------------------------------*/
	@Before
	@Given("The user is on URL")
 
	public void the_user_is_on_url()
 
	{
 
	    RestAssured.baseURI="http://localhost:9191";
 
	    System.out.println("background");
 
	}
 
	/*---------------------------------Scenario-1--Created By Siddharth Kishore-------------*/
 
	@When("The user is on the endpoint1")
	public void the_user_is_on_the_endpoint1()
	{
		req=RestAssured.given();
		res=req.get("/api/getAllVehicles");
	    System.out.println("When endpoint1");
	}
	@Then("the user should get a list of vehicles.")
	public void the_user_should_get_a_list_of_vehicles()
	{
	System.out.println(res.getStatusCode());

		System.out.println("Then Step");
 
	}
 
	/*-------------------------------Scenario-2-----------------------------------------------*/
 
	@Given("The user is on the URL endpoint2")
 
	public void the_user_is_on_the_url_endpoint2()
 
	{   System.out.println("scenario 2 starting");
 
		req=RestAssured.given();
 
		System.out.println("Given endpoint2");
 
	}
 
	@When("The user enters the  vehicleID")
 
	public void the_user_enters_the_vehicle_id() {
 
		System.out.println("before header");
 
		res=req.get("/api/getVehicle/103");
 
		System.out.println("When Step");
 
	}
 
	@Then("the user should get the vehicle with that vehicleId.")
 
	public void the_user_should_get_the_vehicle_with_that_vehicle_id()
 
	{
 
		System.out.println(res.asString());
 
		//System.out.println("Then Step");
 
		System.out.println("scenario 2 ending");
 
	}
 
	/*------------------------------------Scenario-3--------------------------------------------*/
 
	@Given("The user is on the URL endpoint3")
 
	public void the_user_is_on_the_url_endpoint3()
 
	{
 
		req=RestAssured.given();
 
		System.out.println("scenario 3 starting");
 
	}
 
	public void assertEquals(String string, Object errorMessage) {
 
		// TODO Auto-generated method stub
 
	}
 
	public Object errorMessage(String string, String errorMessage) {
 
		// TODO Auto-generated method stub
 
		return null;
 
	}
 
	public JsonPath given() {
 
		// TODO Auto-generated method stub
 
		return null;
 
	}
 
	@When("The user enters the {string} and {string} and {string} and {string}")
 
	public void the_user_enters_the_and_and_and(String string, String string2, String string3, String string4)
 
	{
 
		vehicleId = string;
 
		emp = new JSONObject();
 
//		System.out.println("string2--->"+string2);
 
//		System.out.println("string--->"+string);
 
		emp.put("id", string);
 
		emp.put("model", string2);
 
		emp.put("cost", string3);
 
		emp.put("company", string4);
 
	    expected = string2;
 
	    System.out.println("------->" + expected);
 
		System.out.println("When Step");
 
	}
 
	@When("makes a POST request to the API")
 
	public void makes_a_post_request_to_the_api()
 
	{
 
		req.header("Content-Type","application/json");	
 
		req.body(emp.toJSONString());
 
		res = req.post("/api/addVehicle");
 
		System.out.println("When Step");
 
	}
 
	@Then("new vehicle should be created")
 
	public void new_vehicle_should_be_created()
 
	{   System.out.println("entering");
 
	    System.out.println(res.asPrettyString());
 
		jp = res.jsonPath();
 
	    List<String> actual = jp.getList("model");
 
	    int size= actual.size();
 
	    System.out.println(jp);
 
	    System.out.println("new vehicle result--------->" + actual);
 
	    Assert.assertEquals( expected,actual.get(size-1));
 
		System.out.println("Then Step");
 
		System.out.println("scenario 3 ending");
 
	}
 
	/*------------------------------------Scenario-4--------------------------------------------------------*/
 
	@Given("The user is on the URL endpoint4")
 
	public void the_user_is_on_the_url_endpoint4()
 
	{
 
		req=RestAssured.given();
 
		System.out.println("scenario 4 starting");
 
		System.out.println("Given endpoint4");
 
	}
 
	@When("The user enters the updated vehicle data")
 
	public void the_user_enters_the_updated_vehicle_data(io.cucumber.datatable.DataTable dataTable)
 
	{
 
		 List<List<String>> vehicle = dataTable.asLists(String.class);
 
		    String id = vehicle.get(0).get(0);
 
		    String model = vehicle.get(0).get(1);
 
		    String cost = vehicle.get(0).get(0);
 
		    String company = vehicle.get(0).get(1);
 
		    emp = new JSONObject();
 
		    emp.put("id", id);
 
			emp.put("model", model);
 
			emp.put("cost", cost);
 
			emp.put("company", company);
 
		    expected = model;
 
		System.out.println("When Step");
 
	}
 
	@When("makes a PUT request to the API")
 
	public void makes_a_put_request_to_the_api()
 
	{
 
		req.header("Content-Type","application/json");		
 
		res = req.body(emp.toJSONString()).put("/api/Sprintboot-rest-demo/updateVehicle/103");
 
		System.out.println("When Step");
 
	}
 
	@Then("the vehicle details should be updated.")
 
	public void the_vehicle_details_should_be_updated()
 
	{
 
		jp = res.jsonPath();
 
		System.out.println(res.asPrettyString());
 
	    String actual = jp.getString("model[0]");
 
	    System.out.println("Zero index"+jp.getString("model[0]"));
 
	    System.out.println("First index"+jp.getString("model[1]"));
 
	    System.out.println("---------->"+ actual);
 
	    System.out.println("---------->"+expected);
 
	    Assert.assertEquals(actual, expected);
 
		System.out.println("Then Step");
 
	}
 
	/*-----------------------------------Scenario-5---------------------------------------------------*/
 
	@Given("The user is on the URL endpoint5")
 
	public void the_user_is_on_the_url_endpoint5()
 
	{
 
		req=RestAssured.given();
 
		System.out.println("Given endpoint5");
 
	}
 
	@When("user makes a DELETE request to the API")
 
	public void makes_a_delete_request_to_the_api()
 
	{
 
		res=req.delete("/api/deleteVehicle/104");
 
		System.out.println("When Step");
 
	}
 
	@Then("the particular vehicle should be deleted.")
 
	public void the_particular_vehicle_should_be_deleted()
 
	{
 
	   System.out.println(res.statusCode());
 
	   System.out.println("Then Step");
 
	}
 
     /*
 
----- -- -------------------Scenario6--------------------------------------
 
      */
 
	@Given("The user is on the URL endpoint6")
 
	public void the_user_is_on_the_url_endpoint6() {
 
		req=RestAssured.given();
 
		System.out.println("Given endpoint6");
 
	    // Write code here that turns the phrase above into concrete actions
 
	}
 
	@When("users makes a DELETE request to the API")
 
	public void users_makes_a_delete_request_to_the_api() {
 
		res=req.delete("/api/deleteVehicle/104");
 
		System.out.println("When Step");
 
	    // Write code here that turns the phrase above into concrete actions
 
	    //throw new io.cucumber.java.PendingException();
 
	}
 
	@Then("the particular vehicle should not be deleted.")
 
	public void the_particular_vehicle_should_not_be_deleted() {
 
		System.out.println(res.statusCode());
 
		   System.out.println("Then Step");
 
	    // Write code here that turns the phrase above into concrete actions
 
	    //throw new io.cucumber.java.PendingException();
 
	}
 
}