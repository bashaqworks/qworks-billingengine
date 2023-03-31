package com.qworks.billingengine.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.qworks.billingengine.model.BillingPreference;

@Repository
public interface BillingPreferenceRepository extends CosmosRepository<BillingPreference, String> {
	Optional<BillingPreference> findByNameAndActive(String name, Boolean active);

	Optional<BillingPreference> findByIdAndActive(String id, Boolean active);

	List<BillingPreference> findAllByActive(Boolean active, Sort byColumn);

	BillingPreference save(BillingPreference config);
}