package com.example.tutorselectionsystem.service;

import com.example.tutorselectionsystem.entity.Student;
import com.example.tutorselectionsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public void addStudent(Student student){
        studentRepository.save(student);
    }
}
