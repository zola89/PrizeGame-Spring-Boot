package com.serverless.user;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.serverless.ApiGatewayResponse;
import com.serverless.Response;
import com.serverless.dal.User;
import com.serverless.dal.User;

public class GetUserHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

    try {
        // get the 'pathParameters' from input
        Map<String,String> pathParameters =  (Map<String,String>)input.get("pathParameters");
        String userId = pathParameters.get("id");

        // get the User by id
        User user = new User().get(userId);

        // send the response back
        if (user != null) {
          return ApiGatewayResponse.builder()
      				.setStatusCode(200)
      				.setObjectBody(user)
      				.setHeaders(new HashMap<String, String>() {
						{
							put("X-Powered-By", "AWS Lambda & Serverless");
							put("Access-Control-Allow-Origin", "*");
							put("Access-Control-Allow-Credentials", "true");
						}
					}).build();
        } else {
          return ApiGatewayResponse.builder()
      				.setStatusCode(404)
              .setObjectBody("User with id: '" + userId + "' not found.")
      				.setHeaders(new HashMap<String, String>() {
						{
							put("X-Powered-By", "AWS Lambda & Serverless");
							put("Access-Control-Allow-Origin", "*");
							put("Access-Control-Allow-Credentials", "true");
						}
					}).build();
        }
    } catch (Exception ex) {
        logger.error("Error in retrieving user: " + ex);

        // send the error response back
  			Response responseBody = new Response("Error in retrieving user: " + ex, input);
  			return ApiGatewayResponse.builder()
  					.setStatusCode(500)
  					.setObjectBody(responseBody)
  					.setHeaders(new HashMap<String, String>() {
						{
							put("X-Powered-By", "AWS Lambda & Serverless");
							put("Access-Control-Allow-Origin", "*");
							put("Access-Control-Allow-Credentials", "true");
						}
					}).build();
    }
	}
}
