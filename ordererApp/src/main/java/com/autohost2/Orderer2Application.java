package com.autohost2;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
/**
 * Application dotée de point d'entrée (API rest), qui permet de gérer des instances
 * auprès de différents providers,
 * Vultr et ensuite d'autres seront implémentés
 * @author mehdi
 *
 */
@ComponentScan
@SpringBootApplication
@EnableBatchProcessing
public class Orderer2Application {

	public static void main(String[] args) {
		SpringApplication.run(Orderer2Application.class, args);
	}

}
