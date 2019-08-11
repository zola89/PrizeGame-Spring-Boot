package com.serverless.user;

import java.util.Collections;
import java.util.Map;

import org.apache.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.ApiGatewayResponse;
import com.serverless.Response;
import com.serverless.dal.User;

public class ValidateUserHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

		try {
			
			// get the 'body' from input
	        JsonNode body = new ObjectMapper().readTree((String) input.get("body"));
	        
	        String email, password;
	        if(body != null) {
	        	email = body.get("email").asText();
	        	password = body.get("password").asText();
	        
	        } else
	        	throw new RuntimeException("Validate User request body must not be null!");
	        
	        
	        if(isNotEmptyOrNull(email) && isNotEmptyOrNull(password)) {
	        	logger.info("Validating user with email: " + email);
	        	
	        	User user = new User().validateUserWithEmail(email, password);
	        	
	        	if(user != null) {
	        		logger.debug("Validate User: user found.");

			        // send the error response back
					return ApiGatewayResponse.builder()
							.setStatusCode(200) // NOT_FOUND
							.setObjectBody(user)
							.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
							.build();
	        		
	        	} else {
	        		logger.warn("Validate User: credentials wrong.");

			        // send the error response back
					Response responseBody = new Response("User not found.", input);
					return ApiGatewayResponse.builder()
							.setStatusCode(404) // NOT_FOUND
							.setObjectBody(responseBody)
							.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
							.build();
	        		
	        	}
	        	
	        	
	        }
	        else {
	        	logger.error("Validate User: email and password must not be null.");

		        // send the error response back
				Response responseBody = new Response("Validate User: email and password must not be null.", input);
				return ApiGatewayResponse.builder()
						.setStatusCode(400) // BAD_REQUEST
						.setObjectBody(responseBody)
						.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
						.build();
	        }
	        
		} catch(Exception ex) {
			logger.error("Error in validating user: " + ex.getMessage());

	        // send the error response back
			Response responseBody = new Response("Error in validating user: " + ex.getMessage(), input);
			return ApiGatewayResponse.builder()
					.setStatusCode(500)
					.setObjectBody(responseBody)
					.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
					.build();
		}
	}
	
	private boolean isNotEmptyOrNull(String text) {
		return text != null && !text.isEmpty();
	}

}
