package com.example.tutorselectionsystem.component;

import com.example.tutorselectionsystem.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.rmi.server.UID;

@Component
@Slf4j
public class RequestComponent {
    public int getUid(){
        return (int)RequestContextHolder.currentRequestAttributes()
                //springmvc提供的可以直接获取当前线程的方法，直接获取当前线程所有的attribute。
                .getAttribute(MyToken.UID, RequestAttributes.SCOPE_REQUEST );
                //在请求范围内获取MyToken.UID
    }
    public User.Role getRole(){
        return (User.Role) RequestContextHolder.currentRequestAttributes()
                .getAttribute(MyToken.ROLE, RequestAttributes.SCOPE_REQUEST);
    }
}
