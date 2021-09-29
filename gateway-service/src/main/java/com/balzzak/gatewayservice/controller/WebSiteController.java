package com.balzzak.gatewayservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/webSite")
@RestController
@RequiredArgsConstructor
public class WebSiteController {

    @GetMapping(path = "/{webSiteId}")
    public void getWebSiteId(@PathVariable Object obj) {
    }
}
