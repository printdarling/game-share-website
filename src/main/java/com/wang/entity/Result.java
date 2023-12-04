package com.wang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Result {
    private Boolean success;
    private int code;
    private String message;
    private Object data;

    //构造方法私有
    private Result(){}
}
