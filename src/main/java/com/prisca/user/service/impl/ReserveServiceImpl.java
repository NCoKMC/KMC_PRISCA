package com.prisca.user.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prisca.user.dto.ReserveDto;
import com.prisca.user.dto.UserDto;
import com.prisca.user.dto.UserFamilyDto;
import com.prisca.user.mapper.ReserveMapper;
import com.prisca.user.mapper.UserMapper;
import com.prisca.user.service.ReserveService;
import com.prisca.user.service.UserService;





@Service
public class ReserveServiceImpl implements ReserveService{


	@Autowired
	private ReserveMapper mapper;

	@Override
	public List<ReserveDto> getResveManageList(ReserveDto dto) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectResveManageList(dto);
	}

	@Override
	public List<ReserveDto> getRoomResveOkList(ReserveDto dto) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectRoomResveOkList(dto);
	}

	@Override
	public void saveReserveInfo(ReserveDto vo) throws SQLException {
		String kmcCd = vo.getKmcCd();

		mapper.insertReserveInfo(vo);

		for (UserFamilyDto fVo : vo.getFamilyList()) {
			fVo.setKmcCd(kmcCd);

			mapper.insertFamilyInfo(fVo);
		}

	}







}
