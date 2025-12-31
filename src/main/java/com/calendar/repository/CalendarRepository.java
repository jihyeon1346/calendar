package com.calendar.repository;

import com.calendar.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    List<Calendar> findAllByUserNameOrderByModifiedAtDesc(String userName); //작성자명으로 필터링 내림차순

    List<Calendar> findAllByOrderByModifiedAtDesc();// 전체조회 내림차순
}
