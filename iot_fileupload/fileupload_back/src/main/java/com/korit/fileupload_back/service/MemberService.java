package com.korit.fileupload_back.service;

import com.korit.fileupload_back.dto.ReqFileUploadDto;
import com.korit.fileupload_back.dto.RespAddMemberDto;
import com.korit.fileupload_back.dto.RespGetMemberDto;
import com.korit.fileupload_back.entity.Member;
import com.korit.fileupload_back.exception.MemberInsertException;
import com.korit.fileupload_back.exception.NotFoundException;
import com.korit.fileupload_back.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MemberService {

    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private MemberRepository memberRepository;

    public RespAddMemberDto addMember(ReqFileUploadDto dto) {
        List<String> profileImgPathList = new ArrayList<>();
        dto.getImg().forEach(img -> profileImgPathList.add(fileUploadService.uploadFile(img))); // 리스트 형태로 여러가지 이미지 업로드 할 때
//        String profileImgPath = fileUploadService.uploadFile(dto.getImg());   // 단일 사진 입력할 때

        Member memberEntity = Member.builder()
                .name(dto.getTitle())
                .profileImgPath(String.join(",", profileImgPathList))
                .build();

        Member insertedMember = memberRepository.save(memberEntity)
                .orElseThrow(() -> {
                    Map<String, Object> errorMap = Map.of(
                            "httpStatus", HttpStatus.INTERNAL_SERVER_ERROR,
                            "message", "DB에 멤버 추가 중 오류!"
                    );
                    return new MemberInsertException("멤버 추가 오류!", errorMap);
                });

        return RespAddMemberDto.builder()
                .isSuccess(true)
                .memberId(insertedMember.getId())
                .build();
    }

    public RespGetMemberDto getMember(Long id) {
        Member foundMember = memberRepository.findById(id).
                orElseThrow(() -> new NotFoundException("해당 멤버 ID는 존재하지 않습니다."));

        return RespGetMemberDto.builder()
                .memberId(foundMember.getId())
                .name(foundMember.getName())
                .profileImgPath(foundMember.getProfileImgPath())
                .build();
    }
}
