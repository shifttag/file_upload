package com.korit.fileupload_back.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespGetMemberDto {
    private Long memberId;
    private String name;
    private String profileImgPath;

}
