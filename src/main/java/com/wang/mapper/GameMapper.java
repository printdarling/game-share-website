package com.wang.mapper;

import com.wang.entity.Game;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GameMapper {

    //增
    @Insert("INSERT INTO game (tittle, prince, description, du_link, ali_link, quark_link, other_link) " +
            "VALUES (#{tittle}, #{prince}, #{description}, #{du_link}, #{ali_link}, #{quark_link}, #{other_link})")
    int addGame(Game game);

    //删

    //改

    //查

    @Select("select * from game")
    List<Game> queryAllGames();

    @Select("select * from game limit #{pageNum} , #{pageSize}")
    List<Game> queryAllGamesByPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @Select("select * from game where prince = 0 limit #{pageNum} , #{pageSize}")
    List<Game> getAllFreeGamesByPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @Select("select * from game where prince = 0 limit #{pageNum} , #{pageSize}")
    List<Game> getAllPayGamesByPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @Select("select * from game where id = #{id}")
    Game queryGameById(Integer id);
}
