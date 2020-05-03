package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.Student;
import com.example.tutorselectionsystem.entity.StudentCourse;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepository extends BaseRepository<StudentCourse,Integer> {
}
