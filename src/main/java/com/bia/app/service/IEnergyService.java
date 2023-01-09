package com.bia.app.service;

import com.bia.app.model.dto.RequestDto;
import com.bia.app.model.dto.ResponseDto;

import java.util.List;

public interface IEnergyService {
    List<ResponseDto> getDataByPeriod(RequestDto requestDto);
}
