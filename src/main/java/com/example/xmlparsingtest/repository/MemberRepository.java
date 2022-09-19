package com.example.xmlparsingtest.repository;

import com.example.xmlparsingtest.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository를 상속받기 때문에 알아서 @Repository로 등록됨
public interface MemberRepository extends JpaRepository<MemberEntity, Long> { // 대상 entity, pk타입
}
