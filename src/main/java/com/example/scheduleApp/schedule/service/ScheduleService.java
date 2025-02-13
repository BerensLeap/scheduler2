package com.example.scheduleApp.schedule.service;

import com.example.scheduleApp.User.entity.User;
import com.example.scheduleApp.common.exception.ScheduleNotFoundException;
import com.example.scheduleApp.common.exception.UnauthorizedAccessException;
import com.example.scheduleApp.schedule.repository.ScheduleRepository;
import com.example.scheduleApp.schedule.dto.ScheduleResponseDto;
import com.example.scheduleApp.schedule.entity.Schedule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;


    @Transactional
    public ScheduleResponseDto save(String title, String content, User user) { // 일정 생성

        Schedule schedule = new Schedule(title, content, user);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getUser().getUserName(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getUpdatedAt()
        );
    }

    @Transactional(readOnly = true)
    public ScheduleResponseDto findById(Long id) { // 일정 id 단건 조회
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new ScheduleNotFoundException("Schedule not found")
        );
        return ScheduleResponseDto.toDto(schedule);
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findAll() { // 모든 일정 조회
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleResponseDto> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            dtos.add(ScheduleResponseDto.toDto(schedule));
        }
        return dtos;
    }

    @Transactional
    public ScheduleResponseDto updateTitleOrContent(Long id,String title, String content, User registerUser) { // title, content 업데이트
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new ScheduleNotFoundException("Schedule not found")
        );
        validateAuth(registerUser, schedule); // 검증
        schedule.update(title, content);
        return ScheduleResponseDto.toDto(schedule);
    }

    @Transactional
    public void deleteById(Long id, User registerUser) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new ScheduleNotFoundException("Schedule not found"));
        validateAuth(registerUser, schedule); // 검증
        scheduleRepository.deleteById(id);

    }

    public static void validateAuth(User registerUser, Schedule schedule) { // 작성 유저와 로그인 유저 일치 검증
        if (!schedule.getUser().getUserName().equals(registerUser.getUserName())) {
            throw new UnauthorizedAccessException("No authorization");
        }
    }

}
