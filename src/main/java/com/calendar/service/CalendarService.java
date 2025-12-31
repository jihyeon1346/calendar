package com.calendar.service;

import com.calendar.dto.*;
import com.calendar.entity.Calendar;
import com.calendar.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class CalendarService {
    private final CalendarRepository calendarRepository;

    @Transactional
    public CreateCalendarResponse save(CreateCalendarRequest request) {
        Calendar calendar = new Calendar(
                request.getTitle(),
                request.getDescription(),
                request.getUserName(),
                request.getPassword());
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
    public List<GetCalendarResponse> findAll(String userName) {
            List<Calendar> calendars;
        if (userName != null) {
            calendars = calendarRepository.findAllByUserNameOrderByModifiedAtDesc(userName);
        } else {
            calendars = calendarRepository.findAllByOrderByModifiedAtDesc();
        }
        List<GetCalendarResponse> dtos = new ArrayList<>();
        for(Calendar calendar : calendars) {
            GetCalendarResponse response = new GetCalendarResponse(
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
    public GetCalendarResponse findOne(Long calendarId) {
        Calendar calendar = calendarRepository.findById(calendarId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );

        return new GetCalendarResponse(
                calendar.getId(),
                calendar.getUserName(),
                calendar.getDescription(),
                calendar.getTitle(),
                calendar.getCreatedAt(),
                calendar.getModifiedAt()
        );
    }

    @Transactional
    public UpdateCalendarResponse update(Long calendarsId, UpdateCalendarRequest request) {
        Calendar calendar = calendarRepository.findById(calendarsId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다."));
        calendar.update(
                request.getUserName(),
                request.getDescription(),
                request.getTitle(),
                request.getPassword());

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
