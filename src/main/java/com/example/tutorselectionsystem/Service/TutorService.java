package com.example.tutorselectionsystem.Service;


import com.example.tutorselectionsystem.entity.Student;
import com.example.tutorselectionsystem.entity.Tutor;
import com.example.tutorselectionsystem.repository.StudentRepository;
import com.example.tutorselectionsystem.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TutorService {
    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private StudentRepository studentRepository;

    public void addTutor(Tutor tutor){
        tutorRepository.save(tutor);
    }
//    //添加指定学生
//    public void addSpecifiedStudent(Long jobNumber,Long studentNumber){
//        Student student =  studentRepository.findById(studentNumber).orElse(null);
//        Tutor tutor = tutorRepository.findById(jobNumber).orElse(null);
//
//        studentRepository.save(student);
//    }

//    public Tutor getTutor(long jobNumber){
//        return tutorRepository.findById(jobNumber).orElse(null);
//    }

}
