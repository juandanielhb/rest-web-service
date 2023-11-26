package com.jdhb.rest.configuration;

import com.jdhb.rest.client.EmployeeClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class EmployeeConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.jdhb.rest.wsdl");
        return marshaller;
    }

    @Bean
    public EmployeeClient employeeClient(Jaxb2Marshaller marshaller) {
        EmployeeClient employeeClient = new EmployeeClient();
        employeeClient.setDefaultUri("http://localhost:8080/ws");
        employeeClient.setMarshaller(marshaller);
        employeeClient.setUnmarshaller(marshaller);
        return employeeClient;
    }
}
