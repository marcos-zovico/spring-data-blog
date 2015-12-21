package com.msouza.blog.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.msouza.blog.entity.Postagem;
import com.msouza.blog.service.PostagemService;

@Controller
public class HomeController {

	@Autowired
	private PostagemService postagemService;

	@RequestMapping(value = "/autor/{nome}", method = RequestMethod.GET)
	public ModelAndView postesByAutor(@PathVariable("nome") String nome, ModelMap model){
		List<Postagem> postagens = postagemService.findByAutor(nome);
		
		model.addAttribute("postagens", postagens);
		return new ModelAndView("posts", model);
	}

	
	
	@RequestMapping(value = "/categoria/{link}", method = RequestMethod.GET)
	public ModelAndView postesByCategoria(@PathVariable("link") String link, ModelMap model){
		List<Postagem> postagens = postagemService.findByCategoria(link);
		
		model.addAttribute("postagens", postagens);
		return new ModelAndView("posts", model);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(ModelMap model) {
		List<Postagem> postagens = postagemService.findAll();

		model.addAttribute("postagens", postagens);

		return new ModelAndView("posts", model);
	}

}
