package com.prisca.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prisca.user.dto.UserAuthDto;
import com.prisca.user.dto.UserDto;
import com.prisca.user.service.EmailService;
import com.prisca.user.service.UserAuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/auth")
public class LoginController {

	@Autowired
    private UserAuthService authService;

	@Autowired
    private EmailService emailService;



//	@GetMapping("/login")
//	public String login() throws Exception {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication instanceof AnonymousAuthenticationToken) {
//        	return "/loginPage";
//        }
//
//        return "redirect:/index";
//	}

	@GetMapping(value={"/login"})
	public String login(Model model) throws Exception {

		return "login";
	}

	@GetMapping(value={"/findCode"})
	public String findCode(Model model) throws Exception {



		return "/findCode";
	}



	@PostMapping(value={"/findCode"})
	public String findUser(Model model, @ModelAttribute UserDto dto) throws Exception {
		String status = "40"; // 찾을수 없음
		List<UserAuthDto> list = authService.getUserByemail(dto);

		if(list != null && list.size() > 0) {
			if(list.size() > 1) {
				status = "42";		// 중복 이메일 관리자에게 문의하세요

			}else {
				UserAuthDto vo = list.get(0);
				status = vo.getStatusCd();
				if("30".equals(status)){

					String imsyPwd = emailService.sendEmail(vo);

					if(imsyPwd != null) {
						vo.setKmcPwd(imsyPwd);
						vo.setStatusCd("20");
						authService.putUserAuth(vo);
					}

				}
			}

		}

		model.addAttribute("status", status);

		return "/findCode";
	}


	@GetMapping(value={"/findPass"})
	public String findPass(Model model) throws Exception {



		return "/findPass";
	}




	@PostMapping(value={"/signup"})

	public String signup(HttpServletRequest request, RedirectAttributes rttr, @ModelAttribute UserAuthDto authVo) throws Exception {
		String statusCd = "-1";
		UserDto user = new UserDto();
		UserAuthDto userAuth =  authService.getUserByKmcCd(authVo);

		if(userAuth != null) {
			statusCd = userAuth.getStatusCd();
			if("00".equals(statusCd)) {	// 관리자 미승인 상태

				rttr.addFlashAttribute("statusCd", statusCd);
				return "redirect:/auth/login";
			}else if("10".equals(statusCd)) { // 관리자 승인 비밀번호 미등록
				rttr.addFlashAttribute("statusCd", statusCd);
				return "redirect:/auth/regKmcCode";


			}else if("20".equals(statusCd)) { // code 비밀번호 찾기
				rttr.addFlashAttribute("statusCd", statusCd);
				return "redirect:/auth/regKmcCode";

			}else if("90".equals(statusCd)) { // 블랙리스트 멤버
				rttr.addFlashAttribute("statusCd", statusCd);
				return "redirect:/auth/login";


			}
		}else {
			rttr.addFlashAttribute("statusCd", statusCd); // 선교사 코드 미신청
			return "redirect:/auth/login";
		}

		user = authService.getUserInfo(userAuth);
		System.out.println(user.getUserNm()+"");

		HttpSession session = request.getSession();
//		HttpSession session   ; //(HttpSession)request.getSession();
//		session.setAttribute("loginUser", loginUser);

		session.setAttribute("loginUser", user);
		return "redirect:/myPage";


	}

	@GetMapping(value = "/logout")
	public String logoutGET(HttpServletRequest request) {
	    HttpSession session = request.getSession();
	    session.invalidate();

	    return "redirect:/index";
	}

	@GetMapping(value={"/regKmcCode"})

	public String regKmcCdPage(Model model,@ModelAttribute UserAuthDto authVo) throws Exception {



        return "regKmcCode";


	}


	@PostMapping(value={"/regKmcCode"})

	public String regKmcCode(@ModelAttribute UserAuthDto authVo) throws Exception {


		System.out.println(authVo.getKmcCd()+"");
		authVo.setStatusCd("30");
		Long result =  authService.putUserAuth(authVo);


		if(result > 0 ) {

			 return "redirect:/auth/login";
		}

        return "redirect:/auth/regKmcCode";


	}


//	@PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
//		  public String login(@RequestBody LoginDto request) {
//		      return "/login";
//		  }


}
