package com.prisca.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFamilyDto {


	private String name;		//아이디 = 이메
	private String kmcCd;
	private String birthYmd;	//생년월일
	private String genderCd;		//이메일
	private String familyCd;	//전화번호
	private String desc; 	//국가코드

	private String seq;


}
