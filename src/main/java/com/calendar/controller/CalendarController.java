package com.calendar.controller;


import com.calendar.dto.*;
import com.calendar.service.CalendarService;
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
    public ResponseEntity<List<GetCalendarsResponse>> getAll(@RequestParam(required = false) String userName) {
        return ResponseEntity.status(HttpStatus.OK).body(calendarService.findAll(userName));
    }

    @GetMapping("/calendars/{calendarsId}")
    public ResponseEntity<GetCalendarOneResponse> getOne(@PathVariable Long calendarsId){
        return ResponseEntity.status(HttpStatus.OK).body(calendarService.findOne(calendarsId));
    }

    @PutMapping("/calendars/{calendarsId}")
    public ResponseEntity<UpdateCalendarResponse> update(
            @PathVariable Long calendarsId,
            @RequestBody UpdateCalendarRequest request)
            {
        return ResponseEntity.status(HttpStatus.OK).body(calendarService.update(calendarsId, request));
    }

    @DeleteMapping("/calendars/{calendarsId}")
    public void delete(@PathVariable Long calendarsId){
        calendarService.delete(calendarsId);
    }
}
