package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisAutoConfiguration {

    private static final String host = "localhost";
    private static final int port = 6379;

    @Bean
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }

//    @Bean
//    public ReactiveRedisOperations<String, String> reactiveRedisTemplate() {
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//
//        RedisSerializationContext<String, String> serializationContext = RedisSerializationContext.<String, String>newSerializationContext(stringRedisSerializer)
//                .value(stringRedisSerializer)
//                .build();
//        return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory(), serializationContext);
//    }
}
