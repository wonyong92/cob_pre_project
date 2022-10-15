package com.team23.PreProject.FIle.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDto {
    private String uuid;	// unique 한 파일 이름을 만들기 위한 속성
    private String fileName;	// 실제 파일 이름
    private String contentType;

    public FileDto() {}

    public FileDto(String uuid, String fileName, String contentType) {
        this.uuid = uuid;
        this.fileName = fileName;
        this.contentType = contentType;

    }

    // setter/getter는 생략
}

