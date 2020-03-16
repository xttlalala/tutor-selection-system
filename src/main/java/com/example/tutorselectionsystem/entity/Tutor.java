package com.example.tutorselectionsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Entity
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    private int maxStuNum;
    private int nowStuNum;
    private Double CourseWeight;
    private Double DirectionWeight;
    @OneToMany(mappedBy = "tutor")
    private List<Student> students;
}
