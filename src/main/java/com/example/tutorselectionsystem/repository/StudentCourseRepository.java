package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.Course;
import com.example.tutorselectionsystem.entity.StudentCourse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseRepository extends BaseRepository<StudentCourse,Integer> {
     void deleteAllByCourse_Id(int id);
     List<StudentCourse> findAllByCourse(Course course);

    @Query("SELECT sc FROM StudentCourse sc WHERE sc.course.id=:cid")
    StudentCourse getSC(@Param("cid")int cid);

    @Query("SELECT sc FROM StudentCourse sc WHERE sc.student.user.id=:sid")
    List<StudentCourse> findStudent(@Param("sid")int sid);
}
