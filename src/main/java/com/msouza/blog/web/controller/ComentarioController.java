package com.msouza.blog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.msouza.blog.entity.Comentario;
import com.msouza.blog.entity.Postagem;
import com.msouza.blog.service.ComentarioService;
import com.msouza.blog.service.PostagemService;

@Controller
@RequestMapping("comentario")
public class ComentarioController {

	@Autowired
	private ComentarioService comentarioService;
	
	@Autowired
	private PostagemService postagemService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("comentario") @Validated Comentario comentario, BindingResult result ,   
			           @RequestParam("permaLink") String permaLink){
		
		Postagem postagem = postagemService.findByPermaLink(permaLink);
		
		if (result.hasErrors()) {
			return new ModelAndView("post", "postagem",  postagem);
		}
		
		
		comentario.setPostagem(postagem);
		comentarioService.save(comentario);
		return new ModelAndView("redirect:/" + permaLink);
	}
}
