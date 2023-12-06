package com.wang.controller;

import com.wang.entity.Result;
import com.wang.entity.User;
import com.wang.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/")
    public String hello(){
        return "hello";
    }


    @PostMapping("/login")
    public Result login(@RequestBody User user){

        System.out.println("login..."+user);

        User selectUser = new User();
        if (user.getEmail() == null||user.getEmail().equals("")){
            selectUser = userMapper.queryUserByUserName(user.getUserName());
        }else if (user.getUserName() == null||user.getUserName().equals("")){
            selectUser = userMapper.queryUserByEmail(user.getEmail());
        }

        if (selectUser == null){
            return new Result(false,10001,"用户不存在",null);
        }else if (!user.getPassword().equals(selectUser.getPassword())){
            return new Result(false,10002,"密码错误",null);
        }else {
            user.setPassword("");
            return new Result(true,20000,"登录成功",selectUser);
        }
    }

    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestBody User user){
        System.out.println("register..."+user);
        if (userMapper.queryUserByUserName(user.getUserName()) != null){
            return new Result(false,10003,"用户名已存在",null);
        }else if(userMapper.queryUserByEmail(user.getEmail()) != null){
            return new Result(false,10004,"邮箱已存在",null);
        }
        userMapper.insertUser(user);
        user.setPassword("");
        return new Result(true,20000,"注册成功",user);
    }

    @PostMapping("/allUsers")
    public Result queryAllUsers(@RequestParam(defaultValue = "1") int pageNum,
                                @RequestParam(defaultValue = "10") int pageSize){
        List<User> users = userMapper.queryAllUsersByPage(pageNum-1, pageSize);

        return new Result(true,20000,"查询成功",users);
    }

    @PostMapping("/allAminUsers")
    public Result queryAllAminUsers(@RequestParam(defaultValue = "1") int pageNum,
                                @RequestParam(defaultValue = "10") int pageSize){
        List<User> users = userMapper.queryAllAminUsersByPage(pageNum-1, pageSize);

        return new Result(true,20000,"查询成功",users);
    }

    @PostMapping("/allNormalUsers")
    public Result queryAllNormalUsers(@RequestParam(defaultValue = "1") int pageNum,
                                @RequestParam(defaultValue = "10") int pageSize){
        List<User> users = userMapper.queryAllNormalUsersByPage(pageNum-1, pageSize);

        return new Result(true,20000,"查询成功",users);
    }

    @PostMapping("/searchUsersByName")
    public Result searchUsersByName(@RequestParam("userName") String userName){
        List<User> users = userMapper.searchUsersByName(userName);
        return new Result(true,20000,"查询成功",users);
    }

    @PostMapping("/getUsersCount")
    public Result getUsersCount(){
        Integer allUserCount = userMapper.queryAllUserCount();
        Integer adminUserCount = userMapper.queryAdminUserCount();
        Integer normalUserCount = userMapper.queryNormalUserCount();
        int[] userCount = {allUserCount,adminUserCount,normalUserCount};
        System.out.println("用户数量:" +Arrays.toString(userCount));
        return new Result(true, 20000,"查询成功", userCount);
    }


}
