package com.bia.app.util;

import com.bia.app.model.dto.ResponseDto;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class MockData {

    public static List<ResponseDto> DAILY_DATA = Arrays.asList(
            new ResponseDto(LocalDateTime.parse("2022-10-26T00:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-26T01:00:00"), 4.3899999999994186D),
            new ResponseDto(LocalDateTime.parse("2022-10-26T02:00:00"), 3.4899999999997817D),
            new ResponseDto(LocalDateTime.parse("2022-10-26T03:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-26T04:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-26T05:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-26T06:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-26T07:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-26T08:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-26T09:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-26T10:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-26T11:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-26T12:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-26T13:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-26T14:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-26T15:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-26T16:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-26T17:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-26T18:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-26T19:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-26T20:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-26T21:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-26T22:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-26T23:00:00"), 3.8699999999989814D)
    );

    public static List<ResponseDto> WEEKLY_DATA = Arrays.asList(
            new ResponseDto(LocalDateTime.parse("2022-10-24T00:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-25T00:00:00"), 4.389999999999418D),
            new ResponseDto(LocalDateTime.parse("2022-10-26T00:00:00"), 3.4899999999997817D),
            new ResponseDto(LocalDateTime.parse("2022-10-27T00:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-28T00:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-29T00:00:00"), 3.8699999999989814D),
            new ResponseDto(LocalDateTime.parse("2022-10-30T00:00:00"), 3.8699999999989814D)
            );

}
