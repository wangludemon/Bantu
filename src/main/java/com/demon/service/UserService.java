package com.demon.service;

import com.demon.mapper.UserMapper;
import com.demon.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    //登陆判断
    //教师1  学生0
    public int loginJudge(String username,String password){
        User user = userMapper.queryUserById(username);
        if(user != null)
        {
            if(user.getPassword().equals(password) )
            {
                if(user.getIdentity()==1)
                    return 1;  //教师
                else
                    return 2;  //学生
            } else
                return 0;
        }else
        {
            return 0;
        }
    }

    //注册
    public boolean register(User user){
        if(userMapper.queryUserById(user.getUsername()) != null){
            return false;
        }else{
            userMapper.addUser(user);
            return true;
        }
    }



}
