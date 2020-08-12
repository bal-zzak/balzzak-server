package com.balzzak.gatewayservice.supports;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class TestController {

    @GetMapping("/wow")
    public String error() {
        throw new RuntimeException("test error!");
    }

    @GetMapping("/api/tests/{id}")
    public TestDto findById(@PathVariable("id") long id) {
        return new TestDto(id, "this is test");
    }

    @GetMapping("/api/tests")
    public List<TestDto> findByContent(@RequestParam("content") String content) {
        return IntStream.range(0, 5)
                .boxed()
                .map(integer -> new TestDto(integer, content))
                .collect(Collectors.toList());
    }
}
