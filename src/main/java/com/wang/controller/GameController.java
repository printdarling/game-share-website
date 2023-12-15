package com.wang.controller;

import com.wang.entity.Game;
import com.wang.entity.Result;
import com.wang.mapper.GameMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController
public class GameController {
    @Resource
    GameMapper gameMapper;

    @PostMapping("/getAllGamesByPage")
    public Result getAllGames(@RequestParam(defaultValue = "1") int pageNum,
                              @RequestParam(defaultValue = "10") int pageSize){
        System.out.println("pageNum: "+pageNum);
        System.out.println("pageSize: "+pageSize);
        // 直接调用分页查询方法返回List
        List<Game> games = gameMapper.queryAllGamesByPage(pageNum-1, pageSize);
        return new Result(true, 20000,"查询成功", games);
    }

    @PostMapping("/getAllFreeGamesByPage")
    public Result getAllFreeGamesByPage(@RequestParam(defaultValue = "1") int pageNum,
                              @RequestParam(defaultValue = "10") int pageSize){
        // 直接调用分页查询方法返回List
        List<Game> games = gameMapper.getAllFreeGamesByPage(pageNum-1, pageSize);
        return new Result(true, 20000,"查询成功", games);
    }

    @PostMapping("/findGamesByTitle")
    public Result findGamesByTitle(@RequestParam("title") String title){
        // 直接调用分页查询方法返回List
        List<Game> games = gameMapper.queryGamesByTittle(title);
        return new Result(true, 20000,"查询成功", games);
    }

    @PostMapping("/getAllPayGamesByPage")
    public Result getAllPayGamesByPage(@RequestParam(defaultValue = "1") int pageNum,
                                        @RequestParam(defaultValue = "10") int pageSize){
        // 直接调用分页查询方法返回List
        List<Game> games = gameMapper.getAllPayGamesByPage(pageNum-1, pageSize);
        return new Result(true, 20000,"查询成功", games);
    }



    @PostMapping("/showGameById")
    public Result showGameById(@RequestParam("id") int id){
        System.out.println("查询游戏id: "+id);
        Game game = gameMapper.queryGameById(id);
        return new Result(true, 20000,"查询成功", game);
    }

    @PostMapping("/addGame")
    public Result addGame(@RequestBody Game game){
        System.out.println("addGame: "+ game);
        int addNums = gameMapper.addGame(game);
        if (addNums > 0){
            return new Result(true, 20000,"添加成功", null);
        }else {
            return new Result(false, 10001,"添加失败", null);
        }
    }

    @PostMapping("/modifyGame")
    public Result modifyGame(@RequestBody Game game){
        System.out.println("接收到game: "+ game);

        Integer modifyNums = gameMapper.updateGame(game);
        if (modifyNums > 0){
            return new Result(true, 20000,"修改成功", null);
        }else {
            return new Result(false, 10001,"修改失败", null);
        }
    }


    @PostMapping("/deleteGameById")
    public Result deleteGameById(@RequestParam("id") int id){
        int nums = gameMapper.deleteGameById(id);
        if (nums > 0){
            return new Result(true, 20000,"删除成功", null);
        }
        return new Result(false, 10001,"删除失败,发生错误", null);
    }


    @PostMapping("/getGamesCount")
    public Result getGamesCount(){
        Integer allGamesCount = gameMapper.getAllGamesCount();
        Integer freeGamesCount = gameMapper.getFreeGamesCount();
        Integer payGamesCount = gameMapper.getPayGamesCount();
        int[] gameCount = {allGamesCount, freeGamesCount, payGamesCount};
        System.out.println("游戏数量:" +Arrays.toString(gameCount));
        return new Result(true, 20000,"查询成功", gameCount);
    }


}
