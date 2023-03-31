package com.qworks.billingengine.services.impl;

import static com.qworks.billingengine.util.BillingResourceUtil.createDataResponse;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.qworks.billingengine.enums.StatusEnum;
import com.qworks.billingengine.model.BillingPreference;
import com.qworks.billingengine.repositories.BillingPreferenceRepository;
import com.qworks.billingengine.response.DataResponse;
import com.qworks.billingengine.services.BillingPreferenceService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 * This service class provides methods for managing billing preferences.
 * @author Basha Shaik
 * @version 1.0
 * @since 03/28/2023
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({ @Autowired }), access = AccessLevel.PACKAGE)
public class BillingPreferenceServiceImpl implements BillingPreferenceService {

	private final BillingPreferenceRepository billingPreferenceRepository;

	/**
	 * Saves the given billing preference configuration to the database.
	 *
	 * @param configData the configuration data in JSON format
	 * @return a {@link DataResponse} object indicating whether the save operation
	 *         was StatusEnum.SUCCESS.name()ful or not
	 * @throws Exception if an error occurs while saving the configuration
	 */
	@Override
	public DataResponse saveBillingPreference(String configData) {
		try {
			Gson gson = new Gson();
			BillingPreference config = gson.fromJson(configData, BillingPreference.class);
			Optional<BillingPreference> preference = billingPreferenceRepository.findByNameAndActive(config.getName(),
					Boolean.TRUE);
			if (preference.isPresent()) {
				return createDataResponse(config, "Name already exists..!", StatusEnum.FAILURE.name());
			} else {
				config.setId(UUID.randomUUID().toString());
				config.setCreateddate(new Date().getTime());
				config.setActive(true);
				config = billingPreferenceRepository.save(config);
				return createDataResponse(config, "Data has been saved successfully..!", StatusEnum.SUCCESS.name());
			}
		} catch (Exception e) {
			log.error("Error occurred while saving billing preference", e);
			return createDataResponse(configData, e.getMessage(), StatusEnum.FAILURE.name());
		}
	}

	/**
	 * find the given billing preference configuration from the database.
	 *
	 * @param id the represents the unique identifier
	 * @return a {@link DataResponse} object containing the retrieved billing
	 *         preference, along with a message indicating whether the operation was
	 *         StatusEnum.SUCCESS.name()ful or not
	 * @throws Exception if an error occurs while saving the configuration
	 */
	@Override
	public DataResponse fetchBillingPreference(String id) {
		try {
			Optional<BillingPreference> preference = billingPreferenceRepository.findByIdAndActive(id, Boolean.TRUE);
			if (preference.isPresent()) {
				return createDataResponse(preference.get(), "Data has been retrieved successfully..!", StatusEnum.SUCCESS.name());
			} else {
				return createDataResponse(id, "Unable to fetch the id from preferences config..!", StatusEnum.FAILURE.name());
			}
		} catch (Exception e) {
			log.error("Error occurred while fetching billing preference", e);
			return createDataResponse(id, e.getMessage(), StatusEnum.FAILURE.name());
		}
	}

	/**
	 * update the given billing preference configuration to the database.
	 *
	 * @param configData the configuration data in JSON format
	 * @return a {@link DataResponse} object indicating whether the save operation
	 *         was StatusEnum.SUCCESS.name()ful or not
	 * @throws Exception if an error occurs while saving the configuration
	 */
	@Override
	public DataResponse updateBillingPreference(String configData) {
		try {
			Gson gson = new Gson();
			BillingPreference config = gson.fromJson(configData, BillingPreference.class);
			Optional<BillingPreference> preference = billingPreferenceRepository.findByIdAndActive(config.getId(),
					Boolean.TRUE);
			if (preference.isPresent()) {
				if (config.getName().equals(preference.get().getName())) {
					config.setLastmodifieddate(new Date().getTime());
					config = billingPreferenceRepository.save(config);
					return createDataResponse(config, "Data has been updated StatusEnum.SUCCESS.name()fully..!", StatusEnum.SUCCESS.name());
				} else {
					Optional<BillingPreference> nameExists = billingPreferenceRepository
							.findByNameAndActive(config.getName(), Boolean.TRUE);
					if (nameExists.isPresent()) {
						return createDataResponse(config, "Name already exists..!", StatusEnum.FAILURE.name());
					} else {
						config.setLastmodifieddate(new Date().getTime());
						config = billingPreferenceRepository.save(config);
						return createDataResponse(config, "Data has been updated successfully..!", StatusEnum.SUCCESS.name());
					}
				}
			} else {
				return createDataResponse(config, "Billing preference is not present..!", StatusEnum.FAILURE.name());
			}
		} catch (Exception e) {
			log.error("Error occurred while updating billing preference", e);
			return createDataResponse(configData, e.getMessage(), StatusEnum.FAILURE.name());
		}
	}

	/**
	 * delete the given billing preference configuration from the database.
	 *
	 * @param id the represents the unique identifier
	 * @return a {@link DataResponse} object containing the retrieved billing
	 *         preference, along with a message indicating whether the operation was
	 *         StatusEnum.SUCCESS.name()ful or not
	 * @throws Exception if an error occurs while saving the configuration
	 */
	@Override
	public DataResponse deleteBillingPreference(String id) {
		try {
			Optional<BillingPreference> preference = billingPreferenceRepository.findByIdAndActive(id, Boolean.TRUE);
			if (preference.isPresent()) {
				BillingPreference config = preference.get();
				config.setLastmodifieddate(new Date().getTime());
				config.setActive(false);
				config = billingPreferenceRepository.save(config);
				return createDataResponse(config, "Data has been deleted successfully..!", StatusEnum.SUCCESS.name());
			} else {
				return createDataResponse(id, "Unable to fetch the id from preference config..!", StatusEnum.FAILURE.name());
			}
		} catch (Exception e) {
			log.error("Error occurred while deleting billing preference", e);
			return createDataResponse(id, e.getMessage(), StatusEnum.FAILURE.name());
		}
	}

	

}
