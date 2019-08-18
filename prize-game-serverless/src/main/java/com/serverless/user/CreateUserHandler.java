package com.serverless.user;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.ApiGatewayResponse;
import com.serverless.Response;
import com.serverless.dal.User;

public class CreateUserHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

		try {
			// get the 'body' from input
			JsonNode body = new ObjectMapper().readTree((String) input.get("body"));

			// create the User object for post
			User user = new User();
			// user.setId(body.get("id").asText());
			user.setEmail(body.get("email").asText());
			user.setName(body.get("name").asText());
			user.setPassword(body.get("password").asText());
			if (body.get("phone") != null)
				user.setPhone(body.get("phone").asText());
			if (body.get("address") != null)
				user.setAddress(body.get("address").asText());
			user.setUser_role(body.get("user_role").asText());

			user.save(user);

			// send the response back
			return ApiGatewayResponse.builder().setStatusCode(200).setObjectBody(user)
					.setHeaders(new HashMap<String, String>() {
						{
							put("X-Powered-By", "AWS Lambda & Serverless");
							put("Access-Control-Allow-Origin", "*");
							put("Access-Control-Allow-Credentials", "true");
						}
					}).build();

		} catch (Exception ex) {
			logger.error("Error in saving user: " + ex);

			// send the error response back
			Response responseBody = new Response("Error in saving user: " + ex.getMessage(), input);
			return ApiGatewayResponse.builder().setStatusCode(500).setObjectBody(responseBody)
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
