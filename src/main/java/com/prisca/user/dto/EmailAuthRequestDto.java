package com.prisca.user.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class EmailAuthRequestDto {

   // @NotEmpty(message = "이메일을 입력해주세요")
    public String email;
}