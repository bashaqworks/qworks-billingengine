package com.qworks.billingengine.beans;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.qworks.billingengine.response.DataResponse;
import com.qworks.billingengine.services.BillingPreferenceService;

@Component
public class BillingPreferenceCrudOperations {

	private final BillingPreferenceService billingPreferenceService;

	@Autowired
	public BillingPreferenceCrudOperations(final BillingPreferenceService billingPreferenceService) {
		super();
		this.billingPreferenceService = billingPreferenceService;
	}

	@Bean
	public Function<String, DataResponse> createPreference() {
		return billingPreferenceService::saveBillingPreference;
	}
	
	@Bean
	public Function<String, DataResponse> fetchPreference() {
		return billingPreferenceService::fetchBillingPreference;
	}
	@Bean
	public Function<String, DataResponse> updatePreference() {
		return billingPreferenceService::updateBillingPreference;
	}
	
	@Bean
	public Function<String, DataResponse> deletePreference() {
		return billingPreferenceService::deleteBillingPreference;
	}
}
