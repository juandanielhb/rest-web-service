package com.jdhb.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.datatype.DatatypeConfigurationException;

@SpringBootApplication
public class RESTWebServiceApplication {

	public static void main(String[] args) throws DatatypeConfigurationException {
		SpringApplication.run(RESTWebServiceApplication.class, args);
	}
}
