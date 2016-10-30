package com.msouza.blog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("auth")
public class LoginController {

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String loginPage() {

		return "login";
	}

	public ModelAndView login() {
		return new ModelAndView();
	}

	public ModelAndView acessoNegado() {
		return new ModelAndView();
	}
}
