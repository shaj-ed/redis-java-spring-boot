package dev.ed.eagles.controller;

import dev.ed.eagles.dto.member.MemberCreateDto;
import dev.ed.eagles.entity.Member;
import dev.ed.eagles.mapper.MemberMapper;
import dev.ed.eagles.service.RedisTestService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/redis")
public class RedisController {
    private final RedisTestService redisTestService;

    public RedisController(RedisTestService redisTestService) {
        this.redisTestService = redisTestService;
    }

    @PostMapping("test-set")
    public ResponseEntity<?> setValue() {
        return ResponseEntity.ok("Stored");
    }

    @GetMapping("test-get")
    public ResponseEntity<?> getValue() {
        Object value = redisTestService.getValue();
        return ResponseEntity.ok(value);
    }

    @PostMapping("set-member/{id}")
    public ResponseEntity<?> addMember(@RequestBody MemberCreateDto memberCreateDto, @PathVariable Long id) {
        Member member = new Member();
        member.setId(id);
        member.setName(memberCreateDto.getName());
        member.setRole(memberCreateDto.getRole());
        member.setInstruments(memberCreateDto.getInstruments());
        member.setIsActive(memberCreateDto.getIsActive());
        redisTestService.setMember(member);
        return ResponseEntity.ok("Added member");
    }

    @GetMapping("get-member")
    public ResponseEntity<?> getMember() {
        Member member = redisTestService.getMember(666L);
        System.out.println(member);
        return ResponseEntity.ok(MemberMapper.toResponse(member));
    }
}
