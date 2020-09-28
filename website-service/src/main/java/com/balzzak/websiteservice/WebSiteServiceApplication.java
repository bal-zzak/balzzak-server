package com.balzzak.websiteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.balzzak.*")
@SpringBootApplication
public class WebSiteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSiteServiceApplication.class, args);
    }

}