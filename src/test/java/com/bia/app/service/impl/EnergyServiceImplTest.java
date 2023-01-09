package com.bia.app.service.impl;

import com.bia.app.exception.PeriodNotFoundException;
import com.bia.app.model.dto.RequestDto;
import com.bia.app.model.dto.ResponseDto;
import com.bia.app.repository.EnergyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class EnergyServiceImplTest {

    @InjectMocks
    EnergyServiceImpl service;

    @Mock
    EnergyRepository repository;

    @Test
    void getAllDataEnergyByDailyPeriod(){
        RequestDto requestDto = new RequestDto(LocalDate.parse("2022-10-26"), "daily");

        List<ResponseDto> expected = service.getDataByPeriod(requestDto);
        Optional<ResponseDto> firstRecord = expected.stream().findFirst();

        Mockito.verify(repository, Mockito.times(24)).findFirstByMeterDateBetweenOrderByMeterDateAsc(any(LocalDateTime.class), any(LocalDateTime.class));
        Mockito.verify(repository, Mockito.times(24)).findFirstByMeterDateBetweenOrderByMeterDateDesc(any(LocalDateTime.class), any(LocalDateTime.class));
        Assertions.assertEquals(24, expected.size());
    }

    @Test
    void getAllDataEnergyByWeeklyPeriod() {
        RequestDto requestDto = new RequestDto(LocalDate.parse("2022-10-26"), "weekly");

        List<ResponseDto> expected = service.getDataByPeriod(requestDto);

        Mockito.verify(repository, Mockito.times(7)).findFirstByMeterDateBetweenOrderByMeterDateAsc(any(LocalDateTime.class), any(LocalDateTime.class));
        Mockito.verify(repository, Mockito.times(7)).findFirstByMeterDateBetweenOrderByMeterDateDesc(any(LocalDateTime.class), any(LocalDateTime.class));
        Assertions.assertEquals(7, expected.size());
    }

    @Test
    void getAllDataEnergyByMonthlyPeriod() {
        RequestDto requestDto = new RequestDto(LocalDate.parse("2022-10-26"), "monthly");

        List<ResponseDto> expected = service.getDataByPeriod(requestDto);

        Mockito.verify(repository, Mockito.times(31)).findFirstByMeterDateBetweenOrderByMeterDateAsc(any(LocalDateTime.class), any(LocalDateTime.class));
        Mockito.verify(repository, Mockito.times(31)).findFirstByMeterDateBetweenOrderByMeterDateDesc(any(LocalDateTime.class), any(LocalDateTime.class));
        Assertions.assertEquals(31, expected.size());
    }


    @Test
    void shouldThrowExceptionWhenPeriodIsNotValid(){
        RequestDto requestDto = new RequestDto(LocalDate.parse("2022-10-26"), "day");
        Assertions.assertThrows(PeriodNotFoundException.class, () -> service.getDataByPeriod(requestDto));
    }
}