package com.demo.tweetsandfeeds;

import java.net.http.HttpClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication

public class TweetsAndFeedsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweetsAndFeedsServiceApplication.class, args);
	}

	@Bean
	public HttpClient httpClient() {
		HttpClient httpClient = HttpClient.newBuilder().build();
		return httpClient;
	}

	@Bean ObjectMapper objectMapper(){
		ObjectMapper mapper = new ObjectMapper();
		mapper.findAndRegisterModules();
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return mapper;
	}

}
