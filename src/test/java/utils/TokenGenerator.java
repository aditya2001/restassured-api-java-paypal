package utils;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class TokenGenerator {


    public static String accessToken() throws OAuthSystemException, OAuthProblemException {

        String clientID = "AddIVf0W_n5AJthpgPdGu5t_q-9RfzcCbfcmRHskih8DhhgpwrFB2DNIBWGMt2wxToB6Knkg7Sbw_TnA";
        String clientSecret = "EOF2EwjptbnzwefJ0JYdg3xSWkUyCUGt8yNEFUzjEVEvTC96RkyyPMk74m_-tG5UQdkb3VQhQmhP6AQD";
        String tokenURL = "https://api-m.sandbox.paypal.com/v1/oauth2/token";

        String finalToken;
        String bearerValue;
        String encodedValue = getBase64Encoded(clientID, clientSecret);


        OAuthClient client = new OAuthClient(new URLConnectionClient());

        OAuthClientRequest request = OAuthClientRequest.tokenLocation(tokenURL)
                .setGrantType(GrantType.CLIENT_CREDENTIALS)
                .buildBodyMessage();

        request.addHeader("Authorization", "Basic " + encodedValue);

        OAuthJSONAccessTokenResponse oAuthResponse = client.accessToken(request, OAuth.HttpMethod.POST, OAuthJSONAccessTokenResponse.class);
        finalToken = oAuthResponse.getAccessToken();
        bearerValue = "Bearer " + finalToken;
        return bearerValue;


    }

    public static String getBase64Encoded(String id, String password){
        return Base64.getEncoder().encodeToString((id + ":" + password).getBytes(StandardCharsets.UTF_8));

    }
}



