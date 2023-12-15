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
    private String imgUrl;     //游戏图片链接
    private Integer prince;         //游戏价格: xx积分
    private String description; //游戏描述
    private String duLink;     //百度网盘链接
    private String aliLink;    //阿里云盘链接
    private String quarkLink;  //夸克网盘链接
    private String otherLink;  //其他盘链接

    public Game(String tittle, String imgUrl, Integer prince, String description, String duLink, String aliLink, String quarkLink, String otherLink) {
        this.tittle = tittle;
        this.imgUrl = imgUrl;
        this.prince = prince;
        this.description = description;
        this.duLink = duLink;
        this.aliLink = aliLink;
        this.quarkLink = quarkLink;
        this.otherLink = otherLink;
    }

    public Game(String tittle, Integer prince, String description, String duLink, String aliLink, String quarkLink, String otherLink) {
        this.tittle = tittle;
        this.prince = prince;
        this.description = description;
        this.duLink = duLink;
        this.aliLink = aliLink;
        this.quarkLink = quarkLink;
        this.otherLink = otherLink;
    }
}
