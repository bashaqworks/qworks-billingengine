package com.qworks.billingengine.repositories;

import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.qworks.billingengine.model.BillingPreference;

@Repository
public interface BillingScheduleRepository extends CosmosRepository<BillingPreference, String> {
}