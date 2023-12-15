package com.wang.controller;

import com.wang.entity.Game;
import com.wang.entity.Result;
import com.wang.entity.User;
import com.wang.mapper.GameMapper;
import com.wang.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    @Resource
    private UserMapper userMapper;
    @Resource
    private GameMapper gameMapper;

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

    //签到接口
    @PostMapping("/mark")
    public Result mark(@RequestParam("id") int id){
        System.out.println("签到用户id: "+id);

        // 获取当前时间
        LocalDate now = LocalDate.now();
        User user = userMapper.queryUserByUserId(id);
        LocalDate lastSignTime = user.getMarkTime().toLocalDate();


        // 比较当前时间和上次签到时间是否同一天
        if (now != lastSignTime) {
            userMapper.updateScoreById(10,id); //增加积分
            userMapper.updateMarkTimeById(LocalDateTime.now(), id); //更新签到时间

            //返回新的user对象
            User user1 = userMapper.queryUserByUserId(id);
            return new Result(true,20000,"签到成功",user1);
        }
        return new Result(false,10001,"签到失败，今日已经签到！",null);
    }

    @PostMapping("/buyGame")
    public Result buyGameById(@RequestParam("gameId") int gameId,
                              @RequestParam("userId") int userId){
        Game game = gameMapper.queryGameById(gameId);
        userMapper.updateScoreById(-game.getPrince(), userId);
        User user = userMapper.queryUserByUserId(userId);
        return new Result(true,20000,"购买成功",user);
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

    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user){
        System.out.println("接收到user: "+user);
        int insertNums = userMapper.insertUser(user);
        if (insertNums > 0){
            return new Result(true,20000,"添加成功",null);
        }else {
            return new Result(false,10001,"添加失败",null);
        }
    }

    @PostMapping("/deleteUserById")
    public Result deleteUserById(@RequestParam("id") int id){
        System.out.println("接收到id: "+id);
        int deleteNums = userMapper.deleteUserById(id);
        if (deleteNums > 0){
            return new Result(true,20000,"删除成功",null);
        }else {
            return new Result(false,10001,"删除失败",null);
        }
    }

    @PostMapping("/modifyUser")
    public Result modifyUser(@RequestBody User user){
        System.out.println("modeify: "+user);
        Integer setNums = userMapper.updateUserById(user);
        if (setNums > 0){
            return new Result(true,20000,"修改成功",null);
        }else {
            return new Result(false,10001,"修改失败",null);
        }
    }


}
