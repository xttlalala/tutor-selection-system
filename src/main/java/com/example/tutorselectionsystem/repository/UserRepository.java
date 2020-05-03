package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends BaseRepository<User,Integer> {
    Optional<User> findById(Integer id);

    @Query("SELECT u FROM User u WHERE u.number=:number")
    User getUser(@Param("number") int number);
}