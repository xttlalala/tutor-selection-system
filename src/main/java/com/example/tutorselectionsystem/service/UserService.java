package com.example.tutorselectionsystem.service;

import com.example.tutorselectionsystem.entity.*;
import com.example.tutorselectionsystem.repository.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Data
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private DirectionRepository directionRepository;
    @Autowired
    private StudentCourseRepository studentCourseRepository;
//    public User getUser(Integer id){
//        return userRepository.findById(id).orElse(null);
//    }
    public User  updatePwd(int number,String password){
        User u = userRepository.findById(number);
        u.setPassword(password);
        return u;
    }
    public User getUser(int number){
        return userRepository.getUser(number);
    }


    public Tutor addTutor(Tutor tutor){//使用级联操作之后
        return tutorRepository.save(tutor);
    }

//    public Tutor addTutor(User user,Tutor tutor){//使用级联操作之前
//        userRepository.save(user);
//        tutor.setUser(user);
//        return tutorRepository.save(tutor);
//    }
    public Tutor getTutor(int tid){
        return tutorRepository.findById(tid).orElse(null);
    }
    public Student getStudent(int sid){return studentRepository.findById(sid).orElse(null);
    }

    public Tutor updateTutor(int maxStuNum,int scopeStuNum,int tid){
        Tutor t = tutorRepository.findById(tid).orElseThrow();
        t.setMaxStuNum(maxStuNum);
        t.setScopeStuNum(scopeStuNum);
        return t;

    }
    public List<Direction> getDirections(){
        return directionRepository.findAll();
    }

    public void chooseTutor(Tutor t,int sid){
        List<StudentCourse> scs = studentCourseRepository.findStudent(sid);

    }
}
