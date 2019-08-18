package com.serverless.code;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Map;

import org.apache.log4j.Logger;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.serverless.ApiGatewayResponse;
import com.serverless.Response;
import com.serverless.dal.Code;

public class InsertUserPrizeCodeHandler  implements RequestHandler<Map<String, Object>, ApiGatewayResponse>{
	
	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		
		try {
			logger.info("InsertPrize input: " + input);
	          
	          // get the mapped 'query' from input
			Map<String, String> queryParamMap = (Map<String, String>) input.get("queryStringParameters");
	          String prize_code =  queryParamMap.get("prize_code");
	          String user_id =  queryParamMap.get("user_id");

	          // get the Code by id
	          Code code = new Code().getCodeByPrize_code(prize_code);
	          
	          
	          if (code != null) {
	        	  
	        	  if(code.getUser_id() != null || code.getPrize_time() != null) {
	        		  
	        		  return ApiGatewayResponse.builder()
		          				.setStatusCode(403)
		                  .setObjectBody("Code with prize_code: '" + prize_code + "' already taken.")
		          				.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
		          				.build();
	        	  }
	        	  else {
	        		  
	        		  code.setUser_id(user_id);
	        		  code.setPrize_time((new Timestamp(System.currentTimeMillis())).toString());
	        	  }

	              code.updateConditional(code);

	              // send the response back
	          		return ApiGatewayResponse.builder()
	          				.setStatusCode(200)
	          				.setObjectBody(code)
	          				.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
	          				.build();

	            } else {
	              return ApiGatewayResponse.builder()
	          				.setStatusCode(404)
	                  .setObjectBody("Code with prize_code: '" + prize_code + "' not found.")
	          				.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
	          				.build();
	            }        

	      } catch (Exception ex) {
	          logger.error("Error in updating code: " + ex);

	          // send the error response back
	    			Response responseBody = new Response("Error in updating code: " + ex, input);
	    			return ApiGatewayResponse.builder()
	    					.setStatusCode(500)
	    					.setObjectBody(responseBody)
	    					.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & Serverless"))
	    					.build();
	      }
	}

}
