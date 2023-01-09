package com.bia.app.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class RequestDto {

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Bogota")
    private LocalDate meterDate;

    @NotBlank(message = "Period can't be null or empty.")
    private String period;

    public RequestDto() {
    }

    public RequestDto(LocalDate meterDate, String period) {
        this.meterDate = meterDate;
        this.period = period;
    }

    public LocalDate getMeterDate() {
        return meterDate;
    }

    public void setMeterDate(LocalDate meterDate) {
        this.meterDate = meterDate;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
