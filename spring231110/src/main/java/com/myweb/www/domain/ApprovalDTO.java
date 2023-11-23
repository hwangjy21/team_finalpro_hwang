package com.myweb.www.domain;

import java.util.List;

import com.myweb.www.security.MemberVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalDTO {
    private ApprovalVO apvo;
    private MemberVO mvo;
    
}
