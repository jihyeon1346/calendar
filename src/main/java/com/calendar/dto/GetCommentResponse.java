package com.calendar.dto;

import jakarta.persistence.Column;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetCommentResponse {
    private final Long id;
    private final String userName;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public GetCommentResponse(Long id, String userName, String content,
                              LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userName = userName;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
