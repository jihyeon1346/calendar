package com.calendar.service;

import com.calendar.dto.*;
import com.calendar.entity.Calendar;
import com.calendar.entity.Comment;
import com.calendar.repository.CalendarRepository;
import com.calendar.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CreateCalendarResponse save(CreateCalendarRequest request) {
        Calendar calendar = new Calendar(
                request.getTitle(),
                request.getDescription(),
                request.getUserName(),
                request.getPassword()
                );
        Calendar savedCalendar = calendarRepository.save(calendar);
        return new CreateCalendarResponse(
                savedCalendar.getId(),
                savedCalendar.getUserName(),
                savedCalendar.getDescription(),
                savedCalendar.getTitle(),
                savedCalendar.getCreatedAt(),
                savedCalendar.getModifiedAt());
    }

    @Transactional(readOnly = true)
    public List<GetCalendarsResponse> findAll(String userName) {
        List<Calendar> calendars;
        if (userName != null) {
            calendars = calendarRepository.findAllByUserNameOrderByModifiedAtDesc(userName);
        } else {
            calendars = calendarRepository.findAllByOrderByModifiedAtDesc();
        }
        List<GetCalendarsResponse> dtos = new ArrayList<>();
        for(Calendar calendar : calendars) {
            GetCalendarsResponse response = new GetCalendarsResponse(
                    calendar.getId(),
                    calendar.getUserName(),
                    calendar.getDescription(),
                    calendar.getTitle(),
                    calendar.getCreatedAt(),
                    calendar.getModifiedAt()

            );
            dtos.add(response);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public GetCalendarOneResponse findOne(Long calendarId) {
        Calendar calendar = calendarRepository.findById(calendarId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );
        List<Comment> comments = commentRepository.findAllByCalendarId(calendarId);
        List<GetCommentResponse> commentResponses = comments.stream().map
                        (comment -> new GetCommentResponse(
                        comment.getId(),
                        comment.getContent(),
                        comment.getUserName(),
                        comment.getCreatedAt(),
                        comment.getModifiedAt()
                )).collect(Collectors.toList());
        return new GetCalendarOneResponse(
                calendar.getId(),
                calendar.getUserName(),
                calendar.getDescription(),
                calendar.getTitle(),
                calendar.getCreatedAt(),
                calendar.getModifiedAt(),
                commentResponses
        );
    }

    @Transactional
    public UpdateCalendarResponse update(Long calendarsId, UpdateCalendarRequest request) {
        Calendar calendar = calendarRepository.findById(calendarsId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다."));
        calendar.update(
                request.getTitle(),
                request.getUserName(),
                request.getPassword()
        );


        return new UpdateCalendarResponse(
                calendar.getId(),
                calendar.getUserName(),
                calendar.getDescription(),
                calendar.getTitle(),
                calendar.getCreatedAt(),
                calendar.getModifiedAt()
        );

    }

    @Transactional
    public void delete(Long calendarsId) {
        boolean existence = calendarRepository.existsById(calendarsId);
        if (!existence) {
            throw new IllegalStateException("없는 일정입니다.");
        }
        calendarRepository.deleteById(calendarsId);
    }
}
