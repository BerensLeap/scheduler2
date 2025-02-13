package com.example.scheduleApp.schedule.controller;

import com.example.scheduleApp.User.entity.User;
import com.example.scheduleApp.common.Const;
import com.example.scheduleApp.schedule.dto.ScheduleResponseDto;
import com.example.scheduleApp.schedule.dto.ScheduleSaveRequestDto;
import com.example.scheduleApp.schedule.entity.Schedule;
import com.example.scheduleApp.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;


    // 일정 생성
    @PostMapping("/schdules")
    public ResponseEntity<ScheduleResponseDto> createSchedule(@Valid @RequestBody ScheduleSaveRequestDto requestDto,
                                                              @SessionAttribute(name = Const.LOGIN_USER, required = false) User user) {
        ScheduleResponseDto responseDto = scheduleService.save(
                requestDto.getTitle(), requestDto.getContent(), user);


        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 일정 단건 조회
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.findById(scheduleId));
    }

    // 모든 일정 조회
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules() {
        return ResponseEntity.ok(scheduleService.findAll());
    }

    // 일정 수정
    @PatchMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long scheduleId, @Valid @RequestBody ScheduleSaveRequestDto requestDto,
                                                              @SessionAttribute(name = Const.LOGIN_USER, required = false) User user) {
        ScheduleResponseDto responseDto = scheduleService.updateTitleOrContent(scheduleId, requestDto.getTitle(), requestDto.getContent(), user);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 일정 삭제
    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> deleteSchedule(@PathVariable Long scheduleId,
                                                              @SessionAttribute(name = Const.LOGIN_USER, required = false) User user) {
        scheduleService.deleteById(scheduleId,user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
