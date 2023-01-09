package com.bia.app.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ResponseDto {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Bogota")
    private LocalDateTime meterDate;
    private Double value;

    public ResponseDto() {
    }
    public ResponseDto(LocalDateTime meterDate, Double value) {
        this.meterDate = meterDate;
        this.value = value;
    }

    public LocalDateTime getMeterDate() {
        return meterDate;
    }

    public void setMeterDate(LocalDateTime meterDate) {
        this.meterDate = meterDate;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
