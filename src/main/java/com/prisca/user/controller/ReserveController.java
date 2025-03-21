package com.prisca.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.prisca.user.dto.ReserveDto;
import com.prisca.user.dto.UserAuthDto;
import com.prisca.user.dto.UserDto;
import com.prisca.user.service.ReserveService;
import com.prisca.user.service.UserService;



@Controller
public class ReserveController {

	@Autowired
	private ReserveService resvService;



	@GetMapping(value={"/reserve"})
	public String reservePage(Model model) throws Exception {

		ReserveDto vo = new ReserveDto();

		model.addAttribute("vo", vo);
		return "reserve";
	}



	@PostMapping(value={"/reserve"})

	public String reserve(@ModelAttribute ReserveDto rDto) throws Exception {
		/*******************************************************
		 * 1. 방이 있는지 확인
		 * 인원 중 조건이 까다롭습니다.
		 * 1) 최대 수용 인원
		 * 2) 만 14세 기준 자녀가 있을 경우
		 *
		 *
		 */

		String revRoomNo = "";
		List<ReserveDto> list = resvService.getRoomResveOkList(rDto);


		if(list != null && !list.isEmpty()) {
			revRoomNo = list.get(0).getRoomNo();

			rDto.setRoomNo(revRoomNo);
		}else {
			return "redirect:/reserve";
		}

		//userService.saveUser(userVo);

		System.out.println("footHold" + rDto.getFootholdCd());
		System.out.println("peopleNum" + rDto.getPeopleNum());
		System.out.println("startDate" + rDto.getStartYmd());
		System.out.println("endDate" + rDto.getEndYmd());

		resvService.saveReserveInfo(rDto);

        return "/myPage";


	}

}
