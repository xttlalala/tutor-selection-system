package com.example.tutorselectionsystem;

import com.example.tutorselectionsystem.repository.impl.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)//实现类
public class TutorSelectionSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorSelectionSystemApplication.class, args);
    }

    @Bean//直接将passwordEncoder组件注入容器，容器中大家一起用
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
