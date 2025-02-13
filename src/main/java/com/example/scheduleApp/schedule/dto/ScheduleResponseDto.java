package com.example.scheduleApp.schedule.dto;

import com.example.scheduleApp.schedule.entity.Schedule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ScheduleResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final String UserName;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser().getUsername(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

}
