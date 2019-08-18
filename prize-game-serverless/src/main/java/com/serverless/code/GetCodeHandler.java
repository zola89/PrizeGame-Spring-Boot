package com.serverless.code;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.ApiGatewayResponse;
import com.serverless.Response;
import com.serverless.dal.Code;

public class GetCodeHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

    try {
        // get the 'pathParameters' from input
        Map<String,String> pathParameters =  (Map<String,String>)input.get("pathParameters");
        String codeId = pathParameters.get("id");

        // get the Code by id
        Code code = new Code().get(codeId);

        // send the response back
        if (code != null) {
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
        logger.error("Error in retrieving code: " + ex);

        // send the error response back
  			Response responseBody = new Response("Error in retrieving code: " + ex, input);
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

