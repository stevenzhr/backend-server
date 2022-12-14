package com.example.backendserver.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.backendserver.common.Result;
import com.example.backendserver.entity.Device;
import com.example.backendserver.entity.User;
import com.example.backendserver.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserMapper userMapper;

    @PostMapping
    public Result<?> login(@RequestBody User user) {
        user.setPassword(getMD5String(user.getPassword()));
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()).eq(User::getPassword, user.getPassword()));
        if (res == null) {
            return Result.error("-1", "Username or password invalid. ");
        }
        return Result.success(res.getUsername());
    }
    @PutMapping
    public Result<?> register(@RequestBody User user) {
        user.setPassword(getMD5String(user.getPassword()));
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()));
        if (res != null) {
            return Result.error("-1", "Username exist. ");
        }
        user.setAuthority(1);
        userMapper.insert(user);
        return Result.success();
    }

    public static String getMD5String(String str) {
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
