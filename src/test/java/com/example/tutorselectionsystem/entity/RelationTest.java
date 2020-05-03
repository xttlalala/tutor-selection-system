package com.example.tutorselectionsystem.entity;

import com.example.tutorselectionsystem.service.CourseService;
import com.example.tutorselectionsystem.service.DirectionService;
import com.example.tutorselectionsystem.service.StudentService;
import com.example.tutorselectionsystem.service.TutorService;
import com.example.tutorselectionsystem.repository.CourseRepository;
import com.example.tutorselectionsystem.repository.DirectionRepository;
import com.example.tutorselectionsystem.repository.TutorRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Autowired
    public TutorRepository tutorRepository;
    @Autowired
    public DirectionRepository directionRepository;
    @Autowired
    public CourseRepository courseRepository;

    //添加导师


    //添加毕设方向
    @Test
    public void test_init_direction(){
        Direction d = new Direction();
        d.setName("人工智能");
        directionService.addDirection(d);
        Direction d2 = new Direction();
        d2.setName("机器学习");
        directionService.addDirection(d2);
        Direction d3 = new Direction();
        d3.setName("软件开发");
        directionService.addDirection(d3);
        Direction d4 = new Direction();
        d4.setName("微信小程序");
        directionService.addDirection(d4);
        Direction d5 = new Direction();
        d5.setName("自然语言处理");
        directionService.addDirection(d5);
        Direction d6 = new Direction();
        d6.setName("图像处理");
        directionService.addDirection(d6);
    }

    //添加课程
    @Test
    public void test_init_course(){
        Course c = new Course();
        c.setName("Java程序设计");
        c.setWeight(0.4);
        courseService.addCourse(c);
        Course c2 = new Course();
        c2.setName("web系统框架");
        c2.setWeight(0.3);
        courseService.addCourse(c2);
        Course c3 = new Course();
        c3.setName("web开发设计");
        c3.setWeight(0.2);
        courseService.addCourse(c3);
        Course c4 = new Course();
        c4.setName("数据库设计");
        c4.setWeight(0.1);
        courseService.addCourse(c4);
    }
    //
//    @Test
//    public void add_student_forCourse(){
//        tutorService.addSpecifiedStudent((long)2017224411,(long)2017224420);
//    }



    //修改指定老师密码
//    @Test
//    public void update_tutor_password(){
//        tutorRepository.updatePassword(1,"hello");
//    }
//    //修改指定老师最大招收学生数和报名学生范围数
//    @Test
//    public void update_tutor_maxStudentNumber(){
//        tutorRepository.updateMaxStuNum(2017224411,10,60);
//    }
//








}

