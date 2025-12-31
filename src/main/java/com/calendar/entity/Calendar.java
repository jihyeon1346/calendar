package com.calendar.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "calendars")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Calendar extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String title;
    private String description;
    @Column(length = 50, nullable = false)
    private String userName;
    @Column(length = 50, nullable = false)
    private String password;


    public Calendar(String title, String description, String userName, String password) {
        this.title = title;
        this.description = description;
        this.userName = userName;
        this.password = password;
    }

    public void update(String title, String description, String userName, String password){
        this.title = title;
        this.description = description;
        this.userName = userName;
        this.password = password;
    }


}
