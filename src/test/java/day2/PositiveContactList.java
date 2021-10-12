package day2;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


public class PositiveContactList {
	String id;
  @Test(enabled=false,description="Getting Contact List")
  public void getContactInfo() {
	 given()
	  .when()
	  .get("http://3.13.86.142:3000/contacts")
	  .then()
	  .log()
	  .body()
	  .statusCode(200);
	  
  }
  @Test(enabled=false,description="Getting Contact List")
  public void getSpecificContactInfo() {
	 given()
	  .when()
	  .get("http://3.13.86.142:3000/contacts/60eba23e170734047659ad54")
	  .then()
	  .log()
	  .body()
	  .statusCode(200);
	  
  }
  @Test(enabled=false,description="Getting Contact List")
  public void getTimeForSpecificContactInfo() {
	Response res= given()
	  .when()
	  .get("http://3.13.86.142:3000/contacts/60eba23e170734047659ad54");
	System.out.println(res.getTime());
	 res .then()
	  .log()
	  .body()
	  .statusCode(200);
	  
  }
  @Test(enabled=true,description="Adding Contact List")
  public void addingContactInfo() {
	JSONObject details=new JSONObject();
	JSONObject loc=new JSONObject();
	JSONObject emp=new JSONObject();
	
	loc.put("city", "Pune");
	loc.put("country", "India");
	emp.put("jobTitle","Trainee");
	emp.put("company","LTI");
	details.put("firstName", "Sam");
	details.put("lastName", "Park");
	details.put("email", "Sam@gmail.com");
	details.put("location", loc);
	details.put("employer", emp);
	
	  
	ExtractableResponse<Response> ex=given()
			.header("content-Type", "application/json")
	 .body(details.toJSONString())
	 .when()
	 .post("http://3.13.86.142:3000/contacts")
	 .then()
	 .log()
	 .body()
	 .statusCode(200)
	 .extract();
	 //.path("_id");

	 id=ex.path("_id");
	 System.out.println(ex.path("_id"));
	 System.out.println(ex.path("firstName"));
	 System.out.println(ex.path("lastName"));
	 System.out.println(ex.path("location.city"));
	 System.out.println(ex.path("location.country"));
	 
	  
  }
  @Test(enabled=true,dependsOnMethods="addingContactInfo",description="Updating Contact List")
  public void updateContactInfo() {
	  
	JSONObject details=new JSONObject();
	JSONObject loc=new JSONObject();
	JSONObject emp=new JSONObject();
	
	loc.put("city", "Pune");
	loc.put("country", "India");
	emp.put("jobTitle","Trainee");
	emp.put("company","LTI");
	details.put("firstName", "Sam");
	details.put("lastName", "Park");
	details.put("email", "Sampark@gmail.com");
	details.put("location", loc);
	details.put("employer", emp);
	
	  
		given()
			.header("content-Type", "application/json")
	 .body(details.toJSONString())
	 .when()
	 .put("http://3.13.86.142:3000/contacts/"+id)
	 .then()
	 .log()
	 .body()
	 .statusCode(204);
		
  }
  @Test(enabled=true,description="Getting a specific Contact",dependsOnMethods="updateContactInfo")
  public void getSpecificContactInfo2() {
	 given()
	  .when()
	  .get("http://3.13.86.142:3000/contacts/"+id)
	  .then()
	  .log()
	  .body()
	  .statusCode(200);
	  
  }
  @Test(enabled=true,description="Deleting a specific Contact",dependsOnMethods="getSpecificContactInfo2")
  public void deletingSpecificContactInfo() {
	 given()
	  .when()
	  .delete("http://3.13.86.142:3000/contacts/"+id)
	  .then()
	  .log()
	  .body()
	  .statusCode(204);
	  
  }
  @Test(enabled=true,description="Veryfing whether a specific contact is deleted",dependsOnMethods="deletingSpecificContactInfo")
  public void getSpecificContactInfo3() {
	 given()
	  .when()
	  .get("http://3.13.86.142:3000/contacts/"+id)
	  .then()
	  .log()
	  .body()
	  .statusCode(404);
	  
  }
  
}
