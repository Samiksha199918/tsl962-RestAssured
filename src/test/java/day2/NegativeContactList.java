package day2;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class NegativeContactList {
  @Test
  public void recordNotFound() {
	  given()
	  .when()
	  .get("http://3.13.86.142:3000/contacts/5")
	  .then()
	  .log()
	  .body()
	  .statusCode(404);
  }
  @Test(enabled=true,description="Adding Contact with missing details")
  public void addingContactWithMissingParameters() {
	JSONObject details=new JSONObject();
	JSONObject loc=new JSONObject();
	JSONObject emp=new JSONObject();
	
	loc.put("city", "");
	loc.put("country", "India");
	emp.put("jobTitle","Trainee");
	emp.put("company","LTI");
	details.put("firstName","Sam");
	details.put("lastName", "Park");
	details.put("email", "Sam@gmail.com");
	details.put("location", loc);
	details.put("employer", emp);
	
	  
			String error=given()
			.header("content-Type", "application/json")
	 .body(details.toJSONString())
	 .when()
	 .post("http://3.13.86.142:3000/contacts")
	 .then()
	 .log()
	 .body()
	 .statusCode(400)
	 .extract()
	 .path("err");
Assert.assertTrue(error.contains("Validator failed for path `city"));
	 
  }
  @Test(enabled=true, description="adding contact with too many character")
  public void addingContactBigSize() {
	  JSONObject details = new JSONObject();
	  JSONObject loc = new JSONObject();
	  JSONObject emp = new JSONObject();
	  
	  loc.put("city", "MumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbaiMumbai");
	  loc.put("country", "India");
		emp.put("jobTitle","Trainee");
		emp.put("company","LTI");
		details.put("firstName","Sam");
		details.put("lastName", "Park");
		details.put("email", "Sam@gmail.com");
		details.put("location", loc);
		details.put("employer", emp);
	  
	  
	  String error = given()
	  .header("Content-Type", "application/json")
	  .body(details.toJSONString())
	  .when()
	  .post("http://3.13.86.142:3000/contacts")
	  .then()
	  .log()
	  .body()
	  .statusCode(400)
	  .extract()
	  .path("err");
	  Assert.assertTrue(error.contains("is longer than the maximum allowed length"));
  }
  
  @Test(enabled=true, description="adding contact with too many character")
  public void addingEmailWithMissingAnnotation() {
	  JSONObject details = new JSONObject();
	  JSONObject loc = new JSONObject();
	  JSONObject emp = new JSONObject();
	  
	  loc.put("city", "Mumbai");
	  loc.put("country", "India");
	  loc.put("country", "India");
		emp.put("jobTitle","Trainee");
		emp.put("company","LTI");
		details.put("firstName","Sam");
		details.put("lastName", "Park");
		details.put("email", "Samgmail.com");
		details.put("location", loc);
		details.put("employer", emp);
	  
	  String error = given()
	  .header("Content-Type", "application/json")
	  .body(details.toJSONString())
	  .when()
	  .post("http://3.13.86.142:3000/contacts")
	  .then()
	  .log()
	  .body()
	  .statusCode(400)
	  .extract()
	  .path("err");
	  Assert.assertTrue(error.contains("Validator failed for path `email` with value `Samgmail.com`"));
  }
}
