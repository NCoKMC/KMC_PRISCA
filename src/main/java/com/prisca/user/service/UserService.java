package com.prisca.user.service;

import java.util.List;

import com.prisca.user.dto.ReserveDto;
import com.prisca.user.dto.UserDto;


public interface UserService {

	public UserDto getUserDetail(UserDto user) throws Exception;

	public int saveUser(UserDto user) throws Exception;

	public List<ReserveDto> getUserReserveList(ReserveDto resv) throws Exception;



}
