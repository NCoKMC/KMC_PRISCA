package com.prisca.user.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.prisca.user.dto.UserAuthDto;
import com.prisca.user.dto.UserDto;


@Repository
@Mapper
public interface UserAuthMapper {


	Long insertUserAuth(UserAuthDto user) throws SQLException;
	Long updateUserAuth(UserAuthDto userVo);
	Long updateUserAuthStatus(UserAuthDto userVo);
	UserAuthDto selectUserByKmcCd(UserAuthDto userVo);
	UserDto selectUserInfo(UserAuthDto userVo);


	List<UserAuthDto> selectUserByEmail(UserDto userVo);

}
