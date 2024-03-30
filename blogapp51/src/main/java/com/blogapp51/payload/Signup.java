package com.blogapp51.payload;

import lombok.Data;

@Data
public class Signup {

    private long id;
    private String name;
    private String email;
    private String username;
    private String password;
}
