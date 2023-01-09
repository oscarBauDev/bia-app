package com.bia.app.service.impl;

import com.bia.app.exception.PeriodNotFoundException;
import com.bia.app.model.Energy;
import com.bia.app.model.dto.RequestDto;
import com.bia.app.model.dto.ResponseDto;
import com.bia.app.repository.EnergyRepository;
import com.bia.app.service.IEnergyService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Service
public class EnergyServiceImpl implements IEnergyService {

    String DAILY = "daily";
    String WEEKLY = "weekly";
    String MONTHLY = "monthly";

    private final EnergyRepository repository;

    public EnergyServiceImpl(EnergyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ResponseDto> getDataByPeriod(RequestDto requestDto) {

        if(requestDto.getPeriod().equalsIgnoreCase(DAILY)){
            return getAllByDay(requestDto.getMeterDate());
        }
        else if(requestDto.getPeriod().equalsIgnoreCase(WEEKLY)){
            return getAllByWeek(requestDto.getMeterDate());
        }
        else if(requestDto.getPeriod().equalsIgnoreCase(MONTHLY)){
            return getAllByMonth(requestDto.getMeterDate());
        }
        else{
            throw new PeriodNotFoundException("The period '" + requestDto.getPeriod() + "' is not valid!");
        }
    }

    private List<ResponseDto> getAllByDay(LocalDate date){
        List<ResponseDto> resultDaily = new ArrayList<>();

        LocalDateTime startDay = date.atStartOfDay();
        LocalDateTime endHour = startDay.plusSeconds(3599);

        for (int i = 0; i < 24; i++) {
            calculateConsume(startDay, endHour, resultDaily);

            startDay = startDay.plusHours(1L);
            endHour = endHour.plusHours(1L);
        }

        return resultDaily;
    }

    private List<ResponseDto> getAllByWeek(LocalDate date){
        List<ResponseDto> resultWeekly = new ArrayList<>();

        DayOfWeek FIRST_DAY_OF_WEEK = DayOfWeek.MONDAY;
        LocalDate monday = date.with(FIRST_DAY_OF_WEEK);

        LocalDateTime startHourDay = monday.atStartOfDay();
        LocalDateTime endHourDay = monday.atTime(LocalTime.MAX);

        for (int i = 0; i < 7; i++) {
            calculateConsume(startHourDay, endHourDay, resultWeekly);

            startHourDay = startHourDay.plusDays(1L);
            endHourDay = endHourDay.plusDays(1L);
        }

        return resultWeekly;
    }

    private List<ResponseDto> getAllByMonth(LocalDate date){
        List<ResponseDto> resultMonth = new ArrayList<>();

        LocalDate firstDayOfTheMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfTheMonth = date.with(TemporalAdjusters.lastDayOfMonth());

        LocalDateTime startHourDay = firstDayOfTheMonth.atStartOfDay();
        LocalDateTime endHourDay = firstDayOfTheMonth.atTime(LocalTime.MAX);


        for (int i = 0; i < lastDayOfTheMonth.getDayOfMonth(); i++) {
            calculateConsume(startHourDay, endHourDay, resultMonth);

            startHourDay = startHourDay.plusDays(1L);
            endHourDay = endHourDay.plusDays(1L);
        }

        return resultMonth;
    }

    private void calculateConsume(LocalDateTime startDay, LocalDateTime endHour, List<ResponseDto> result) {
        ResponseDto actualHour = new ResponseDto();

        Optional<Energy> firstRecord;
        Optional<Energy> lastRecord;

        firstRecord = repository.findFirstByMeterDateBetweenOrderByMeterDateAsc(startDay, endHour);
        lastRecord = repository.findFirstByMeterDateBetweenOrderByMeterDateDesc(startDay, endHour);

        if (firstRecord.isPresent() && lastRecord.isPresent()) {
            Double firstValue = firstRecord.get().getActiveEnergy();
            Double lastValue = lastRecord.get().getActiveEnergy();
            Double finalValue = lastValue - firstValue;
            actualHour.setMeterDate(startDay);
            actualHour.setValue(finalValue);
            result.add(actualHour);
        }
        else {
            actualHour.setMeterDate(startDay);
            actualHour.setValue(0D);
            result.add(actualHour);
        }
    }
}
