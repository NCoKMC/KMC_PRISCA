package com.prisca.user.service;

import java.util.List;

import com.prisca.user.dto.UserAuthDto;
import com.prisca.user.dto.UserDto;


public interface UserAuthService {

	public UserAuthDto getUserByKmcCd(UserAuthDto auth) throws Exception;

	public UserDto getUserInfo(UserAuthDto auth) throws Exception;

	List<UserAuthDto> getUserByemail(UserDto dto) throws Exception;

	Long saveAuthStatus(UserAuthDto auth) throws Exception;

	Long putUserAuth(UserAuthDto auth) throws Exception;



}
