package com.example.scheduleApp.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleSaveRequestDto {

    @NotBlank(message = "title을 입력하세요.")
    @Size(max=10, message = "title은 10자 이내로 입력하세요")
    private String title;

    private String content;
}
