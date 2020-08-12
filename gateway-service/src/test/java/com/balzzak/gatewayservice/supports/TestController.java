package com.balzzak.gatewayservice.supports;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/wow")
    public String error() {
        throw new RuntimeException("test error!");
    }

}
