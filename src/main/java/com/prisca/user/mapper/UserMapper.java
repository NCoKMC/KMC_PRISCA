package com.prisca.user.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.prisca.user.dto.UserDto;


@Repository
@Mapper
public interface UserMapper {

	int selectUserListCnt() throws SQLException;
	List<UserDto> selectUserList() throws SQLException;
	UserDto selectUserDetail(UserDto user) throws SQLException;
	int insertUser(UserDto user) throws SQLException;
	int updateUser(UserDto userVo);
	void deleteUser(Long id);


	List<UserDto> selectUserByEmail(String email);
	List<UserDto> selectUserByPhoneNum(String phoneNum);



}
