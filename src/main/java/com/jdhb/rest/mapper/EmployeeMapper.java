package com.jdhb.rest.mapper;

import com.jdhb.rest.model.EmployeeRequest;
import com.jdhb.rest.model.dto.EmployeeDTO;
import com.jdhb.rest.wsdl.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mappings({
        @Mapping(target = "birthdate", dateFormat = "dd/MM/yyyy"),
        @Mapping(target = "joiningDate", dateFormat = "dd/MM/yyyy"),
    })
    Employee toEmployee(EmployeeRequest employeeRequest);

    EmployeeDTO toEmployeeDTO(Employee employee);

}
