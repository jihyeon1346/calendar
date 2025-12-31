package com.controller;

import com.dto.CreateCalendarRequest;
import com.dto.CreateCalendarResponse;
import com.dto.GetCalendarResponse;
import com.repository.CalendarRepository;
import com.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    @PostMapping("/calendars")
    public ResponseEntity<CreateCalendarResponse> create(
            @RequestBody CreateCalendarRequest request
            ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(calendarService.save(request));
    }

    @GetMapping("/calendars")
    public ResponseEntity<List<GetCalendarResponse>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(calendarService.findAll());
    }

    @GetMapping("/calendars/{calendars{id}")
    public ResponseEntity<GetCalendarResponse> getOne(@PathVariable("calendars") Long calendarsId){
        return ResponseEntity.status(HttpStatus.OK).body(calendarService.findOne(calendarsId));
    }
}
