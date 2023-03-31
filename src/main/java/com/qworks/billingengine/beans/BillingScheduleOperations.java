package com.qworks.billingengine.beans;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.qworks.billingengine.response.DataResponse;
import com.qworks.billingengine.services.BillingScheduleService;

@Component
public class BillingScheduleOperations {

	private final BillingScheduleService billingScheduleService;

	@Autowired
	public BillingScheduleOperations(final BillingScheduleService billingScheduleService) {
		super();
		this.billingScheduleService = billingScheduleService;
	}

	@Bean
	public Function<String, DataResponse> execute() {
		return billingScheduleService::execute;
	}
}
