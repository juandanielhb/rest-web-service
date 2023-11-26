package com.jdhb.rest.service;

import com.jdhb.rest.client.EmployeeClient;
import com.jdhb.rest.exception.EmployeeClientException;
import com.jdhb.rest.wsdl.Employee;
import com.jdhb.rest.wsdl.SaveEmployeeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeeService {

    private final EmployeeClient employeeClient;

    public EmployeeService(EmployeeClient employeeClient) {
        this.employeeClient = employeeClient;
    }

    public Employee saveEmployee(Employee employee) throws EmployeeClientException {
        SaveEmployeeResponse response = employeeClient.saveEmployee(employee);
        return response.getEmployee();
    }
}
