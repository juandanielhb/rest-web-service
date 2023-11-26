package com.jdhb.rest.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jdhb.rest.model.TimeElapsed;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class EmployeeDTO {
    private Integer id;
    private String name;
    private String surname;
    private String documentType;
    private String documentNumber;
    private LocalDate birthdate;
    private TimeElapsed currentAge;
    private LocalDate joiningDate;
    private TimeElapsed lengthOfEmployment;
    private String position;
    private Double salary;

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
        setCurrentAge();
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
        setLengthOfEmployment();
    }

    private void setCurrentAge() {
        if (this.birthdate != null) {
            this.currentAge = new TimeElapsed(birthdate);
        }
    }

    private void setLengthOfEmployment() {
        if (this.joiningDate != null) {
            this.lengthOfEmployment = new TimeElapsed(joiningDate);
        }
    }
}
