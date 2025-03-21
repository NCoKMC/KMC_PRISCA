package com.prisca.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prisca.user.dto.ReserveDto;
import com.prisca.user.dto.UserDto;
import com.prisca.user.mapper.ReserveMapper;
import com.prisca.user.mapper.UserMapper;
import com.prisca.user.service.UserService;





@Service
public class UserServiceImpl implements UserService{


	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ReserveMapper resvMapper;


	@Override
	public UserDto getUserDetail(UserDto user) throws Exception {
		return userMapper.selectUserDetail(user);
	}

	@Override
	public int saveUser(UserDto userVo) throws Exception {
		List<UserDto> list = new ArrayList<UserDto>();
		int result = 0;

		list = userMapper.selectUserByEmail(userVo.getEmail());

		if(list != null && list.size() > 0) {
			return 901;
		}

		list = userMapper.selectUserByPhoneNum(userVo.getPhoneNum());

		if(list != null && list.size() > 0) {
			return 902;
		}

		if (!userVo.getUserNm().equals("") && !userVo.getEmail().equals("")) {
		    // password는 암호화해서 DB에 저장

			result =  userMapper.insertUser(userVo);
        }else {
        	result = -1;
        }
		return result;


	}

	@Override
	public List<ReserveDto> getUserReserveList(ReserveDto resv) throws Exception {
		// TODO Auto-generated method stub

		return null;
	}





}
