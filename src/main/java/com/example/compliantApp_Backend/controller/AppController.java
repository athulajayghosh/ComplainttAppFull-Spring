package com.example.compliantApp_Backend.controller;

import com.example.compliantApp_Backend.dao.UserDao;
import com.example.compliantApp_Backend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
public class AppController {
    @Autowired
    private UserDao dao;

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/adduser", consumes = "application/json", produces = "application/json")
    public User adduser(@RequestBody User u) {
        dao.save(u);
        return u;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/userlogin", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> Userlogin(@RequestBody User u) {
        System.out.println(u.getEmail());
        List<User> result = (List<User>) dao.searchUser(u.getEmail());
        HashMap<String,String> map = new HashMap<>();
        if(result.size()==0){
            map.put("status","failed");
            map.put("message","User doesn't exist");
        }
        else{
            if(Objects.equals(result.get(0).getPassword(), u.getPassword())){
                int id = result.get(0).getId();
                map.put("id",String.valueOf(id));
                map.put("status","success");
                map.put("message","User login success");

            }
            else {
                map.put("status","failed");
                map.put("message","Wrong password");
            }
        }
        return map;
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/userdetails", consumes = "application/json", produces = "application/json")
    public List<User> searchproduct(@RequestBody User u) {
        return (List<User>) dao.userDetails(u.getId());

    }

}
