package com.example.tutorselectionsystem.entity;

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
    private EntityManager manager;
    @Test
    public void test_init(){
        Tutor t = new Tutor();
        t.setName("BO");
        t.setPassword("root");
        t.setCourseWeight(0.7);
        t.setDirectionWeight(0.3);
        t.setMaxStuNum(10);
        t.setNowStuNum(0);
        manager.persist(t);

        Student s = new Student();
        s.setName("Tong");
        manager.persist(s);

        Student s2 = new Student();
        s2.setName("SUN");
        manager.persist(s2);

        Course c = new Course();
        c.setName("Math");
        c.setWeight(0.3);
        manager.persist(c);

        Course c2 = new Course();
        c2.setName("Web");
        c2.setWeight(0.4);
        manager.persist(c2);

        Course c3 = new Course();
        c3.setName("English");
        c3.setWeight(0.3);
        manager.persist(c3);

        Direction d = new Direction();
        d.setName("软件开发");
        d.setWeight(0.6);
        manager.persist(d);

        Direction d2 = new Direction();
        d2.setName("自然语言处理");
        d2.setWeight(0.4);
        manager.persist(d2);



    }

    @Test
    public void test_rel(){
        Student s = manager.find(Student.class, 1);
        Course c = manager.find(Course.class,2);
        StudentCourse sc = new StudentCourse();
        sc.setScore(96);
        sc.setCourse(c);
        sc.setStudent(s);
        manager.persist(sc);
    }

    @Test
    public void test_rel2(){
        Student s = manager.find(Student.class, 1);
        Course c = manager.find(Course.class,1);
        StudentCourse sc = new StudentCourse();
        sc.setScore(92);
        sc.setCourse(c);
        sc.setStudent(s);
        manager.persist(sc);
    }

    @Test
    public void test_rel3(){
        Student s = manager.find(Student.class, 2);
        Course c = manager.find(Course.class,1);
        StudentCourse sc = new StudentCourse();
        sc.setScore(90);
        sc.setCourse(c);
        sc.setStudent(s);
        manager.persist(sc);
    }
    //选择导师
    @Test
    public void test_rel4(){
        Student s = manager.find(Student.class, 1);
        Tutor t = manager.find(Tutor.class, 1);
        s.setTutor(t);

    }

}

