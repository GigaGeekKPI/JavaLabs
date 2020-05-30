package com.Jlabs.consumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
		try {
			KafkaConsumerDemo.runConsumer();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

}
