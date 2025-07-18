package com.example.nftservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NftServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NftServiceApplication.class, args);
	}

}
