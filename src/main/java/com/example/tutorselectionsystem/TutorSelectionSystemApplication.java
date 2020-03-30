package com.example.tutorselectionsystem;

import com.example.tutorselectionsystem.repository.impl.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)//实现类
public class TutorSelectionSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorSelectionSystemApplication.class, args);
    }

}
