package com.example.tutorselectionsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"studentCourses"})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @MapsId
    private User user; //依靠单向@OneToOne和@MapsId，与父表共享主键
    private String mydirection;
    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime updateTime;
    @OneToMany(mappedBy = "student")
    private List<StudentCourse> studentCourses;
    @ManyToOne
    private Tutor tutor;


}
