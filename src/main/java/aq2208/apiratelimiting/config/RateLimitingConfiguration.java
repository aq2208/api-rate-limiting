package aq2208.apiratelimiting.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RateLimitingConfiguration {

    @Bean(name = "initBucket")
    public Bucket createNewBucket() {
        Refill refill = Refill.intervally(5, Duration.ofMinutes(1)); //refill 10 tokens every 1 minute
        Bandwidth limit = Bandwidth.classic(5, refill);
        return Bucket.builder()
                .addLimit(limit)
                .build();
    }

}
