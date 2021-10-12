package day1;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class WeatherAPI {
  @Test(enabled=false,description="Getting weather information of a specific city")
  public void getWeather() {
	  RestAssured.given()//some pre-conditions like authentication
	  			 .when()//perform some steps
	  			 .get("https://api.openweathermap.org/data/2.5/weather?q=Pune&appid=97b6d29925e50838c1dc893a162eabf3")//url
	  			 .then()//some post conditions like verification
	  			 .log()//print data in console
	  			 .body()//print body
	  			 .statusCode(200);//status
  }
	  @Test(enabled=false,description="Getting weather information of a specific city")
	  public void getWeather2() {
		Response res=  RestAssured.given()//some pre-conditions like authentication
		  			 .when()//perform some steps
		  			 .get("https://api.openweathermap.org/data/2.5/weather?q=Pune&appid=97b6d29925e50838c1dc893a162eabf3");//url
		  		System.out.println(res.prettyPrint());
		  		System.out.println(res.getTime());
		  		System.out.println(res.getStatusCode());
		  		System.out.println(res.getContentType());//checking with the help of TestNG Assertion
	
		  		Assert.assertEquals(res.getStatusCode(),200);
	  }
	  @Test(enabled=false,description="Getting weather information of a specific city")
	  public void getWeather3() {
		  RestAssured.given()//some pre-conditions like authentication
		  .queryParam("q","Pune")
		  .queryParam("appid", "97b6d29925e50838c1dc893a162eabf3")
		  .when()//perform some steps
		  			 .get("https://api.openweathermap.org/data/2.5/weather")//url
		  			 .then()//some post conditions like verification
		  			 .log()//print data in console
		  			 .body()//print body
		  			 .statusCode(200);//status
	  }
	  @Test(description="Getting weather information of a specific city")
	  public void getWeather4() {
		  Map<String,String> param=new HashMap<String, String>();
		  param.put("q","Pune");	// Some Pre-condition like Authentication
		  param.put("appid", "97b6d29925e50838c1dc893a162eabf3");
		  RestAssured.given()//some pre-conditions like authentication
		  .queryParams(param)
		  .when()//perform some steps
		  			 .get("https://api.openweathermap.org/data/2.5/weather")//url
		  			 .then()//some post conditions like verification
		  			 .log()//print data in console
		  			 .body()//print body
		  			 .statusCode(200);//status
	  }
}
