package com.qworks.billingengine.services.impl;

import static com.qworks.billingengine.util.BillingResourceUtil.createDataResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.qworks.billingengine.enums.StatusEnum;
import com.qworks.billingengine.model.BillingPreference;
import com.qworks.billingengine.repositories.BillingScheduleRepository;
import com.qworks.billingengine.response.DataResponse;
import com.qworks.billingengine.services.BillingScheduleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * This service class provides methods for execute billing schedules.
 * @author Basha Shaik
 * @version 1.0
 * @since 03/28/2023
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({ @Autowired }), access = AccessLevel.PACKAGE)
public class BillingScheduleServiceImpl implements BillingScheduleService {

	private final BillingScheduleRepository billingScheduleRepository;

	/**
	 * Saves the given billing preference configuration to the database.
	 *
	 * @param configData the configuration data in JSON format
	 * @return a {@link DataResponse} object indicating whether the save operation
	 *         was successful or not
	 * @throws Exception if an error occurs while saving the configuration
	 */
	@Override
	public DataResponse execute(String configData) {
		try {
			Gson gson = new Gson();
			BillingPreference config = gson.fromJson(configData, BillingPreference.class);
			
		} catch (Exception e) {
			log.error("Error occurred while saving billing preference", e);
			return createDataResponse(configData, e.getMessage(), StatusEnum.FAILURE.name());
		}
		return null;
	}

	

}
