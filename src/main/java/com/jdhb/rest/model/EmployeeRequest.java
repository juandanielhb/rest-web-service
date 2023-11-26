package com.jdhb.rest.model;

import com.jdhb.rest.model.validator.IsAdult;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EmployeeRequest {
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String documentType;
    @NotBlank
    private String documentNumber;
    @IsAdult
    private String birthdate;
    @Pattern(regexp = "^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/\\d{4}$", message = "invalid format. Use dd/MM/yyyy")
    private String joiningDate;
    @NotBlank
    private String position;
    @Positive
    private Double salary;
}
