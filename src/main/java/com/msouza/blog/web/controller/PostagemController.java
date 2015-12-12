package com.msouza.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.msouza.blog.entity.Postagem;
import com.msouza.blog.service.PostagemService;

@Controller
@RequestMapping("postagem")
public class PostagemController {

	
	@Autowired
	private PostagemService postagemService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listPostagens(ModelMap model){
		
		model.addAttribute("postagens", postagemService.findAll());
		
		return new ModelAndView("postagem/list");
		
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("postagem") Postagem postagem){
		
		postagemService.saveOrUpdate(postagem);
		
		return"redirect:/postagem/list";
		
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET )
	public ModelAndView cadastro(@ModelAttribute("postagem") Postagem postagem){
		
		return new ModelAndView("postagem/cadastro");
		
	}
}
