package com.example.tutorselectionsystem.Service;


import com.example.tutorselectionsystem.entity.Tutor;
import com.example.tutorselectionsystem.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TutorService {
    @Autowired
    private TutorRepository tutorRepository;
    public void addTutor(Tutor tutor){
        tutorRepository.save(tutor);
    }
//    public Tutor getTutor(long jobNumber){
//        return tutorRepository.findById(jobNumber).orElse(null);
//    }

}
