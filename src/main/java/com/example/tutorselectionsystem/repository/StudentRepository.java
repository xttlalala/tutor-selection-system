package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends BaseRepository<Student,Long> {
}
