package dev.ed.eagles.dto.member;

import lombok.Data;

import java.util.List;

@Data
public class MemberResponseDto {
    private Long id;
    private String name;
    private String role;
    private List<String> instruments;
    private Boolean isActive;
}
