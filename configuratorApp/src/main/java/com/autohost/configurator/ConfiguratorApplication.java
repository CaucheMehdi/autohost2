package com.autohost.configurator;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
@EnableBatchProcessing
public class ConfiguratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfiguratorApplication.class, args);
	}

}
