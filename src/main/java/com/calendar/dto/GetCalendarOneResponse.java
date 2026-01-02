package com.calendar.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetCalendarOneResponse {
    private final Long id;
    private final String userName;
    private final String description;
    private final String title;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final List<GetCommentResponse> comments;
    public GetCalendarOneResponse(Long id, String userName, String description, String title,
                                  LocalDateTime createdAt, LocalDateTime modifiedAt, List<GetCommentResponse> comments) {
        this.id = id;
        this.userName = userName;
        this.description = description;
        this.title = title;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.comments = comments;
    }
}
