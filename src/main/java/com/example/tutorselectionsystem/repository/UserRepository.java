package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends BaseRepository<User,Integer> {

    @Query("SELECT u FROM User u WHERE u.number=:number")
    User getUser(@Param("number") int number);

    User findById(int id);
//    修改用户密码 没用上
    @Modifying
    @Query("update User u set u.password=:password where u.number=:number")
    int updatePassword(@Param("number")int number,@Param("password") String password);
}

