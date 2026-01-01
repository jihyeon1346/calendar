package com.calendar.service;

import com.calendar.dto.CreateCommentRequest;
import com.calendar.dto.CreateCommentResponse;
import com.calendar.entity.Calendar;
import com.calendar.entity.Comment;
import com.calendar.repository.CalendarRepository;
import com.calendar.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CalendarRepository calendarRepository;

    @Transactional
    public CreateCommentResponse saveComment(Long calendarsId, CreateCommentRequest request) {


        Calendar calendar = calendarRepository.findById(calendarsId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다."));

        Long commentCount = commentRepository.countByCalendarId(calendar.getId());
        if (commentCount >= 10) {
            throw new IllegalStateException("댓글은 10개 까지 작성 가능합니다.");
        }
        Comment comment = new Comment(
                request.getContent(),
                request.getUserName(),
                request.getPassword(),
                calendar
        );

        Comment savedComment = commentRepository.save(comment);
                return new CreateCommentResponse(
                        savedComment.getId(),
                        savedComment.getContent(),
                        savedComment.getUserName(),
                        savedComment.getModifiedAt(),
                        savedComment.getCreatedAt()
                );
    }
}
