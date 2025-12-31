package com.service;

import com.dto.CreateCalendarRequest;
import com.dto.CreateCalendarResponse;
import com.dto.GetCalendarResponse;
import com.entity.Calendar;
import com.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
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
        Calendar calendar = new Calendar(request.getUserName(), request.getDescription(),
                request.getTitle(),  request.getPassword());
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
    public List<GetCalendarResponse> findAll() {
        List<Calendar> calendars = calendarRepository.findAll();
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
}
