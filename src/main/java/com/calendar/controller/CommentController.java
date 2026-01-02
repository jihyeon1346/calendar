package com.calendar.controller;

import com.calendar.dto.CreateCommentRequest;
import com.calendar.dto.CreateCommentResponse;
import com.calendar.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/calendars/{calendarsId}/comments")
    public ResponseEntity<CreateCommentResponse> createComment(
            @PathVariable Long calendarsId, @RequestBody CreateCommentRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.saveComment(calendarsId, request));
    }

}
