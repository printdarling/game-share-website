package com.wang.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")
public class User {
    @TableId(type = IdType.AUTO)
    private int id;
    private String userName;    //用户名
    private String password;    //密码
    private String email;   //邮箱
    private int score;      //积分
    private int role;    //是否管理员： 0：否 1：是

    @TableField(fill = FieldFill.INSERT)    //自动填充当前时间
    private LocalDateTime markTime; //上次签到时间
    private LocalDateTime registerTime;   //注册时间

    public User(String userName, String password, String email, int score) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.score = score;
    }


}
