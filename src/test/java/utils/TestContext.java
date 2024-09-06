package utils;

import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import com.github.dzieciou.testing.curl.Options;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
		
	public Response response;
	public Map<String, Object> session = new HashMap<String, Object>();
	private static final String CONTENT_TYPE = PropertiesFile.getProperty("content.type");


	public RequestSpecification requestSetup() {
		RestAssured.reset();
		Options options = Options.builder().logStacktrace().build();
		RestAssuredConfig config = CurlRestAssuredConfigFactory.createConfig(options); 
		RestAssured.baseURI = PropertiesFile.getProperty("baseURL");
		try {
			RequestSpecification httpRequest = RestAssured.given();
			httpRequest.header("Authorization", TokenGenerator.accessToken());
			httpRequest.header("Accept", "application/json");
			httpRequest.config(config);
			httpRequest.filter(new RestAssuredRequestFilter());
			httpRequest.contentType(CONTENT_TYPE);
			return httpRequest;
		} catch (OAuthSystemException | OAuthProblemException e) {
			throw new RuntimeException(e);
		}

	} 
}
