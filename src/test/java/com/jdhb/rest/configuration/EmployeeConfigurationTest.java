package com.jdhb.rest.configuration;

import com.jdhb.rest.client.EmployeeClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmployeeConfigurationTest {

    @Autowired
    private Jaxb2Marshaller marshaller;

    @Autowired
    private EmployeeClient employeeClient;

    @Test
    public void testMarshallerBean() {
        assertNotNull(marshaller);
        assertEquals("com.jdhb.rest.wsdl", marshaller.getContextPath());
    }

    @Test
    public void testEmployeeClientBean() {
        assertNotNull(employeeClient);
        assertEquals("http://localhost:8080/ws", employeeClient.getDefaultUri());
        assertEquals(marshaller, employeeClient.getMarshaller());
        assertEquals(marshaller, employeeClient.getUnmarshaller());
    }
}
