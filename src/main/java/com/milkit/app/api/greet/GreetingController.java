package com.milkit.app.api.greet;

import java.util.concurrent.atomic.AtomicLong;

import com.milkit.app.domain.greet.Greeting;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@RestController
@Api(tags = "1. 기본 'Hello World' API  (ROLE_MEMBER, ROLE_ADMIN 접근가능)", value = "GreetingController")
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/api/greeting")
    @ApiOperation(value = "간단한 인사 !", notes = "Hello World !")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    
    
}