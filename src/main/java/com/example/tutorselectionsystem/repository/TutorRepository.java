package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.Tutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends BaseRepository<Tutor,Long>{
//    @Modifying
//    @Query("update Tutor t set t.password=:password where t.jobNumber=:jobNumber")
//    int update(@Param("jobNumber")long jobNumber,@Param("password") String password);

}
