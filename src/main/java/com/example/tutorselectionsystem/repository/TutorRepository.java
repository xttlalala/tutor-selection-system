package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.Student;
import com.example.tutorselectionsystem.entity.Tutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends BaseRepository<Tutor,Integer>{
    //修改指定教师密码
//    @Modifying
//    @Query("update Tutor t set t.password=:password where t.id=:id")
//    int updatePassword(@Param("id")int id,@Param("password") String password);
//    //修改指定教师的最大学生数宇与最大范围数
//    @Modifying
//    @Query("update Tutor t set t.maxStuNum=:maxStuNum,t.scopeStuNum=:scopeStuNum where t.id=:id")
//    int updateMaxStuNum(@Param("id")long id,@Param("maxStuNum") int maxStuNum,@Param("scopeStuNum")int scopeStuNum);


}
