package stepdefs;

import static org.junit.Assert.*;

import java.util.Map;

import com.api.model.CreateProductRequest;
import io.cucumber.java.en.And;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.api.model.ProductDTO;
import utils.JsonReader;
import utils.ResponseHandler;
import utils.TestContext;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateProductStepDefinition {
	ProductDTO productDTO;
	Map<String,String> bookingData;
	private TestContext context;
	private static final Logger LOG = LogManager.getLogger(CreateProductStepDefinition.class);

	public CreateProductStepDefinition(TestContext context) {
		this.context = context;
	}

	@When("user sends a request to create a product")
	public void userSendsARequestToCreateAProduct(DataTable dataTable) {
		bookingData = dataTable.asMaps().get(0);
		CreateProductRequest productBody = new CreateProductRequest();
		productBody.setName(bookingData.get("name"));
		productBody.setType(bookingData.get("type"));
		productBody.setDescription(bookingData.get("description"));
		productBody.setCategory(bookingData.get("category"));
		productBody.setImageUrl(bookingData.get("image_url"));
		productBody.setHomeUrl(bookingData.get("home_url"));
		context.response = context.requestSetup().body(productBody)
				.when().post(context.session.get("endpoint").toString());
		productDTO = ResponseHandler.deserializedResponse(context.response, ProductDTO.class);
		assertNotNull("Booking not created", productDTO);
		LOG.info("Newly created booking ID: "+productDTO.getId());
		context.session.put("productID", productDTO.getId());

	}

	@And("user validates the response body against input message")
	public void userValidatesTheResponseBodyAgainstInputMessage() {
		validateBookingData(new JSONObject(bookingData), productDTO);
	}


	private void validateBookingData(JSONObject bookingData, ProductDTO productDTO) {
		LOG.info(bookingData);
//		assertNotNull("Booking ID missing", productDTO.getId());
//		assertEquals("First Name did not match", bookingData.get("firstname"), productDTO.getBooking().getFirstname());
//		assertEquals("Last Name did not match", bookingData.get("lastname"), bookingDTO.getBooking().getLastname());
//		assertEquals("Total Price did not match", bookingData.get("totalprice"), bookingDTO.getBooking().getTotalprice());
//		assertEquals("Deposit Paid did not match", bookingData.get("depositpaid"), bookingDTO.getBooking().getDepositpaid());
//		assertEquals("Additional Needs did not match", bookingData.get("additionalneeds"), bookingDTO.getBooking().getAdditionalneeds());
//		assertEquals("Check in Date did not match", bookingData.get("checkin"), bookingDTO.getBooking().getBookingdates().getCheckin());
//		assertEquals("Check out Date did not match", bookingData.get("checkout"), bookingDTO.getBooking().getBookingdates().getCheckout());

		assertNotNull("Booking ID missing", productDTO.getId());
		assertEquals("First Name did not match", bookingData.get("name"), productDTO.getName());
//		assertEquals("Last Name did not match", bookingData.get("type"), bookingDTO.getBooking().getLastname());
//		assertEquals("Total Price did not match", bookingData.get("description"), bookingDTO.getBooking().getTotalprice());
//		assertEquals("Deposit Paid did not match", bookingData.get("category"), bookingDTO.getBooking().getDepositpaid());
//		assertEquals("Additional Needs did not match", bookingData.get("image_url"), bookingDTO.getBooking().getAdditionalneeds());
//		assertEquals("Check in Date did not match", bookingData.get("home_url"), bookingDTO.getBooking().getBookingdates().getCheckin());
	}


	@Then("user validates the response with JSON schema from Excel")
	public void userValidatesTheResponseWithJSONSchemaFromExcel() {
		context.response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(((Map<String,String>) context.session.get("excelDataMap")).get("responseSchema")));
		LOG.info("Successfully Validated schema from Excel");
	}

	@When("user creates a product using data {string} from JSON file {string}")
	public void userCreatesABookingUsingDataFromJSONFile(String dataKey, String JSONFile) {
		context.response = context.requestSetup().body(JsonReader.getRequestBody(JSONFile,dataKey))
				.when().post(context.session.get("endpoint").toString());

		ProductDTO productDTO = ResponseHandler.deserializedResponse(context.response, ProductDTO.class);
		assertNotNull("Product not created", productDTO);
		LOG.info("Newly created product ID: "+productDTO.getId());
		context.session.put("productID", productDTO.getId());

	}
}
