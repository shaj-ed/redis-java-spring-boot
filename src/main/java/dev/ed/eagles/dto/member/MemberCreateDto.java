package dev.ed.eagles.dto.member;

import lombok.Data;

import java.util.List;

@Data
public class MemberCreateDto {
    private String name;
    private String role;
    private List<String> instruments;
    private Boolean isActive;
}
