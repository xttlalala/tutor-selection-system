package com.example.tutorselectionsystem.controller;

import com.example.tutorselectionsystem.component.MyToken;
import com.example.tutorselectionsystem.component.RequestComponent;
import com.example.tutorselectionsystem.entity.Course;
import com.example.tutorselectionsystem.entity.Student;
import com.example.tutorselectionsystem.entity.Tutor;
import com.example.tutorselectionsystem.service.CourseService;
import com.example.tutorselectionsystem.service.TutorService;
import com.example.tutorselectionsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Console;
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

    //导师增加课程
    @GetMapping("courses")
    public Map postCourse(@RequestBody Course course){
        course.setTutor(new Tutor(requestComponent.getUid()));
        courseService.addCourse(course);
        return Map.of("course",course);
    }
    //导师修改范围数和最大学生数
    @PatchMapping("settings")
    public Map patchSettings(@RequestBody Tutor t){
        Tutor t1 = userService.updateTutor(t.getMaxStuNum(),t.getScopeStuNum(),requestComponent.getUid());
        return Map.of("tutor",t1);
    }
    //添加预先联系好的学生
    @PostMapping("students")
    public Map postStudent(@RequestBody Student s){
        Student student = tutorService.addStudent(s,requestComponent.getUid());
        return Map.of("student",s);
    }


}
