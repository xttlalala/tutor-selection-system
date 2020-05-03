package com.example.tutorselectionsystem.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class EncryptComponent {
    @Autowired
    private ObjectMapper objectMapper;//注入jackson对象，注入序列化组件
    @Value("${my.secretkey}")//@Value("${my.secretkey}")从配置里拿到密钥
    private String secretkey;
    @Value("${my.salt}")
    private String salt;
    @Autowired
    private TextEncryptor encryptor;//文本加密器对象

    /**
     * 直接基于密钥/盐值创建单例 TextEncryptor对象（文本加密器），避免反复创建。
     */
    @Bean
    public TextEncryptor getTextEncryptor(){
        return Encryptors.text(secretkey, salt);
    }

    /**
     * 加密算法，无法加密则是服务器（我们)的问题
     * @param token
     * @return
     */
    public String encryptToken(MyToken token){
        try{
            String json = objectMapper.writeValueAsString(token);
            return encryptor.encrypt(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "服务器端错误");
        }
    }

    /**
     * 无法验证/解密/反序列化，说明数据被篡改，提示无权限
     * @param auth
     * @return
     */
    public MyToken decryptToken(String auth){
        try{
            String json = encryptor.decrypt(auth);
            return objectMapper.readValue(json,MyToken.class);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "无权限");
        }
    }

}
