package com.bia.app.controller;

import com.bia.app.model.dto.RequestDto;
import com.bia.app.model.dto.ResponseDto;
import com.bia.app.service.IEnergyService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/biadata")
public class EnergyController {

    private final IEnergyService service;

    public EnergyController(IEnergyService service) {
        this.service = service;
    }

    @GetMapping()
    public List<ResponseDto> getData(@Valid @RequestBody RequestDto requestDto){
        return service.getDataByPeriod(requestDto);
    }
}
