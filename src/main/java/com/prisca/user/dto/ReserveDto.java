package com.prisca.user.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReserveDto {

	private String kmcCd;
	private String userId;		//아이디 = 이메


	private String birthYmd;	//생년월일
	private String email;		//이메일
	private String phoneNum;	//전화번호
	private String countryCd; 	//국가코드

	private String footholdCd;  //거점
	private String peopleNum;  	//입실인원수


	private String startYmd;
	private String endYmd;
	private String startDay;
	private String endDay;

	private String roomNo;
	private String statusCd;
	private String useYn;


	private List<UserFamilyDto> familyList;



}
