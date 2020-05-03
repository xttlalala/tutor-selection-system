package com.example.tutorselectionsystem.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class User {
    public enum Role{
        STUDENT,TUTOR
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique = true)
    private Integer number;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //只能反序列化为java，不能序列化为json。也就是json返回不到前端
    private String password;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Role role;
    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
}
