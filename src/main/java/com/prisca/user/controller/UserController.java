package com.prisca.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prisca.user.dto.UserDto;
import com.prisca.user.service.UserService;



@Controller
public class UserController {

	@Autowired
	private UserService userService;


	@GetMapping(value={"/","/index"})
	public String indexPage(Model model) throws Exception {
		return "index";
	}




	@GetMapping(value={"/member"})
	public String memberInfo(Model model) throws Exception {

		UserDto vo = new UserDto();

		model.addAttribute("vo", vo);
		return "member";
	}



//	@PostMapping(value={"/member"})
//	public String signup(UserDto userVo) throws Exception {
//
//		userService.saveUser(userVo);
//        return "redirect:/auth/login";
//
//
//	}


	@PostMapping(value={"/member"})
    public String regUserInfo(UserDto userVo, Model model) throws Exception {
		int result = userService.saveUser(userVo);

		model.addAttribute("result", result);
		model.addAttribute("vo",userVo);
        return "member";
    }


	@GetMapping(value={"/myPage"})
	public String reserveResult(Model model) throws Exception {

		UserDto vo = new UserDto();
		model.addAttribute("vo", vo);
		return "myPage";
	}

	@GetMapping(value={"/intro"})
	public String introPage(Model model) throws Exception {

		UserDto vo = new UserDto();
		model.addAttribute("vo", vo);
		return "intro";
	}



}
