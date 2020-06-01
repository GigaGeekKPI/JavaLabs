package com.Jlabs.CommentClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@ServletComponentScan
@EnableEurekaClient
@SpringBootApplication
public class CommentClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentClientApplication.class, args);
	}

}
