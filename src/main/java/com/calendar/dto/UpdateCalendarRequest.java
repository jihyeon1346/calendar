package com.calendar.dto;

import lombok.Getter;

@Getter
public class UpdateCalendarRequest {
    private String description;
    private String userName;
    private String title;
    private String password;
}
