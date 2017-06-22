package com.infor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@EntityScan("com.infor")
@EnableJpaRepositories("com.infor")
public class SlotsApplication {
	public static void main(String[] args) {
		SpringApplication.run(SlotsApplication.class, args);
	}
}
