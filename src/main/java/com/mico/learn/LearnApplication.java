package com.mico.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@ComponentScan(value = "com.mico")
@SpringBootApplication
@ImportResource(value ={"classpath:spring/redis-cluster.xml","classpath:spring/mysql.xml","classpath:spring/mongo.xml"} )
public class LearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnApplication.class, args);
	}

}
