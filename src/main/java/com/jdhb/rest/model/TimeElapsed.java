package com.jdhb.rest.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Data
@NoArgsConstructor
public class TimeElapsed {
    private int years;
    private int months;
    private int days;

    public TimeElapsed(LocalDate initialDate) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(initialDate, currentDate);
        this.days = period.getDays();
        this.months = period.getMonths();
        this.years = period.getYears();
    }

    public TimeElapsed(int days, int months, int years) {
        this.days = days;
        this.months = months;
        this.years = years;
    }
}
