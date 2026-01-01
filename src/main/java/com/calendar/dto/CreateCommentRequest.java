package com.calendar.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequest {
    private String content;
    private String userName;
    private String password;
}
