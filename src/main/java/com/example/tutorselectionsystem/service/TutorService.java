package com.example.tutorselectionsystem.service;


import com.example.tutorselectionsystem.entity.*;
import com.example.tutorselectionsystem.repository.CourseRepository;
import com.example.tutorselectionsystem.repository.StudentCourseRepository;
import com.example.tutorselectionsystem.repository.StudentRepository;
import com.example.tutorselectionsystem.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Encoder;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TutorService {
    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentCourseRepository studentCourseRepository;
    @Autowired
    private PasswordEncoder encoder;

    //添加老师
    public void addTutor(Tutor tutor){
        tutorRepository.save(tutor);
    }

    /**
     * 对user声明了persist/remove级联操作
     * 缺少判断该生已经被其他老师选择，可以抛个异常
     * @param s
     * @param tid
     * @return
     */
    public Student addStudent(Student s,int tid){
        Student student = Optional.ofNullable(studentRepository.find(s.getUser().getName(),s.getUser().getNumber()))
                .orElseGet(()->{
                    User u = s.getUser();
                    u.setPassword(encoder.encode(String.valueOf(s.getUser().getNumber())));
                    u.setRole(User.Role.STUDENT);
                    return s;
                });
        student.setTutor(new Tutor(tid));
        studentRepository.save(student);
        return student;
    }






}
