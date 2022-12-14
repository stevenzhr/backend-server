package com.example.backendserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user_list")
@Data
public class User {

    private Long uid;
    private String username;
    private String password;
    private int authority;

}
