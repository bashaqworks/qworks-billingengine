package com.qworks.billingengine.services;

import org.springframework.stereotype.Service;

import com.qworks.billingengine.response.DataResponse;

@Service
public interface BillingPreferenceService {

	public DataResponse saveBillingPreference(String data);
	public DataResponse fetchBillingPreference(String id);
	public DataResponse updateBillingPreference(String data);
	public DataResponse deleteBillingPreference(String id);
}
