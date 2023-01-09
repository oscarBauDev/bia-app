package com.bia.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Energy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double meterId;
    private double activeEnergy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Bogota")
    private LocalDateTime meterDate;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMeterId() {
        return meterId;
    }

    public void setMeterId(double meterId) {
        this.meterId = meterId;
    }

    public double getActiveEnergy() {
        return activeEnergy;
    }

    public void setActiveEnergy(double activeEnergy) {
        this.activeEnergy = activeEnergy;
    }

    public LocalDateTime getMeterDate() {
        return meterDate;
    }

    public void setMeterDate(LocalDateTime meterDate) {
        this.meterDate = meterDate;
    }

    @Override
    public String toString() {
        return "Energy{" +
                "id=" + id +
                ", meterId=" + meterId +
                ", activeEnergy=" + activeEnergy +
                ", meterDate=" + meterDate +
                '}';
    }
}
