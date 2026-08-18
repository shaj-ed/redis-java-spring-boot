package dev.ed.eagles.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class MemberUpdateDto extends MemberCreateDto {
    private Long memberId;
}
