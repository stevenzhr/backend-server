package com.example.backendserver.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.backendserver.common.Result;
import com.example.backendserver.entity.Automation;
import com.example.backendserver.entity.Device;
import com.example.backendserver.mapper.AutomationMapper;
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
    @Resource
    AutomationMapper automationMapper;

    // INSERT INTO
    @PostMapping
    public Result<?> save(@RequestBody Device device) {
        deviceMapper.insert(device);
        return Result.success();
    }

    // SELECT
    @GetMapping
    public Result<?> findDevice(@RequestParam(defaultValue = "0") String search, String username) {
        List<Device> deviceList = deviceMapper.selectList(Wrappers.<Device>lambdaQuery()
                .eq(Device::getUsername, username)
                .like(Device::getDeviceId, search));
        return Result.success(deviceList);
    }

    // UPDATE
    @PutMapping
    public Result<?> update(@RequestBody UserDevice userDevice) {
        deviceMapper.updateById(userDevice.device);
        automaticOperation(userDevice);
        return Result.success();
    }

    // DELETE
    @DeleteMapping("/{deviceId}")
    public Result<?> delete(@PathVariable String deviceId) {
        deviceMapper.deleteById(deviceId);
        return Result.success();
    }

    private static class UserDevice {
        public Device device;
        public String username;
    }
    private void automaticOperation(UserDevice userDevice) {
        String currentUser = userDevice.username;
        String primeDeviceId = userDevice.device.getDeviceId();
        String primeOperation = userDevice.device.getStatus();

        List<Automation> automationList = automationMapper.selectList(Wrappers.<Automation>lambdaQuery()
                .eq(Automation::getUsername, currentUser)
                .eq(Automation::getPrimeDeviceId, primeDeviceId)
                .eq(Automation::getPrimeOperation, primeOperation));
        for (Automation a:automationList) {
            Device drivenDevice = deviceMapper.selectOne(Wrappers.<Device>lambdaQuery()
                    .eq(Device::getDeviceId, a.getDrivenDeviceId()));
            if (a.getDrivenOperation().equals("switch")){
                drivenDevice.setStatus(drivenDevice.getStatus().equals("on")?"off":"on");
            } else {
                drivenDevice.setStatus(a.getDrivenOperation());
            }
            deviceMapper.updateById(drivenDevice);
        }
    }


}
