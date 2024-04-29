package StepDefinition;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import BaseSteps.BaseStep;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class FakeApiSteps {
	JSONObject obj = new JSONObject();
	RequestSpecification req;
	Response res;
	JsonPath data;
	String token;
	String expectedString;
	int expectedInt;
	Properties prop = new Properties();
	InputStream input = null;
	int num=1;
	
	BaseStep base=new BaseStep();
	
	@Before
	@Given("user have access to the Activities API Url")
	public void user_have_access_to_the_activities_api_url() throws IOException {
		
		
		input = new FileInputStream("C:\\Users\\SIDKISHO\\Desktop\\project\\TestingVehicleManagement 1\\TestingProj\\src\\test\\resources\\Properties\\Env.properties");
		prop.load(input);
//		RestAssured.baseURI = prop.getProperty("Base_URL_FAKEAPI");
//		req = RestAssured.given();
		
		base.setBaseURL(prop.getProperty("Base_URL_FAKEAPI"));
		
		System.out.println("---Background Satisfied User has access to activities api---");
	    
	}
/*----------------Scenario 1-----------------Created By Siddharth Kishore-----------------*/
	
	@When("user sends request to get Endpoint with {string}")
	public void user_sends_request_to_get_endpoint_with_id(String id) {
	   String endpoint=prop.getProperty("get_activity_by_id");
//	   res=req.get(endpoint+id);
	   res=base.getRequest(endpoint+id);
	   expectedString = id;
	}
	
	@Then("the response should contain details of the activity")
	public void the_response_should_contain_details_of_the_activity() {
		data=res.jsonPath();
		
		System.out.println(res.asPrettyString());
		Assert.assertEquals(res.getStatusCode(),200);
		String str="Activity "+ (num++) ;
		System.out.println(num);
		Assert.assertEquals(data.getString("id"), expectedString);
		System.out.println("Results = \n "+data.getString("title") +" <--> "+str);
		System.out.println("----------------------------------------------");

	}
/*-----------------------------------------------------------------------------------------*/
	
/*---------------------------Scenario 2-----------------Created By Siddharth Kishore-------*/
	@When("user sends request to post url with {string} and {string} and {string} and {string}")
	public void user_sends_request_to_post_url_with_and_and_and(String string1, String string2, String string3, String string4) {
		obj=new JSONObject();
	    obj.put("id", Integer.parseInt(string1));
		obj.put("title", string2);
		obj.put("dueDate", string3);
		obj.put("completed", Boolean.parseBoolean(string4));
		String endpoint = prop.getProperty("post_activities");
		//req.contentType(ContentType.JSON);		
//		res = req.body(obj.toJSONString()).post(endpoint);
		res=base.postRequest(endpoint, obj.toJSONString());
	}

	
	
	@Then("the response should contain details of the newly created activity")
	public void the_response_should_contain_details_of_the_newly_created_activity() {
		data = res.jsonPath();
		System.out.println(res.asPrettyString());
	    Object id = data.get("id");
	    Assert.assertNotNull(id);
	}
/*-----------------------------------------------------------------------------------------*/

/*---------------------------Scenario 3-----------------Created By Siddharth Kishore-------*/
//put positive
	@When("user sends request to update Url with data")
	public void user_sends_request_to_update_url_with_data(io.cucumber.datatable.DataTable dataTable) {
		List<List<String>> response = dataTable.asLists(String.class);
		String id = response.get(0).get(0);
		String title = response.get(0).get(1);
		String dueDate = response.get(0).get(2);
		String completed = response.get(0).get(3);
		obj = new JSONObject();
		obj.put("id", Integer.parseInt(id));
		obj.put("title", title);
		obj.put("dueDate", dueDate);
		obj.put("completed", Boolean.parseBoolean(completed));
		expectedInt = Integer.parseInt(id);
		expectedString = title;
		String endpoint1 = prop.getProperty("put_activity");
//		req.contentType(ContentType.JSON);		
//		res = req.body(obj.toJSONString()).put(endpoint1+id);
		res=base.putRequest(endpoint1+id, obj.toJSONString());
		System.out.println(res.asPrettyString());
	}
	@Then("the response should contain updated Activity")
	public void the_response_should_contain_updated_activity() {
		data = res.jsonPath();
//		System.out.println(data);
		Assert.assertEquals(expectedString, data.getString("title"));
	}
/*-----------------------------------------------------------------------------------------*/

/*-------------------------Scenario 4-----------------Created By Siddharth Kishore---------*/
//Delete positive
	@When("user send request to delete endpoint with id")
	public void user_send_request_to_delete_endpoint_with_id(io.cucumber.datatable.DataTable dataTable) {
		List<List<String>> response = dataTable.asLists(String.class);
		String id = response.get(0).get(0);
		expectedInt = Integer.parseInt(id);
		String endpoint = prop.getProperty("del_activity");
//		res = req.delete(endpoint+expectedInt);
		res=base.deleteRequest(endpoint);
	}
	@Then("the activity with that ID should no longer exist")
	public void the_activity_with_that_id_should_no_longer_exist() {
	    Assert.assertEquals(res.getStatusCode(),200,"Activity deleted successfully");
	}
/*-----------------------------------------------------------------------------------------*/

/*-------------------------Scenario 5-----------------Created By Siddharth Kishore--------*/
//Delete negative
	@When("user send request to delete endpoint with id that not exists")
	public void user_send_request_to_delete_endpoint_with_id_that_not_exists(io.cucumber.datatable.DataTable dataTable) {
		List<List<String>> response = dataTable.asLists(String.class);
		String id = response.get(0).get(0);
		expectedInt = Integer.parseInt(id);
		String endpoint = prop.getProperty("del_activity");
//		res = req.delete(endpoint+expectedInt);
		res=base.deleteRequest(endpoint+expectedInt);
	}
	@Then("response should contain an error message")
	public void response_should_contain_an_error_message() {
	    Assert.assertEquals(res.getStatusCode(),404,"200 status code with invalid id");
	}
	
	
/*-----------------------------------------------------------------------------------------*/
}
