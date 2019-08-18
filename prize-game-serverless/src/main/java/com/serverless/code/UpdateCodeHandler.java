package com.serverless.code;

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
import com.serverless.dal.Code;

public class UpdateCodeHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse>{
	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

      try {
          
          // get the 'pathParameters' from input
          Map<String,String> pathParameters =  (Map<String,String>)input.get("pathParameters");
          String codeId = pathParameters.get("id");

          // get the Code by id
          Code code = new Code().get(codeId);
          if (code != null) {
        	  // get the 'body' from input
              JsonNode body = new ObjectMapper().readTree((String) input.get("body"));

              	// update the Code object for post
    			if (body.get("prize_code") != null)
    				code.setPrize_code(body.get("prize_code").asText());
    			if (body.get("prize_time") != null)
    				code.setPrize_time(body.get("prize_time").asText());
    			if (body.get("user_id") != null)
    				code.setUser_id(body.get("user_id").asText());
    			if (body.get("prize_type") != null)
    				code.setPrize_type(body.get("prize_type").asText());

              code.update(code);

              // send the response back
          		return ApiGatewayResponse.builder()
          				.setStatusCode(200)
          				.setObjectBody(code)
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
                  .setObjectBody("Code with id: '" + codeId + "' not found.")
          				.setHeaders(new HashMap<String, String>() {
    						{
    							put("X-Powered-By", "AWS Lambda & Serverless");
    							put("Access-Control-Allow-Origin", "*");
    							put("Access-Control-Allow-Credentials", "true");
    						}
    					}).build();
            }        

      } catch (Exception ex) {
          logger.error("Error in updating code: " + ex);

          // send the error response back
    			Response responseBody = new Response("Error in updating code: " + ex, input);
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
