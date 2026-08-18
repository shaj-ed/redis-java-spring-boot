package dev.ed.eagles.service;

import dev.ed.eagles.entity.Member;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisTestService {
    private final RedisTemplate<String, Member> redisTemplate;

    public RedisTestService(RedisTemplate<String, Member> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

//    public void setValue() {
//        redisTemplate.opsForValue()
//                .set("hello", "Redis Testing");
//    }

    public Object getValue() {
        return redisTemplate.opsForValue()
                .get("hello");
    }

    public void setMember(Member member) {
        String key = "member:" + member.getId();
        redisTemplate.opsForValue()
                .set(key, member, Duration.ofMinutes(1));
    }

    public Member getMember(Long id) {
        String key = "member:" + id;
        System.out.println("KEY");
        System.out.println(key);
        return redisTemplate.opsForValue()
                .get(key);
    }
}
