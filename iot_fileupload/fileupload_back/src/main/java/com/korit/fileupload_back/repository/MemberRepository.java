package com.korit.fileupload_back.repository;

import com.korit.fileupload_back.entity.Member;
import com.korit.fileupload_back.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MemberRepository {

    @Autowired
    private MemberMapper memberMapper;

    public Optional<Member> save(Member member) {
        memberMapper.insert(member);
        return Optional.of(member);
    }

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(memberMapper.selectMemberById(id));
    }

}








