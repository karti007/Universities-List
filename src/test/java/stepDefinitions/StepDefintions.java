package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.junit.Assert.*;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;


import static io.restassured.RestAssured.given;

public class StepDefintions {
	
	private String baseURL;
	private String searchEndpoint;
	private RequestSpecification reqSpec;
	private ResponseSpecification resSpec;
	private Response response;
	private String res;
	private JsonPath js; 
	
	@Before
	public void beforeScenario() throws IOException
	{
		Properties prop = new Properties();	
		FileInputStream fis = new FileInputStream("src//test//resources//global.properties");
		prop.load(fis);
		baseURL = prop.getProperty("baseURL");
		searchEndpoint = prop.getProperty("searchEndpoint");
	}
	
	
	@Given("User initiates call to {string}")
	public void user_initiates_call_to(String string) {

		reqSpec = new RequestSpecBuilder()
 					.setBaseUri(baseURL)
					.setContentType(ContentType.JSON)
					.setAccept(ContentType.JSON)
					.build();

		resSpec = new ResponseSpecBuilder()
					.expectStatusCode(200)
					.expectContentType(ContentType.JSON)
					.build();
								
	}
	
	
	@When("User makes the GET call for {string}")
	public void user_makes_the_get_call_for(String country) {
		response = given().spec(reqSpec).queryParam("country", country)
				  .when().get(searchEndpoint)
				  .then().spec(resSpec).extract().response();
		res = response.asString();
		js = new JsonPath(res);
	}
	
	
	@Then("API call is success with status code {int}")
	public void api_call_is_success_with_status_code(Integer int1) {
		assertNotEquals(js.getList("$").size(), 0);
		assertEquals(response.getStatusCode(),200);
	}
	

	@Then("Assert {string} to be equal to {string}")
	public void assert_to_be_equal_to(String key, String value) {
		assertEquals(js.get(key),value);
	}


	@Then("Search if University name {string} is present in the response")
	public void search_if_university_name_is_present_in_the_response(String universityName) {
		List<String> names = js.getList("name");
		assertTrue(names.contains(universityName));
	}

}
