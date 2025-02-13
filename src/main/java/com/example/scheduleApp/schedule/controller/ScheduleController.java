package com.example.scheduleApp.schedule.controller;

import com.example.scheduleApp.schedule.dto.ScheduleResponseDto;
import com.example.scheduleApp.schedule.dto.ScheduleSaveRequestDto;
import com.example.scheduleApp.schedule.entity.Schedule;
import com.example.scheduleApp.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;


    @PostMapping("/schdules")
    public ResponseEntity<ScheduleResponseDto> createSchedule(@Valid @RequestBody ScheduleSaveRequestDto requestDto) {

    }
}
