package com.example.backendserver.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.backendserver.common.Result;
import com.example.backendserver.entity.Device;
import com.example.backendserver.mapper.DeviceMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/device")
public class DeviceController {

    // Informal import, formal way: mapper --> service class --> controller class
    @Resource
    DeviceMapper deviceMapper;

    // INSERT INTO
    @PostMapping
    public Result<?> save(@RequestBody Device device) {
        deviceMapper.insert(device);
        return Result.success();
    }

    // SELECT
    @GetMapping
    public Result<?> findDevice(@RequestParam(defaultValue = "0") String search) {
        List<Device> deviceList = deviceMapper.selectList(Wrappers.<Device>lambdaQuery().like(Device::getDeviceId, search));
        return Result.success(deviceList);
    }

    // UPDATE
    @PutMapping
    public Result<?> update(@RequestBody Device device) {
        deviceMapper.updateById(device);
        return Result.success();
    }

    // DELETE
    @DeleteMapping("/{deviceId}")
    public Result<?> delete(@PathVariable String deviceId) {
        deviceMapper.deleteById(deviceId);
        return Result.success();
    }
}
