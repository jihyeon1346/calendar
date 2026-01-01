package com.calendar.repository;

import com.calendar.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Long countByCalendarId(Long id);
}
