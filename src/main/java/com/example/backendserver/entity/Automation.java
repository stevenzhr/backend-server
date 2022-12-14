package com.example.backendserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("auto_list")
@Data
public class Automation {
    @TableId(type = IdType.AUTO)
    private Long autoId;
    private String username;
    @TableField("prime_device_id")
    private String primeDeviceId;
    @TableField("prime_device_name")
    private String primeDeviceName;
    @TableField("prime_operation")
    private String primeOperation;
    @TableField("driven_device_id")
    private String drivenDeviceId;
    @TableField("driven_device_name")
    private String drivenDeviceName;
    @TableField("driven_operation")
    private String drivenOperation;
}
