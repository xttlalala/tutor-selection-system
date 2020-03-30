package com.example.tutorselectionsystem.entity;

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
public class Tutor {
    @Id
    @Column(unique = true)
    private long jobNumber;
    private String name;
    private String password;
    private int maxStuNum;
    private int nowStuNum;
    private int scopeStuNum;
    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime updateTime;
    @OneToMany(mappedBy = "tutor")
    private List<Student> students;
    @OneToMany(mappedBy = "tutor")
    private List<Course>  courses;
}
