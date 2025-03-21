package com.prisca.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prisca.user.dto.UserAuthDto;
import com.prisca.user.dto.UserDto;
import com.prisca.user.mapper.UserAuthMapper;
import com.prisca.user.service.UserAuthService;


@Service
public class UserAuthServiceImpl implements UserAuthService{
	@Autowired
	private UserAuthMapper authMapper;




	@Override
	public UserAuthDto getUserByKmcCd(UserAuthDto auth) throws Exception {

		return authMapper.selectUserByKmcCd(auth);
	}


	@Override
	public UserDto getUserInfo(UserAuthDto auth) throws Exception {
		// TODO Auto-generated method stub
		return authMapper.selectUserInfo(auth);
	}


	@Override
	public List<UserAuthDto> getUserByemail(UserDto dto) throws Exception {
		List<UserAuthDto> list = authMapper.selectUserByEmail(dto);



		return list;
	}


	@Override
	public Long saveAuthStatus(UserAuthDto auth) throws Exception {
		return authMapper.updateUserAuthStatus(auth);
	}

	@Override
	public Long putUserAuth(UserAuthDto auth) throws Exception {
		return authMapper.updateUserAuth(auth);
	}

}
