package com.prisca.user.dto;

import java.util.Collection;
import java.util.List;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserAuthDto {
	private static final long serialVersionUID = 1L;


	private Long auth;
	private String kmcCd;
    private String kmcPwd;
    private String statusCd;
    private String email;



}
