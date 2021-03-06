package com.serverless.user;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.serverless.ApiGatewayResponse;
import com.serverless.Response;
import com.serverless.dal.User;

public class ListUsersHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
    try {
        // get all users
        List<User> users = new User().list();

        // send the response back
        return ApiGatewayResponse.builder()
    				.setStatusCode(200)
    				.setObjectBody(users)
    				.setHeaders(new HashMap<String, String>() {
						{
							put("X-Powered-By", "AWS Lambda & Serverless");
							put("Access-Control-Allow-Origin", "*");
							put("Access-Control-Allow-Credentials", "true");
						}
					}).build();
    } catch (Exception ex) {
        logger.error("Error in listing users: " + ex);

        // send the error response back
  			Response responseBody = new Response("Error in listing users: " + ex, input);
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
