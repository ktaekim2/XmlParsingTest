package com.example.xmlparsingtest.service;

import com.example.xmlparsingtest.dto.MemberDTO;
import com.example.xmlparsingtest.entity.MemberEntity;
import com.example.xmlparsingtest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        memberRepository.save(MemberEntity.toEntity(memberDTO));
    }

    public MemberEntity findById() {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(1L);
        if(optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity = optionalMemberEntity.get();
            return memberEntity;
        }
        return null;
    }
}
