package com.qworks.billingengine.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.lang.Nullable;

import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.DirectConnectionConfig;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.config.CosmosConfig;
import com.azure.spring.data.cosmos.core.ResponseDiagnostics;
import com.azure.spring.data.cosmos.core.ResponseDiagnosticsProcessor;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;
import com.azure.spring.data.cosmos.repository.config.EnableReactiveCosmosRepositories;

@Configuration
@EnableConfigurationProperties(CosmosProperties.class)
@EnableCosmosRepositories(basePackages = { "com.qworks.billingengine.repositories" })
@EnableReactiveCosmosRepositories(basePackages = { "com.qworks.billingengine.repositories" })
@PropertySource("classpath:application.properties")
public class CosmosDatabaseConfig extends AbstractCosmosConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(CosmosDatabaseConfig.class);

	@Autowired
	private CosmosProperties properties;

	@Bean
	public CosmosClientBuilder cosmosClientBuilder() {
		DirectConnectionConfig directConnectionConfig = DirectConnectionConfig.getDefaultConfig();
		return new CosmosClientBuilder().endpoint(properties.getUri()).key(properties.getKey())
				.directMode(directConnectionConfig);
	}

	@Bean
	public CosmosConfig cosmosConfig() {
		return CosmosConfig.builder().responseDiagnosticsProcessor(new ResponseDiagnosticsProcessorImplementation())
				.enableQueryMetrics(properties.isQueryMetricsEnabled()).build();
	}

	@Override
	protected String getDatabaseName() {
		return properties.getDatabase();
	}

	private static class ResponseDiagnosticsProcessorImplementation implements ResponseDiagnosticsProcessor {

		@Override
		public void processResponseDiagnostics(@Nullable ResponseDiagnostics responseDiagnostics) {
//            logger.info("Response Diagnostics {}", responseDiagnostics);
		}
	}
}
