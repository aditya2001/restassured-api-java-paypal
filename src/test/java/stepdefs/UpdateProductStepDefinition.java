package stepdefs;

import java.util.Map;

import com.api.model.ProductDTO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import utils.JsonReader;
import utils.TestContext;

import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;

public class UpdateProductStepDefinition {
	private TestContext context;
	ProductDTO productDTO;
	private static final Logger LOG = LogManager.getLogger(UpdateProductStepDefinition.class);
	
	public UpdateProductStepDefinition(TestContext context) {
		this.context = context;
	}

	@When("user creates a auth token with credential {string} & {string}")
	public void userCreatesAAuthTokenWithCredential(String username, String password) {
		JSONObject credentials = new JSONObject();
		credentials.put("username", username);
		credentials.put("password", password);
		context.response = context.requestSetup().body(credentials.toString())
				.when().post(context.session.get("endpoint").toString());
		String token = context.response.path("token");
		LOG.info("Auth Token: "+token);
		context.session.put("token", "token="+token);	
	}

	@When("user sends a request to update the details of a product")
	public void userUpdatesAProduct(DataTable dataTable) {
		Map<String,String> productData = dataTable.asMaps().get(0);
		JSONObject bookingBody = new JSONObject();
		bookingBody.put("op", productData.get("op"));
		bookingBody.put("path", productData.get("path"));
		bookingBody.put("value", productData.get("value"));
		JSONArray js = new JSONArray();
		js.put(0, bookingBody);


		context.response = context.requestSetup()
//				.header("Cookie", context.session.get("token").toString())
				.pathParam("productID", context.session.get("productID"))
				.body(js.toString())
				.when().patch(context.session.get("endpoint")+"/{productID}");

//		productDTO = ResponseHandler.deserializedResponse(context.response, ProductDTO.class);
//		assertNotNull("Product not created", productDTO);
	}



	@When("user sends a request to update product details using data {string} from JSON file {string}")
	public void userUpdatesTheProductDetailsUsingDataFromJSONFile(String dataKey, String JSONFile) {
		context.response = context.requestSetup()
//				.header("Cookie", context.session.get("token").toString())
				.pathParam("productID", context.session.get("productID"))
				.body(JsonReader.getRequestBody(JSONFile,dataKey))
				.when().put(context.session.get("endpoint")+"/{productID}");


//		productDTO = ResponseHandler.deserializedResponse(context.response, BookingDetailsDTO.class);
//		assertNotNull("Product not created", productDTO);
	}

}
