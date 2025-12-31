package com.calendar.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetCalendarResponse {
    private final Long id;
    private final String userName;
    private final String description;
    private final String title;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetCalendarResponse(Long id, String userName, String description, String title, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userName = userName;
        this.description = description;
        this.title = title;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
