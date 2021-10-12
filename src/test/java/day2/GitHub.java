package day2;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class GitHub {
	
	@BeforeTest
	public void beforeTest() {
		baseURI="https://api.github.com/user/repos";
		authentication=RestAssured.oauth2("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");
	}
  @Test(enabled=false)
  public void gettingAllRepository() {
	  given().auth()
	  .oauth2("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")
	  .when()
	  .get("https://api.github.com/user/repos")
	  .then()
	  .log()
	  .body()
	  .statusCode(200);
  }
  @Test(enabled=false)
  public void createRepository() {
	  JSONObject data=new JSONObject();
	  data.put("name", "RestAssuredCreations");
	  data.put("description","I am created by RestAssured Tool");
	  data.put("homepage","https://github.com/Samiksha199918");
	  
	  given()
	  .auth()
	  .oauth2("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")
	  .header("Content-Type","application/json")
	  .body(data.toJSONString())
	  .when()
	  .post("https://api.github.com/user/repos")
	  .then()
	  .log()
	  .body()
	  .statusCode(201)
	  .time(Matchers.lessThan(10000L),TimeUnit.MILLISECONDS);
	  
  }
  @Test(enabled=true)
  public void createRepository1() {
	  JSONObject data=new JSONObject();
	  data.put("name", "RestAssuredCreations1");
	  data.put("description","I am created by RestAssured Tool");
	  data.put("homepage","https://github.com/Samiksha199918");
	  
	  given()
	  .header("Content-Type","application/json")
	  .body(data.toJSONString())
	  .when()
	  .post()
	  .then()
	  .log()
	  .body()
	  .statusCode(201)
	  .time(Matchers.lessThan(10000L),TimeUnit.MILLISECONDS);
	  
  }
}
