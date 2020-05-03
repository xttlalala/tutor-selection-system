package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends BaseRepository<Course,Integer>{
//    Optional<List<Course>> findAllByTutorId(int tid);
}
