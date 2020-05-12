package com.example.tutorselectionsystem.controller;

import com.example.tutorselectionsystem.component.MyToken;
import com.example.tutorselectionsystem.component.RequestComponent;
import com.example.tutorselectionsystem.entity.*;
import com.example.tutorselectionsystem.service.CourseService;
import com.example.tutorselectionsystem.service.TutorService;
import com.example.tutorselectionsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api/tutor/")
public class TutorController {
    @Autowired
    private RequestComponent requestComponent;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private TutorService tutorService;

    @GetMapping("welcome")
    public void getIndex(HttpServletRequest request, @RequestAttribute(MyToken.UID) int uid){
        //获得request请求信息
        log.debug("{}",(int)request.getAttribute(MyToken.UID));//获取值方法一 从request对象上找
        log.debug("{}",uid);//获取值方法二 使用注解注入属性
        log.debug("{}",requestComponent.getUid());
    }

    @GetMapping("index")
    public Map getTutor(){
        Tutor t = userService.getTutor(requestComponent.getUid());
                return Map.of(
                "tutor",t,
                "course",t.getCourses(),//springmvc允许延迟加载的get方法
                "students",t.getStudents()
        );
    }
    //计算学生
    @GetMapping("excuteStudent")
    public Map excuteStudent(){
        Tutor t = userService.getTutor(requestComponent.getUid());
        List<Integer> studentNumbers = tutorService.excuteStudent(t);
        int num = studentNumbers.size();
        User[] users = new User[num];
        for(int i=0;i<num;i++){
            users[i] = userService.getUser(studentNumbers.get(i));
        }
        log.debug("{}", users);
        return Map.of("studentUsers",users);
    }

    //建立学生 学生-课程-成绩关联
//    @PostMapping("buildStudent")
//    public void buildStudent(@RequestBody StudentCourse sc){
//        log.debug("{}", sc.getCourse().getId());
//        log.debug("{}", sc.getStudent().getUser().getNumber());
//    }
    @PostMapping("buildStudent")
    public Map buildStudent(@RequestBody List<StudentCourse> studentCourses){
        log.debug("{}", studentCourses.get(1));
        tutorService.buildStudents(studentCourses);
        return Map.of("result",1);
    }

    //导师增加课程
    @PostMapping("addcourse")
    public Map postCourse(@RequestBody Course course){
        course.setTutor(new Tutor(requestComponent.getUid()));
        courseService.addCourse(course);
        return Map.of("course",course);
    }
    @PatchMapping("updateCourse")
    public Map updateCourse(@RequestBody Course c){
        Course course = tutorService.updateCourse(c.getId(),c.getName(),c.getWeight());
        return Map.of("course",course);
    }

    //导师修改范围数和最大学生数
    @PatchMapping("settings")
    public Map patchSettings(@RequestBody Tutor t){
        Tutor t1 = userService.updateTutor(t.getMaxStuNum(),t.getScopeStuNum(),requestComponent.getUid());
        return Map.of("tutor",t1);
    }
    //添加预先联系好的学生
    @PostMapping("addStudent")
    public Map postStudent(@RequestBody Student s){
        Student student = tutorService.addStudent(s,requestComponent.getUid());
        return Map.of("student",s);
    }
    //修改全部方向
    @PostMapping("updateDirections")
    public Map updateDirections(@RequestBody List<Direction> newDirections){
        tutorService.updateDirections(newDirections);
        return Map.of("result",1);
    }
    //删除课程
    @PostMapping("deleteCourse")
    public Map deleteCourse(@RequestBody Course c){
//        Tutor t = userService.getTutor(requestComponent.getUid());
        tutorService.deleteCourse(c.getId());
        return Map.of("result",1);
    }



}

