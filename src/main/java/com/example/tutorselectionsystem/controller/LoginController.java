package com.example.tutorselectionsystem.controller;

import com.example.tutorselectionsystem.component.EncryptComponent;
import com.example.tutorselectionsystem.component.MyToken;
import com.example.tutorselectionsystem.entity.User;
import com.example.tutorselectionsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/")
public class LoginController {
    @Value("${my.student}")//@Value("${my.student}")
    private String roleStudent;
    @Value("${my.tutor}")
    private String roleTutor;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private EncryptComponent encrypt;

    @PostMapping("login")
    public Map login(@RequestBody User loginUser, HttpServletResponse response){//直接在方法中注入HttpServletResponse response对象
        User user = Optional.ofNullable(userService.getUser(loginUser.getNumber()))//判断其是否为空，不为空进入filter
                .filter(u -> encoder.matches(loginUser.getPassword(),u.getPassword()))//过滤 返回ture则跳出
                .orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"用户名或密码错误"));
        MyToken token  = new MyToken(user.getId(), user.getRole());
        String auth = encrypt.encryptToken(token);
        response.setHeader(MyToken.AUTHORIZATION, auth);//以键值对形式放入头中
        log.debug("{}", "登陆成功");
        String roleCode = user.getRole()== User.Role.TUTOR?roleTutor:roleStudent;
        return Map.of("role",roleCode);//告诉前端你是什么身份，前端渲染不同界面
    }
}