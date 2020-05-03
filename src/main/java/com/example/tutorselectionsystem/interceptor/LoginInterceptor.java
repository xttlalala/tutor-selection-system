package com.example.tutorselectionsystem.interceptor;

import com.example.tutorselectionsystem.component.EncryptComponent;
import com.example.tutorselectionsystem.component.MyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private EncryptComponent encrypt;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Optional.ofNullable(request.getHeader(MyToken.AUTHORIZATION))
                .map(auth->encrypt.decryptToken(auth))
                .ifPresentOrElse(token -> {
                    request.setAttribute(MyToken.UID, token.getUid());//键值对
                    request.setAttribute(MyToken.ROLE, token.getRole());
                }, ()->{
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"未登录");
                });
        return true;
    }
}
