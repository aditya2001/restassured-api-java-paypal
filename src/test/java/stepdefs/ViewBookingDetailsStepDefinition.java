package stepdefs;

import static org.junit.Assert.*;

import com.api.model.ProductDTO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import utils.ResponseHandler;
import utils.TestContext;

import io.cucumber.java.en.*;
import io.restassured.module.jsv.JsonSchemaValidator;

public class ViewBookingDetailsStepDefinition {
	private TestContext context;
	private static final Logger LOG = LogManager.getLogger(ViewBookingDetailsStepDefinition.class);
	
	public ViewBookingDetailsStepDefinition(TestContext context) {
		this.context = context;
	}

	@Given("user has access to endpoint {string}")
	public void userHasAccessToEndpoint(String endpoint) {		
		context.session.put("endpoint", endpoint);
	}

//	@Given("user has generated access token")
//	public void userHasGeneratedAccessToken() {
//		TokenGenerator;
//	}


	@Then("user receives the response code {int}")
	public void userShouldGetTheResponseCode(Integer statusCode) {
		assertEquals(Long.valueOf(statusCode), Long.valueOf(context.response.getStatusCode()));
	}


	@Then("user sends a request to view details of a product ID")
	public void userSendsARequestToViewDetailsOfProductID() {
		LOG.info("Session ProductID: "+context.session.get("productID"));
		context.response = context.requestSetup().pathParam("productID", context.session.get("productID"))
				.when().get(context.session.get("endpoint")+"/{productID}");
		ProductDTO productDTO = ResponseHandler.deserializedResponse(context.response, ProductDTO.class);
//		assertNotNull("Booking Details not found!!", bookingDetails);
//		context.session.put("firstname", bookingDetails.getFirstname());
//		context.session.put("lastname", bookingDetails.getLastname());
	}


	@Then("user validates the response with JSON schema {string}")
	public void userValidatesResponseWithJSONSchema(String schemaFileName) {
		context.response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/"+schemaFileName));
		LOG.info("Successfully Validated schema from "+schemaFileName);
	}
	

}
