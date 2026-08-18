package dev.ed.eagles.service;

import dev.ed.eagles.dto.member.MemberCreateDto;
import dev.ed.eagles.dto.member.MemberResponseDto;
import dev.ed.eagles.dto.member.MemberUpdateDto;
import dev.ed.eagles.entity.Member;
import dev.ed.eagles.mapper.MemberMapper;
import dev.ed.eagles.repository.MemberRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final RedisTemplate<String, Member> redisTemplate;

    public MemberServiceImpl(MemberRepository memberRepository, RedisTemplate<String, Member> redisTemplate) {
        this.memberRepository = memberRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public MemberResponseDto addMember(MemberCreateDto memberCreateDto) {
        Member savedMember = memberRepository.save(MemberMapper.toEntity(memberCreateDto));
        return MemberMapper.toResponse(savedMember);
    }

    @Override
    public MemberResponseDto updateMember(MemberUpdateDto memberUpdateDto) {
        Member member = memberRepository.findById(memberUpdateDto.getMemberId())
                .orElseThrow(() -> new RuntimeException("Not found"));
        member.setName(memberUpdateDto.getName());
        member.setRole(memberUpdateDto.getRole());
        member.setInstruments(memberUpdateDto.getInstruments());
        member.setIsActive(memberUpdateDto.getIsActive());

        Member savedMember = memberRepository.save(member);

        redisTemplate.delete("member:"+memberUpdateDto.getMemberId());

        return MemberMapper.toResponse(savedMember);
    }

    @Override
    public List<MemberResponseDto> getMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(MemberMapper::toResponse).toList();
    }

    @Override
    public MemberResponseDto getMemberById(Long id) {
        String key = "member:" + id;

        Member cachedMember = redisTemplate.opsForValue()
                .get(key);

        if(cachedMember != null) {
            System.out.println("REDIS HIT");
            return MemberMapper.toResponse(cachedMember);
        }

        System.out.println("REDIS MISS");

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        redisTemplate.opsForValue()
                .set(key, member, Duration.ofMinutes(5));

        return MemberMapper.toResponse(member);
    }
}
