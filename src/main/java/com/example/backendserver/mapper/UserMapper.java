package com.example.backendserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backendserver.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
