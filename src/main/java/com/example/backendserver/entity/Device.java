package com.example.backendserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@TableName("device_list")
@Data
public class Device {

    private Long key_id;
    @TableId
    private String deviceId;
    private String name;
    private String type;
    private String status;
    private String username;

}
