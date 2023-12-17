package com.OOAD.domain;

import lombok.Data;

import java.util.Date;

@Data
public class AuthorizeVO {
    String username;
    Integer Id;
    String role;
    String token;
    Date expire;
}
