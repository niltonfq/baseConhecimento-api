package com.abs.baseConhecimento.api.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.abs.baseConhecimento.api.services.DBService;


@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		if (strategy.equals("create")) {
			dbService.instantiateTestDatabase();
		}
		return true;
	}
	
}
