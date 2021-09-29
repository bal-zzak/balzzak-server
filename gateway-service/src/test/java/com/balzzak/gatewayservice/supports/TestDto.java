package com.balzzak.gatewayservice.supports;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class TestDto {

    @Max(100)
    private long id;
    @Length(min = 5)
    @NotBlank
    private String content;
    @NotNull
    private TestType type;

    public TestDto(long id, String content, TestType type) {
        this.id = id;
        this.content = content;
        this.type = type;
    }
}
