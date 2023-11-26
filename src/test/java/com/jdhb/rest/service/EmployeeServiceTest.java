package com.jdhb.rest.service;

import com.jdhb.rest.client.EmployeeClient;
import com.jdhb.rest.exception.EmployeeClientException;
import com.jdhb.rest.wsdl.Employee;
import com.jdhb.rest.wsdl.SaveEmployeeResponse;
import jakarta.validation.Validation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeClient employeeClient;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void saveEmployee_ValidEmployee_ReturnsEmployee() {
        Employee employee = createEmployee();
        SaveEmployeeResponse response = new SaveEmployeeResponse();
        response.setEmployee(employee);

        when(employeeClient.saveEmployee(employee)).thenReturn(response);

        Employee savedEmployee = employeeService.saveEmployee(employee);

        assertNotNull(savedEmployee);
        assertEquals(employee, savedEmployee);
        verify(employeeClient, times(1)).saveEmployee(employee);
    }

    @Test
    void saveEmployee_ClientException_ThrowsException() {
        Employee employee = createEmployee();
        SaveEmployeeResponse response = new SaveEmployeeResponse();
        response.setEmployee(employee);
        String errorMessage = "Error message";

        when(employeeClient.saveEmployee(employee)).thenThrow(new EmployeeClientException(errorMessage));

        assertThrows(EmployeeClientException.class, () -> {
            employeeService.saveEmployee(employee);
        }, errorMessage);
    }

    private Employee createEmployee() {
        Employee employee = new Employee();
        employee.setId(BigInteger.valueOf(1L));
        employee.setName("John");
        employee.setSurname("Doe");
        employee.setDocumentType("ID");
        employee.setDocumentNumber("123456");
        employee.setPosition("Developer");
        employee.setSalary(50000.0);
        return employee;
    }
}
