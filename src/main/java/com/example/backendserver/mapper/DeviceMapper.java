package com.example.backendserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backendserver.entity.Device;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeviceMapper extends BaseMapper<Device> {
}
