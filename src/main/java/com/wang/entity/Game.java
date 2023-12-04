package com.wang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    private int id;             //游戏id,主键
    private String tittle;      //游戏名称
    private int prince;         //游戏价格: xx积分
    private String description; //游戏描述
    private String du_link;     //百度网盘链接
    private String ali_link;    //阿里云盘链接
    private String quark_link;  //夸克网盘链接
    private String other_link;  //其他盘链接

    public Game(String tittle, int prince, String description, String du_link, String ali_link, String quark_link, String other_link) {
        this.tittle = tittle;
        this.prince = prince;
        this.description = description;
        this.du_link = du_link;
        this.ali_link = ali_link;
        this.quark_link = quark_link;
        this.other_link = other_link;
    }
}
