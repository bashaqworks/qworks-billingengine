package com.qworks.billingengine.model;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

import lombok.Getter;
import lombok.Setter;

@Container(containerName = "billing_preference")
@Getter
@Setter
public class BillingPreference extends BaseEntity {
	private static final long serialVersionUID = 323049927149990327L;
	@Id
	@PartitionKey
	private String id;
	private String name;
	private String billingCycleStart;
	private String readyForBillingDate;
	private int billingDayOfMonth;
	private String prorationPeriodTreatment;
	private String billingInterval;
	private int decimalPrecision;

}
