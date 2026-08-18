package dev.ed.eagles.mapper;

import dev.ed.eagles.dto.member.MemberCreateDto;
import dev.ed.eagles.dto.member.MemberResponseDto;
import dev.ed.eagles.entity.Member;

public class MemberMapper {
    public static Member toEntity(MemberCreateDto memberCreateDto) {
        Member member = new Member();

        member.setName(memberCreateDto.getName());
        member.setRole(memberCreateDto.getRole());
        member.setInstruments(memberCreateDto.getInstruments());
        member.setIsActive(memberCreateDto.getIsActive());

        return member;
    }

    public static MemberResponseDto toResponse(Member member) {
        MemberResponseDto memberResponseDto = new MemberResponseDto();

        memberResponseDto.setId(member.getId());
        memberResponseDto.setName(member.getName());
        memberResponseDto.setRole(member.getRole());
        memberResponseDto.setInstruments(member.getInstruments());
        memberResponseDto.setIsActive(member.getIsActive());

        return memberResponseDto;
    }
}
