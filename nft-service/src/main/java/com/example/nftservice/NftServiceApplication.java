package com.example.nftservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
=======
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
>>>>>>> 521b5ee (refactor(docker) combine sevices to 1 register)
public class NftServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NftServiceApplication.class, args);
	}

}
