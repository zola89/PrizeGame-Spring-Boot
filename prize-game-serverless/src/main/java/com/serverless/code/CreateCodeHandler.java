package com.serverless.code;

import java.util.Collections;
import java.util.Map;

import org.apache.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.ApiGatewayResponse;
import com.serverless.Response;
import com.serverless.dal.Code;

public class CreateCodeHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {
	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

		try {
			// get the 'body' from input
			JsonNode body = new ObjectMapper().readTree((String) input.get("body"));

			// create the Code object for post
			Code code = new Code();
			// code.setId(body.get("id").asText());
			code.setPrize_code(body.get("prize_code").asText());
			if (body.get("prize_time") != null)
				code.setPrize_time(body.get("prize_time").asText());
			if (body.get("user_id") != null)
				code.setUser_id(body.get("user_id").asText());
			code.setPrize_type(body.get("prize_type").asText());

			code.save(code);

			// send the response back
			return ApiGatewayResponse.builder().setStatusCode(200).setObjectBody(code)
					.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless")).build();

		} catch (Exception ex) {
			logger.error("Error in saving code: " + ex);

			// send the error response back
			Response responseBody = new Response("Error in saving code: " + ex.getMessage(), input);
			return ApiGatewayResponse.builder().setStatusCode(500).setObjectBody(responseBody)
					.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless")).build();
		}
	}

}
