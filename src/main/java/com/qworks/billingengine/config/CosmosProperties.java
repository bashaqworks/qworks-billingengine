package com.qworks.billingengine.config;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "azure.cosmos")
@Data
public class CosmosProperties implements Serializable {
	private static final long serialVersionUID = -2279969981651721254L;
	private String uri;
	private String key;
	private String secondaryKey;
	private boolean queryMetricsEnabled;
	private String database;
}
