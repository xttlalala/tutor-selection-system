package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.Direction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DirectionRepository extends BaseRepository<Direction,Integer> {
//    Optional<List<Direction>> findAllByStudentId(int sid);
}
