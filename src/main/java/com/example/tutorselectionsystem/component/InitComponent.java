package com.example.tutorselectionsystem.component;

import com.example.tutorselectionsystem.entity.Tutor;
import com.example.tutorselectionsystem.entity.User;
import com.example.tutorselectionsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//系统初始化之后调用该组件
@Component
@Slf4j
public class InitComponent implements InitializingBean {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserService userService;
    //完成系统最初的管理员基本的账号的添加
    @Override
    public void afterPropertiesSet() throws Exception {
        int number = 1001;
        int number2 = 1002;
        User user = userService.getUser(number);
        User user2 = userService.getUser(number2);
        if(user ==null){
            User u = new User();
            u.setName("BO");
            u.setNumber(number);
            u.setRole(User.Role.TUTOR);
            u.setPassword(encoder.encode(String.valueOf(number)));

            Tutor t = new Tutor();//注意：tutor和user是组合关系，
            // 直接持久化tutor是不行的 save User之后才能save Tutor

//            userService.addTutor(u,t);//也可以使用级联的方法
            t.setUser(u);
            userService.addTutor(t);
        }
        if(user2 ==null){
            User u = new User();
            u.setName("Lily");
            u.setNumber(number2);
            u.setRole(User.Role.TUTOR);
            u.setPassword(encoder.encode(String.valueOf(number)));

            Tutor t = new Tutor();//注意：tutor和user是组合关系，
            // 直接持久化tutor是不行的 save User之后才能save Tutor

//            userService.addTutor(u,t);//也可以使用级联的方法
            t.setUser(u);
            userService.addTutor(t);
        }


    }
}
