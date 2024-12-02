package com.korit.fileupload_back.exception;

import com.korit.fileupload_back.dto.RespAddMemberDto;
import lombok.Getter;

import java.util.Map;

public class MemberInsertException extends RuntimeException {

    @Getter
    private Map<String, Object> errorMap;

    public MemberInsertException(String message, Map<String, Object> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }
}



