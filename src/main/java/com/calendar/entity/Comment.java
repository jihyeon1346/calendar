package com.calendar.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String content;
    @Column(length = 50, nullable = false)
    private String userName;
    @Column(length = 50, nullable = false)
    private String password;
    @ManyToOne
    private Calendar calendar;
    public Comment(String content, String userName, String password, Calendar calendar) {
        this.content = content;
        this.userName = userName;
        this.password = password;
        this.calendar = calendar;
    }
}
