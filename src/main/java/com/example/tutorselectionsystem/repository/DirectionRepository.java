package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.Direction;

import org.springframework.stereotype.Repository;


@Repository
public interface DirectionRepository extends BaseRepository<Direction,Integer> {
//    Optional<List<Direction>> findAllByStudentId(int sid);
}
