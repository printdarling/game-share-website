package com.wang.mapper;

import com.wang.entity.Game;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface GameMapper {

    //增
    @Insert("INSERT INTO game (tittle,img_url, prince, description, du_link, ali_link, quark_link, other_link) " +
            "VALUES (#{tittle},#{imgUrl}, #{prince}, #{description}, #{duLink}, #{aliLink}, #{quarkLink}, #{otherLink})")
    int addGame(@RequestParam("game") Game game);

    //删

    //改

    //查

    @Select("select * from game")
    List<Game> queryAllGames();

    @Select("select * from game limit #{pageNum} , #{pageSize}")
    List<Game> queryAllGamesByPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @Select("select * from game where prince = 0 limit #{pageNum} , #{pageSize}")
    List<Game> getAllFreeGamesByPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @Select("select * from game where prince != 0 limit #{pageNum} , #{pageSize}")
    List<Game> getAllPayGamesByPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @Select("select * from game where tittle like concat('%',#{tittle},'%')")
    List<Game> queryGamesByTittle(@Param("tittle") String tittle);

    @Select("select * from game where id = #{id}")
    Game queryGameById(Integer id);

    @Select("select count(*) from game")
    Integer getAllGamesCount();

    @Select("select count(*) from game where prince = 0")
    Integer getFreeGamesCount();

    @Select("select count(*) from game where prince != 0")
    Integer getPayGamesCount();

    @Delete("delete from game where id = #{id}")
    Integer deleteGameById(@Param("id") int id);

    @Update("update game set tittle = #{tittle},img_url = #{imgUrl},prince = #{prince},description = #{description},du_link = #{duLink},ali_link = #{aliLink},quark_link =#{quarkLink},other_link = #{otherLink} where id = #{id}")
    Integer updateGame(@RequestParam("game") Game game);
}
