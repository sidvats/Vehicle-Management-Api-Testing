package BaseSteps;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseStep {

	RequestSpecification req;
	Response res;
	JSONObject emp;
	JsonPath jp;
	public void setBaseURL(String baseurl) 
	{
		RestAssured.baseURI = baseurl;
		req=RestAssured.given();
		req.header("Content-Type", "application/json");
	}
   public Response getRequest(String endpoint)
   {
	   return req.get(endpoint);
   }
 
   public Response postRequest(String endpoint, String reqBody)
   {
	   req.body(reqBody);
	   return req.post(endpoint);
   }
 
   public Response putRequest(String endpoint,String reqBody)
   {
	   req.body(reqBody);
	   return req.put(endpoint);
   }
   public Response deleteRequest(String endpoint)
   {
	   return req.delete(endpoint);
   }

}
