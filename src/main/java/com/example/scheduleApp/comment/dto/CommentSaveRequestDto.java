package com.example.scheduleApp.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentSaveRequestDto {

    @NotBlank(message = "댓글을 입력해주세요.")
    @Size(max = 50, message = "댓글은 50자 내외로 입력해주세요.")
    private String comment;
}
