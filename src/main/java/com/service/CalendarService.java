package com.service;

import com.dto.CreateCalendarRequest;
import com.dto.CreateCalendarResponse;
import com.entity.Calendar;
import com.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
