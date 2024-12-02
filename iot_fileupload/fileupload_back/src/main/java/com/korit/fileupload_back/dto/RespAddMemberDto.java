package com.korit.fileupload_back.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RespAddMemberDto {
    private boolean isSuccess;
    private Long memberId;
}
