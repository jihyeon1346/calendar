package com.controller;

import com.dto.CreateCalendarRequest;
import com.dto.CreateCalendarResponse;
import com.repository.CalendarRepository;
import com.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    @PostMapping("/calendars")
    public ResponseEntity<CreateCalendarResponse> create(
            @RequestBody CreateCalendarRequest request
            ){
        return ResponseEntity.status(HttpStatus.CREATED).body(calendarService.save(request));
    }
}
