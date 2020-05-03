package com.example.tutorselectionsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T,ID> extends JpaRepository<T,ID> {
    T refresh(T t);//强制从数据库里把数据拉回来
    //现在有方法可以直接套refresh使用了
}
