package com.bia.app.controller;

import com.bia.app.exception.PeriodNotFoundException;
import com.bia.app.model.dto.RequestDto;
import com.bia.app.service.impl.EnergyServiceImpl;
import com.bia.app.util.MockData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EnergyController.class)
@ExtendWith(MockitoExtension.class)
class EnergyControllerTest {

    private static final String PATH = "/biadata";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EnergyServiceImpl service;

    @Test
    void shouldReturnAllEnergyDataByDailyPeriod() throws Exception {
        RequestDto requestDto = new RequestDto(LocalDate.parse("2022-10-26"), "daily");

        Mockito.when(service.getDataByPeriod(any(RequestDto.class))).thenReturn(MockData.DAILY_DATA);

        this.mockMvc.perform(get(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(24)));
    }

    @Test
    void shouldReturnAllEnergyDataByWeeklyPeriod() throws Exception {
        RequestDto requestDto = new RequestDto(LocalDate.parse("2022-10-26"), "weekly");

        Mockito.when(service.getDataByPeriod(any(RequestDto.class))).thenReturn(MockData.WEEKLY_DATA);

        this.mockMvc.perform(get(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(7)));
    }


    @Test
    void shouldGetErrorWhenPeriodIsEmpty() throws Exception {
        RequestDto requestDto = new RequestDto(LocalDate.parse("2022-10-26"), "");

        Mockito.when(service.getDataByPeriod(requestDto)).thenThrow(RuntimeException.class);

        this.mockMvc.perform(get(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.violations", IsNot.not(IsNull.nullValue())));
    }

    @Test
    void shouldGetErrorWhenPeriodNotExist() throws Exception {
        RequestDto requestDto = new RequestDto(LocalDate.parse("2022-10-26"), "month");

        Mockito.doThrow(new PeriodNotFoundException("The period '" + requestDto.getPeriod() + "' is not valid!")).when(service).getDataByPeriod(any(RequestDto.class));
        this.mockMvc.perform(get(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }
}