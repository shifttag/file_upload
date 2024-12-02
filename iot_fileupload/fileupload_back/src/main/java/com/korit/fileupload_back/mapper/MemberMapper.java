package com.korit.fileupload_back.mapper;

import com.korit.fileupload_back.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    int insert(Member member);
    Member selectMemberById(@Param("id") Long id);
}
