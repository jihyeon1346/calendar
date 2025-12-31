package com.dto;

import lombok.Getter;

@Getter
public class CreateCalendarRequest {
    private String description;
    private String userName;
    private String title;
    private String password;
}
