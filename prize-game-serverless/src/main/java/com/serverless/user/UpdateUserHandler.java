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

public class UpdateUserHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

      try {
          
          // get the 'pathParameters' from input
          Map<String,String> pathParameters =  (Map<String,String>)input.get("pathParameters");
          String userId = pathParameters.get("id");

          // get the User by id
          User user = new User().get(userId);
          if (user != null) {
        	  // get the 'body' from input
              JsonNode body = new ObjectMapper().readTree((String) input.get("body"));

              	// update the User object for post
              	if (body.get("email") != null)
              		user.setEmail(body.get("email").asText());
    			if (body.get("name") != null)
    				user.setName(body.get("name").asText());
    			if (body.get("password") != null)
    				user.setPassword(body.get("password").asText());
    			if (body.get("phone") != null)
    				user.setPhone(body.get("phone").asText());
    			if (body.get("address") != null)
    				user.setAddress(body.get("address").asText());
    			if (body.get("user_role") != null)
    				user.setUser_role(body.get("user_role").asText());

              user.update(user);

              // send the response back
          		return ApiGatewayResponse.builder()
          				.setStatusCode(200)
          				.setObjectBody(user)
          				.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
          				.build();

            } else {
              return ApiGatewayResponse.builder()
          				.setStatusCode(404)
                  .setObjectBody("User with id: '" + userId + "' not found.")
          				.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
          				.build();
            }        

      } catch (Exception ex) {
          logger.error("Error in updating user: " + ex);

          // send the error response back
    			Response responseBody = new Response("Error in updating user: " + ex, input);
    			return ApiGatewayResponse.builder()
    					.setStatusCode(500)
    					.setObjectBody(responseBody)
    					.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
    					.build();
      }
	}
}

