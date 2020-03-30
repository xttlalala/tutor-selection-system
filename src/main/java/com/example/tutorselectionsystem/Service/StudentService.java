package com.example.tutorselectionsystem.Service;

import com.example.tutorselectionsystem.entity.Student;
import com.example.tutorselectionsystem.entity.Tutor;
import com.example.tutorselectionsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public void addStudent(Student student){
        studentRepository.save(student);
    }
    //方法：为指定课程添加学生，课程id，学生集合

//    public Student getStudent(long schoolNumber){
//        return studentRepository.findById(schoolNumber).orElse(null);
//    }
}
