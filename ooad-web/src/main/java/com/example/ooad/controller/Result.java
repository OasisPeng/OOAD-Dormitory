package com.example.ooad.controller;

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
}
