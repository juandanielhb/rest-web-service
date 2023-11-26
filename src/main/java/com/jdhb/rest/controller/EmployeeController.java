package com.jdhb.rest.controller;

import com.jdhb.rest.service.EmployeeService;
import com.jdhb.rest.mapper.EmployeeMapper;
import com.jdhb.rest.model.dto.EmployeeDTO;
import com.jdhb.rest.model.EmployeeRequest;
import com.jdhb.rest.wsdl.Employee;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class EmployeeController {
    private final EmployeeMapper employeeMapper;
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeMapper employeeMapper, EmployeeService employeeService) {
        this.employeeMapper = employeeMapper;
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public ResponseEntity<EmployeeDTO> saveEmployee(@Valid @RequestBody EmployeeRequest employeeRequest){
        log.info("SaveEmploye {}", employeeRequest);
        Employee employee = employeeMapper.toEmployee(employeeRequest);
        employee = employeeService.saveEmployee(employee);
        EmployeeDTO employeeDTO = employeeMapper.toEmployeeDTO(employee);
        return ResponseEntity.ok(employeeDTO);
    }
}
