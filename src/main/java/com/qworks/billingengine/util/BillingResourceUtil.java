package com.qworks.billingengine.util;

import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.qworks.billingengine.response.DataResponse;

public class BillingResourceUtil {
	public static HttpResponseMessage createBadRequestResponse(HttpRequestMessage<String> request, String message) {
		return createResponse(request, HttpStatus.BAD_REQUEST, message, "FAILURE");
	}

	public static HttpResponseMessage createOkResponse(HttpRequestMessage<String> request, DataResponse dataResponse) {
		return createResponse(request, HttpStatus.OK, dataResponse, "SUCCESS");
	}

	public static HttpResponseMessage createErrorResponse(HttpRequestMessage<String> request, String errorMessage) {
		return createResponse(request, HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, "FAILURE");
	}

	private static HttpResponseMessage createResponse(HttpRequestMessage<String> request, HttpStatus status,
			Object data, String message) {
		return request.createResponseBuilder(status)
				//.body(createDataResponse(data, message, status == HttpStatus.OK ? "SUCCESS" : "FAILURE"))
				.body(data)
				.header("Content-Type", "application/json").build();
	}

	public static DataResponse createDataResponse(Object data, String message, String status) {
		return DataResponse.builder().data(data).message(message).status(status).build();
	}
}


