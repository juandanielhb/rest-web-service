package com.jdhb.rest.controller;

import com.jdhb.rest.service.EmployeeService;
import com.jdhb.rest.mapper.EmployeeMapper;
import com.jdhb.rest.model.dto.EmployeeDTO;
import com.jdhb.rest.model.EmployeeRequest;
import com.jdhb.rest.wsdl.Employee;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Slf4j
class EmployeeControllerTest {

    @Mock
    private EmployeeMapper employeeMapper;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private Validator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void saveEmployee_ValidRequest_ReturnsOk() {
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setName("John Doe");

        Employee employee = new Employee();
        employee.setName("John Doe");

        EmployeeDTO employeeDTO = new EmployeeDTO();

        when(employeeMapper.toEmployee(employeeRequest)).thenReturn(employee);
        when(employeeService.saveEmployee(employee)).thenReturn(employee);
        when(employeeMapper.toEmployeeDTO(employee)).thenReturn(employeeDTO);

        ResponseEntity<EmployeeDTO> responseEntity = employeeController.saveEmployee(employeeRequest);

        assertEquals(ResponseEntity.ok().body(employeeDTO), responseEntity);
        verify(employeeMapper, times(1)).toEmployee(employeeRequest);
        verify(employeeService, times(1)).saveEmployee(employee);
        verify(employeeMapper, times(1)).toEmployeeDTO(employee);
    }

}

