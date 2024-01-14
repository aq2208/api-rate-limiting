package aq2208.apiratelimiting.controller;

import io.github.bucket4j.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Bucket4jDemoController {

    @Autowired
    @Qualifier("initBucket")
    private Bucket bucket;

    @GetMapping("/hello")
    public String sayHello() {
        System.out.println("Bucket Available Tokens: " + bucket.getAvailableTokens());

        if (bucket.tryConsume(1L)) {
            System.out.println("Hello, World!");
            return "Hello, World!";
        } else {
            System.out.println("Exceeded Rate Limit!");
            return "Exceeded Rate Limit!";
        }
    }

}
