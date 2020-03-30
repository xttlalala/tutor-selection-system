package com.example.tutorselectionsystem.Service;

import com.example.tutorselectionsystem.entity.Direction;
import com.example.tutorselectionsystem.repository.DirectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DirectionService {
    @Autowired
    private DirectionRepository directionRepository;
    public void addDirection(Direction direction){
        directionRepository.save(direction);
    }
//    public Direction getDirection(int id){
//        return directionRepository.findById(id).orElse(null);
//    }
}
