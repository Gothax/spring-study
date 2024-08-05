package com.gothaxcity.restfulservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RestfulServiceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ac = SpringApplication.run(RestfulServiceApplication.class, args);

		String[] allbeanNames = ac.getBeanDefinitionNames();
		for (String allbeanName : allbeanNames) {
			System.out.println("allbeanName = " + allbeanName);
		}

	}

}
