package com.example.compliantApp_Backend.dao;

import com.example.compliantApp_Backend.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends CrudRepository<User,Integer> {
    @Query(value = "SELECT `id`, `email`, `name`, `password`, `phoneno` FROM `user` WHERE `email`= :email",nativeQuery = true)
    List<User> searchUser (@Param("email") String email);

    @Query(value = "SELECT `id`, `email`, `name`, `password`, `phoneno` FROM `user` WHERE `id`= :id",nativeQuery = true)
    List<User> userDetails (@Param("id") int id);

}


