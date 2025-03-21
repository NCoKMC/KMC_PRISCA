package com.prisca.user.service;

import java.sql.SQLException;
import java.util.List;

import com.prisca.user.dto.ReserveDto;


public interface ReserveService {

	public List<ReserveDto> getResveManageList(ReserveDto dto) throws Exception;
	public List<ReserveDto> getRoomResveOkList(ReserveDto dto) throws Exception;
	public void saveReserveInfo(ReserveDto vo) throws SQLException;


}
