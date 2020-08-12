package com.balzzak.gatewayservice.supports;

import lombok.Getter;

@Getter
public class TestDto {

    private long id;
    private String content;

    public TestDto(long id, String content) {
        this.id = id;
        this.content = content;
    }
}
