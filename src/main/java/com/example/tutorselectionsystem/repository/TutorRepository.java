package com.example.tutorselectionsystem.repository;

import com.example.tutorselectionsystem.entity.Student;
import com.example.tutorselectionsystem.entity.Tutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends BaseRepository<Tutor,Long>{
    @Modifying
    @Query("update Tutor t set t.password=:password where t.jobNumber=:jobNumber")
    int updatePassword(@Param("jobNumber")long jobNumber,@Param("password") String password);

    @Modifying
    @Query("update Tutor t set t.maxStuNum=:maxStuNum,t.scopeStuNum=:scopeStuNum where t.jobNumber=:jobNumber")
    int updateMaxStuNum(@Param("jobNumber")long jobNumber,@Param("maxStuNum") int maxStuNum,@Param("scopeStuNum")int scopeStuNum);

//    public void addSpecifiedStudent(Long studentNumber){
//        @Query("from Student s where s.studentNumber=:studentNumber")
//        Student findSpecStudent(@Param("studentNumber") long studentNumber);
//
//    }
}
