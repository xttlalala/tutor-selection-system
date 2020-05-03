package com.example.tutorselectionsystem.service;

import com.example.tutorselectionsystem.entity.Course;
import com.example.tutorselectionsystem.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    //添加课程
    public void addCourse(Course course){
        courseRepository.save(course);
    }

}
