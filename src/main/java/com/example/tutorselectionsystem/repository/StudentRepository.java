package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends BaseRepository<Student,Long> {
    //方法：指定教师修改他已有学生的方向

}

