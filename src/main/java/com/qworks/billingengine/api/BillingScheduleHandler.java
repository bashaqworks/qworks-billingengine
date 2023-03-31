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
 * This Handler class provides operations for billing execution.
 * @author Basha Shaik
 * @version 1.0
 * @since 03/28/2023
 */
public class BillingScheduleHandler extends FunctionInvoker<Object, DataResponse> {
	private final ExecutorService newFixedPool = Executors.newFixedThreadPool(10);
	/**
	 * Execute billing schedules.
	 * 
	 * @param request The HTTP request message containing the create preference object.
	 * @param context The execution context for the function.
	 * @return An HTTP response message indicating the result of the create operation.
	 */
	@FunctionName("execute")
	public HttpResponseMessage execute(@HttpTrigger(name = "execute", methods = {
			HttpMethod.POST }, route = "billing/execute", authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<String> request,
			ExecutionContext context) {
		try {
			// Extract the body from the request object
			if (request.getBody() == null) {
				return createBadRequestResponse(request, "Please pass the billing info...");
			} else {
				// Otherwise, handle the request asynchronously using a CompletableFuture
				CompletableFuture<HttpResponseMessage> response = CompletableFuture
	                    .supplyAsync(() -> handleRequest(request.getBody(), context), newFixedPool)
	                    .thenApplyAsync((str) -> createOkResponse(request, str), newFixedPool)
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
}
