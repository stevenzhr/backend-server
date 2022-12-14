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
@RequestMapping("/auto")
public class AutomationController {
    @Resource
    AutomationMapper automationMapper;
    @Resource
    DeviceMapper deviceMapper;

    @PutMapping
    public Result<?> addAutomation(@RequestBody Automation automation) {
        Device primeDevice = deviceMapper.selectOne(Wrappers.<Device>lambdaQuery().eq(Device::getDeviceId, automation.getPrimeDeviceId()));
        Device drivenDevice = deviceMapper.selectOne(Wrappers.<Device>lambdaQuery().eq(Device::getDeviceId, automation.getDrivenDeviceId()));
        automation.setPrimeDeviceName(primeDevice.getName());
        automation.setDrivenDeviceName(drivenDevice.getName());
        automationMapper.insert(automation);
        return Result.success();
    }

    @GetMapping
    public Result<?> getAutoList(@RequestParam String username) {
        List<Automation> automationList = automationMapper.selectList(Wrappers.<Automation>lambdaQuery().eq(Automation::getUsername, username));
        return Result.success(automationList);
    }

    @DeleteMapping("/{autoId}")
    public Result<?> delete(@PathVariable String autoId) {
        automationMapper.deleteById(autoId);
        return Result.success();
    }

}
