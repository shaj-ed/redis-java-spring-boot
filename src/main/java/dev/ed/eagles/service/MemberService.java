package dev.ed.eagles.service;

import dev.ed.eagles.dto.member.MemberCreateDto;
import dev.ed.eagles.dto.member.MemberResponseDto;
import dev.ed.eagles.dto.member.MemberUpdateDto;

import java.util.List;

public interface MemberService {
    public MemberResponseDto addMember(MemberCreateDto memberCreateDto);
    public MemberResponseDto updateMember(MemberUpdateDto memberUpdateDto);
    public List<MemberResponseDto> getMembers();
    public MemberResponseDto getMemberById(Long id);
}
