package com.example.tutorselectionsystem.Service;

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
    public void addCourse(Course course){
        courseRepository.save(course);
    }
//    public Course getCourse(int id){
//        return courseRepository.findById(id).orElse(null);
//    }
}
