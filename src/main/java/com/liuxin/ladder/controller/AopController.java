package com.liuxin.ladder.controller;

import com.liuxin.ladder.aop.ControllerWebLog;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: liuxin79
 * @date: 2019-09-03 11:32
 */
@RestController
public class AopController {


    @GetMapping("/testaop")
    @ControllerWebLog(name = "GET 请求测试", intoDb = true)
    public void postTest(HttpServletRequest baseRequest) {
    }
}
