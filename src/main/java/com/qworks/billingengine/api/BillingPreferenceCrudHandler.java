package com.qworks.billingengine.api;

import static com.qworks.billingengine.util.BillingResourceUtil.createBadRequestResponse;
import static com.qworks.billingengine.util.BillingResourceUtil.createErrorResponse;
import static com.qworks.billingengine.util.BillingResourceUtil.createOkResponse;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.qworks.billingengine.response.DataResponse;
/**
 * This Handler class provides crud operations for billing preferences.
 * @author Basha Shaik
 * @version 1.0
 * @since 03/28/2023
 */
public class BillingPreferenceCrudHandler extends FunctionInvoker<Object, DataResponse> {
	private final ExecutorService newFixedPool = Executors.newFixedThreadPool(10);
	/**
	 * create a preference in the billing system.
	 * 
	 * @param request The HTTP request message containing the create preference object.
	 * @param context The execution context for the function.
	 * @return An HTTP response message indicating the result of the create operation.
	 */
	@FunctionName("createPreference")
	public HttpResponseMessage createPreference(@HttpTrigger(name = "createPreference", methods = {
			HttpMethod.POST }, route = "billing/preference", authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<String> request,
			ExecutionContext context) {
		try {
			// Extract the body from the request object
			if (request.getBody() == null) {
				return createBadRequestResponse(request, "Please pass the Preference configuration");
			} else {
				// Otherwise, handle the request asynchronously using a CompletableFuture
				CompletableFuture<HttpResponseMessage> response = CompletableFuture
	                    .supplyAsync(() -> handleRequest(request.getBody(), context), newFixedPool)
	                    .thenApplyAsync((result) -> createOkResponse(request, result), newFixedPool)
	                    .exceptionally(throwable -> {
	                        return createErrorResponse(request,throwable.getMessage());
	                    });
	            return response.get();
			}
		} catch (Exception e) {
			// If an exception is thrown, return an error response with the corresponding error message
			return createErrorResponse(request, e.getMessage());
		}
	}


	

	/**
	 * fetch a preference in the billing system.
	 * 
	 * @param request The HTTP request message containing the ID of the preference to be retrieved.
	 * @param context The execution context for the function.
	 * @return An HTTP response message indicating the result of the get operation.
	 */
	@FunctionName("fetchPreference")
	public HttpResponseMessage fetchPreference(@HttpTrigger(name = "fetchPreference", methods = {
			HttpMethod.GET }, route = "billing/preference", authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<String> request,
			final ExecutionContext context) {

		try {
			// Extract the ID value from the request's query parameters
			String id = request.getQueryParameters().get("id");
			if (id == null) {
				// If ID value is missing, return a bad request response with an error message
				return createBadRequestResponse(request, "Please pass id value");
			} else {
				// Otherwise, handle the request asynchronously using a CompletableFuture
				CompletableFuture<HttpResponseMessage> response = CompletableFuture
						.supplyAsync(() -> handleRequest(id, context), newFixedPool)
						.thenApplyAsync((result) -> createOkResponse(request, result), newFixedPool)
						.exceptionally(throwable -> {
							return createErrorResponse(request, throwable.getMessage());
						});
				return response.get();
			}
		} catch (Exception e) {
			// If an exception is thrown, return an error response with the corresponding error message
			return createErrorResponse(request, e.getMessage());
		}
	}
	
	/**
	 * update a preference in the billing system.
	 * 
	 * @param request The HTTP request message containing the updated preference object.
	 * @param context The execution context for the function.
	 * @return An HTTP response message indicating the result of the update operation.
	 */
	@FunctionName("updatePreference")
	public HttpResponseMessage updatePreference(@HttpTrigger(name = "updatePreference", methods = {
			HttpMethod.PUT }, route = "billing/preference", authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<String> request,
			ExecutionContext context) {
		try {
			// Extract the body from the request object
			if (request.getBody() == null) {
				// If ID value is missing, return a bad request response with an error message
				return createBadRequestResponse(request, "Please pass the Preference configuration");
			}
			else {
				// Otherwise, handle the request asynchronously using a CompletableFuture
				CompletableFuture<HttpResponseMessage> response = CompletableFuture
	                    .supplyAsync(() -> handleRequest(request.getBody(), context), newFixedPool)
	                    .thenApplyAsync((result) -> createOkResponse(request, result), newFixedPool)
	                    .exceptionally(throwable -> {
	                        return createErrorResponse(request,throwable.getMessage());
	                    });
	            return response.get();
			}
		} catch (Exception e) {
			// If an exception is thrown, return an error response with the corresponding error message
			return createErrorResponse(request, e.getMessage());
		}
	}
	
	/**
	 * Deletes a preference in the billing system.
	 * 
	 * @param request The HTTP request message containing the ID of the preference to be deleted.
	 * @param context The execution context for the function.
	 * @return An HTTP response message indicating the result of the deletion operation.
	 */
	@FunctionName("deletePreference")
	public HttpResponseMessage deletePreference(@HttpTrigger(name = "deletePreference", methods = {
			HttpMethod.DELETE }, route = "billing/preference", authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<String> request,
			final ExecutionContext context) {

		try {
			// Extract the ID value from the request's query parameters
			String id = request.getQueryParameters().get("id");
			if (id == null) {
				// If ID value is missing, return a bad request response with an error message
				return createBadRequestResponse(request, "Please pass id value");
			} else {
				// Otherwise, handle the request asynchronously using a CompletableFuture
				CompletableFuture<HttpResponseMessage> response = CompletableFuture
						.supplyAsync(() -> handleRequest(id, context), newFixedPool)
						.thenApplyAsync((result) -> createOkResponse(request, result), newFixedPool)
						.exceptionally(throwable -> {
							return createErrorResponse(request, throwable.getMessage());
						});
				return response.get();
			}
		} catch (Exception e) {
			// If an exception is thrown, return an error response with the corresponding error message
			return createErrorResponse(request, e.getMessage());
		}
	}
}
