package com.OOAD.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Result {
    Object data;
    String msg;
    int code;

    public Result() {
    }

    public Result(Object data, String msg, int code) {
        this.data = data;
        this.msg = msg;
        this.code = code;
    }
}
