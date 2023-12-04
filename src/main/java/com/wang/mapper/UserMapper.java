package com.wang.mapper;

import com.wang.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    //增
    @Insert("insert into user(userName, password, email, score) VALUES (#user.userName, #user.password, #user.email, #user.score)")
    int insertUser(User user);

    //删

    //改

    //查

    @Select("select * from user where email = #{email}")
    User queryUserByEmail(String email);

    @Select("select * from user where id = #{userId}")
    User queryUserByUserId(Integer id);

    @Select("select * from user where userName = #{userName}")
    User queryUserByUserName(String userName);

    @Select("select prince from user where id = #{id}")
    double queryPriceByUserId(Integer id);
}
