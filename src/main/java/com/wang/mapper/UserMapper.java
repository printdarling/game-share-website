package com.wang.mapper;

import com.wang.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper {
    //增
    @Insert("insert into user(userName, password, email, score,role,markTime,registerTime) VALUES (#{userName}, #{password}, #{email}, #{score},#{role},NOW(),NOW())")
    int insertUser(@RequestParam("user") User user);

    //删
    @Delete("delete from user where id = #{id}")
    Integer deleteUserById(@Param("id") int id);

    //改
    @Update("update user set userName = #{userName}, password = #{password}, email = #{email}, score = #{score},role = #{role} where id = #{id}")
    Integer updateUserById(@RequestBody User user);

    //查
    @Select("select * from user where email = #{email}")
    User queryUserByEmail(String email);

    @Select("select * from user where id = #{userId}")
    User queryUserByUserId(Integer id);

    @Select("select * from user where userName = #{userName}")
    User queryUserByUserName(String userName);

    @Select("select prince from user where id = #{id}")
    void queryPriceByUserId(Integer id);

    @Select("select * from user limit #{pageNum}, #{pageSize}")
    List<User> queryAllUsersByPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);


    @Select("select * from user where role=1 limit #{pageNum}, #{pageSize}")
    List<User> queryAllAminUsersByPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @Select("select * from user where role!=1 limit #{pageNum}, #{pageSize}")
    List<User> queryAllNormalUsersByPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @Select("select * from user where userName like concat('%',#{userName},'%')")
    List<User> searchUsersByName(@Param("userName") String userName);

    @Select("select count(*) from user")
    Integer queryAllUserCount();

    @Select("select count(*) from user where role=1")
    Integer queryAdminUserCount();

    @Select("select count(*) from user where role!=1")
    Integer queryNormalUserCount();

    /*
     * 用户签到
     * parms: userId
     */
    @Update("update user set markTime = #{now} where id = #{id}")
    Integer updateMarkTimeById(@Param("now") LocalDateTime now, @Param("id") Integer id);

    @Update("update user set score = score + #{score} where id = #{id}")
    Integer updateScoreById(@Param("score")Integer score, @Param("id") Integer id);
}
