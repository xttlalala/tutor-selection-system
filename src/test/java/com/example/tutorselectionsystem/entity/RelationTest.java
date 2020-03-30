package com.example.tutorselectionsystem.entity;

import com.example.tutorselectionsystem.Service.CourseService;
import com.example.tutorselectionsystem.Service.DirectionService;
import com.example.tutorselectionsystem.Service.StudentService;
import com.example.tutorselectionsystem.Service.TutorService;
import com.example.tutorselectionsystem.repository.TutorRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
@Slf4j
@Rollback(value = false)
public class RelationTest {
    @Autowired
    public TutorService tutorService;
    @Autowired
    public StudentService studentService;
    @Autowired
    public CourseService courseService;
    @Autowired
    public DirectionService directionService;
//    @Autowired
//    public TutorRepository tutorRepository;

    @Test
    public void test_init_tutor() {
        Tutor t = new Tutor();
        t.setJobNumber(2017224411);
        t.setName("BO");
        t.setPassword("root");
        t.setMaxStuNum(10);
        t.setNowStuNum(0);
        tutorService.addTutor(t);
    }

//    @Test
//    public void test_add_reportCard(){
//        Student s = new Student();
//        s.setName("Tong");
//    }

//    @Test
//    public void update_tutor_password(){
//        tutorRepository.update(2017224411,"hello");
//    }



}

