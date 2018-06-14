package com.us.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.us.example.domain.User;
import com.us.example.repository.UserRepository;
import com.us.example.service.CustomUserService;
import com.us.example.util.AjaxUtils;
import com.us.example.util.EnumConstant;
import com.us.example.util.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;

@Controller
class SignupController {

	private static final String SIGNUP_VIEW_NAME = "signup";

	@Autowired
	private CustomUserService userService;
	@Autowired
	private UserRepository userRepository;

	@GetMapping("signup")
	String signup(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {

		if (AjaxUtils.isAjaxRequest(requestedWith)) {
			return SIGNUP_VIEW_NAME.concat(" :: signupForm");
		}
		model.addAttribute("nearestEmbassies", EnumConstant.NearestEmbassy.values());
		return SIGNUP_VIEW_NAME;
	}

	@ResponseBody
	@PostMapping("checkvalidmail")
	public String checkValidMail(@ModelAttribute User user,@RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
		HashMap<String, Boolean> map = new HashMap<>();
		boolean valid = true;
		if (AjaxUtils.isAjaxRequest(requestedWith)) {

			User isUserExisted = userRepository.findByEmail(user.getEmail());
			if (isUserExisted != null) {
				valid = false;
			}
			map.put("valid", valid);
		}
		return JSONObject.toJSONString(map);
	}

	@PostMapping("signup")
	public String signUp (@ModelAttribute User user, RedirectAttributes ra){

		user.setRole("ROLE_USER");
		user.setStatus(1);
		user.setUsername(user.getEmail());
		User addedUser = userService.save(user);
		userService.signin(addedUser);
		MessageHelper.addSuccessAttribute(ra, "signup.success");
		return "redirect:/";
	}

}
