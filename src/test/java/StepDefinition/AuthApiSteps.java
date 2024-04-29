package StepDefinition;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.testng.Assert;

import ExcelReader.Reader;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthApiSteps {
	
	JSONObject obj = new JSONObject();
	RequestSpecification req;
	Response res;
	JsonPath data;
	String token;
	String userName;
	String password;
	
/*------------------------------------------Background-------------------------------------*/
	@Before
	@Given("User is on the login page")
	public void user_is_on_the_login_page() {
		RestAssured.baseURI = "https://bookstore.toolsqa.com";   
	}
	
	@When("User enter valid username and password")
	public void user_enter_valid_username_and_password(io.cucumber.datatable.DataTable dataTable) {
	    List<List<String>> user=dataTable.asLists(String.class);
	    userName=user.get(0).get(0);
	    password=user.get(0).get(1);
	    obj.put("userName",userName);
	    obj.put("password", password);
	}
/*-------------------Scenario 1-----------Created By : Siddharth Kishore--------------------*/	
	
	@When("User clicks the generate Token endpoint")
	public void user_clicks_the_generate_token_endpoint() {
	    req=RestAssured.given().contentType(ContentType.JSON).body(obj.toJSONString());
	    System.out.println(obj);
	    res=req.post("Account/v1/GenerateToken");
	}
	
	@Then("token is received successfully")
	public void token_is_received_successfully() {
		data=res.jsonPath();
		token=data.getString("token");
		Assert.assertEquals(data.getString("status"),"Success");
		System.out.println(token);
	}
	/*-------------------Scenario 2-----------Created By : Siddharth Kishore--------------------*/	
	
	
	@When("hits authorize endpoint with token")
	public void hits_authorize_endpoint_with_token() {
	    req=RestAssured.given().contentType(ContentType.JSON).auth()
	    		.basic(userName, password)
	    		.header("Content-Type","application/json")
	    		.body(obj.toString());
	    		
	    res=req.post("Account/v1/Authorized");
	}
	@Then("User is authorised response is received as Boolean")
	public void user_is_authorised_response_is_received_as_boolean() {
		   System.out.println(res.asString());
		    Assert.assertEquals(res.asString(),"true" );
	}
	/*-------------------Scenario 3-----------Created By : Siddharth Kishore--------------------*/	
	@When("user enters credentials")
	public void user_enters_credentials() throws IOException {
		Reader excel = new Reader();
		JSONObject excelData = excel.getData();
	    String endpoint = "/Account/v1/User";
//	    res = base.postRequest(endpoint, excelData.toJSONString());
	    req = RestAssured.given();
	    req.header("Content-Type","application/json").body(excelData.toJSONString());
	    res = req.post(endpoint);
	    System.out.println(excelData);
	    System.out.println(res.asPrettyString());
	}
 
	@Then("user is created")
	public void user_is_created() {
		   data = res.jsonPath();
		    String username = data.getString("username");
		    Assert.assertEquals(username, "kishore");
	}

}
