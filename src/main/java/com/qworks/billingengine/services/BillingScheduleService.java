package com.qworks.billingengine.services;

import org.springframework.stereotype.Service;

import com.qworks.billingengine.response.DataResponse;

@Service
public interface BillingScheduleService {

	public DataResponse execute(String data);
}
