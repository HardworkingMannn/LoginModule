package com.example.loginmodule.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name="测试接口")
@Slf4j
public class TestController {
    @GetMapping("/test")
    @Operation(summary = "测试")
    public String test(){
        log.info("调用测试接口");
        return "测试成功";
    }
}
