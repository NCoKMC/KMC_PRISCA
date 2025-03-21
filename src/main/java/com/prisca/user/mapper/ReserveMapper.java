package com.prisca.user.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.prisca.user.dto.ReserveDto;
import com.prisca.user.dto.UserDto;
import com.prisca.user.dto.UserFamilyDto;


@Repository
@Mapper
public interface ReserveMapper {

	List<ReserveDto> selectResveManageList(ReserveDto vo) throws SQLException;

	List<ReserveDto> selectRoomResveOkList(ReserveDto vo) throws SQLException;

	void insertReserveInfo(ReserveDto vo) throws SQLException;

	int insertFamilyInfo(UserFamilyDto vo) throws SQLException;

	List<ReserveDto> selectRoomResveList(ReserveDto vo) throws SQLException;



}
