package com.wang.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wang.entity.Game;
import com.wang.entity.Result;
import com.wang.mapper.GameMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class GameController {
    @Resource
    GameMapper gameMapper;

    @PostMapping("/getAllGamesByPage")
    public Result getAllGames(@RequestParam(defaultValue = "1") int pageNum,
                              @RequestParam(defaultValue = "10") int pageSize){
        // 直接调用分页查询方法返回List
        List<Game> games = gameMapper.queryAllGamesByPage(pageNum-1, pageSize);
        System.out.println("游戏数量："+games.size());
        return new Result(true, 20000,"查询成功", games);
    }

    @PostMapping("/getAllFreeGamesByPage")
    public Result getAllFreeGamesByPage(@RequestParam(defaultValue = "1") int pageNum,
                              @RequestParam(defaultValue = "10") int pageSize){
        // 直接调用分页查询方法返回List
        List<Game> games = gameMapper.getAllFreeGamesByPage(pageNum-1, pageSize);
        System.out.println("免费游戏数量："+games.size());
        return new Result(true, 20000,"查询成功", games);
    }

    @PostMapping("/getAllPayGamesByPage")
    public Result getAllPayGamesByPage(@RequestParam(defaultValue = "1") int pageNum,
                                        @RequestParam(defaultValue = "10") int pageSize){
        // 直接调用分页查询方法返回List
        List<Game> games = gameMapper.getAllPayGamesByPage(pageNum-1, pageSize);
        System.out.println("免费游戏数量："+games.size());
        return new Result(true, 20000,"查询成功", games);
    }

    @PostMapping("/show")
    public Result showGameById(@RequestParam(defaultValue = "1") int id){
        Game game = gameMapper.queryGameById(id);
        return new Result(true, 20000,"查询成功", game);
    }

    @PostMapping("/addGame")
    public Result addGame(@RequestParam("tittle") String tittle,
                          @RequestParam("prince") int prince,
                          @RequestParam("file") MultipartFile file,
                          @RequestParam("description") String description,
                          @RequestParam("du_link") String du_link,
                          @RequestParam("ali_link") String ali_link,
                          @RequestParam("quark_link") String quark_link,
                          @RequestParam("other_link") String other_link){
        int nums = gameMapper.addGame(new Game(tittle, prince, description, du_link, ali_link, quark_link, other_link));
        if (nums > 0){
            return new Result(true, 20000,"添加成功", null);
        }
        return new Result(false, 10001,"添加失败,发生错误", null);
    }


}
