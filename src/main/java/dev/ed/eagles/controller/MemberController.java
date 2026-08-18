package dev.ed.eagles.controller;

import dev.ed.eagles.dto.member.MemberCreateDto;
import dev.ed.eagles.dto.member.MemberResponseDto;
import dev.ed.eagles.dto.member.MemberUpdateDto;
import dev.ed.eagles.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberResponseDto> addMember(@RequestBody MemberCreateDto memberCreateDto) {
        MemberResponseDto member = memberService.addMember(memberCreateDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(member);
    }

    @PutMapping
    public ResponseEntity<MemberResponseDto> updateMember(@RequestBody MemberUpdateDto memberUpdateDto) {
        MemberResponseDto member = memberService.updateMember(memberUpdateDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(member);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> getMembers() {
        List<MemberResponseDto> members = memberService.getMembers();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(members);
    }

    @GetMapping("{id}")
    public ResponseEntity<MemberResponseDto> getMemberById(@PathVariable Long id) {
        MemberResponseDto member = memberService.getMemberById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(member);
    }
}
