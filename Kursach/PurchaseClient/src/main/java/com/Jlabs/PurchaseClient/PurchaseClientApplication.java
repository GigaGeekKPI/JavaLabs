package com.Jlabs.PurchaseClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@ServletComponentScan
@EnableEurekaClient
@SpringBootApplication
public class PurchaseClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(PurchaseClientApplication.class, args);
	}

}
