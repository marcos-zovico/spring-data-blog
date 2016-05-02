package com.msouza.blog.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.msouza.blog.entity.Comentario;
import com.msouza.blog.entity.Postagem;
import com.msouza.blog.service.PostagemService;

@Controller
public class HomeController {

	@Autowired
	private PostagemService postagemService;

	@RequestMapping(value = "/{permaLink}", method = RequestMethod.GET)
	public ModelAndView openPostagem(
			@ModelAttribute("comentario") Comentario comentario,
			@PathVariable("permaLink") String permaLink, ModelMap model) {
		Postagem postagem = postagemService.findByPermaLink(permaLink);

		model.addAttribute("postagem", postagem);
		return new ModelAndView("post", model);
	}

	@RequestMapping(value = "/autor/{id}/page/{page}", method = RequestMethod.GET)
	public ModelAndView postesByAutor(@PathVariable("id") Long id, 
									  @PathVariable("page") Integer pagina,
									  ModelMap model) {
//		List<Postagem> postagens = postagemService.findByAutor(nome);
//		model.addAttribute("postagens", postagens);
		
		Page<Postagem> page = postagemService.findByPaginationByAutor(pagina - 1, 5, id);
		model.addAttribute("page", page);
		model.addAttribute("urlPagination", "/autor/" + id + "/page");
		
		return new ModelAndView("posts", model);
	}

	@RequestMapping(value = "/categoria/{link}/page/{page}", method = RequestMethod.GET)
	public ModelAndView postesByCategoria(@PathVariable("page") Integer pagina, 
										  @PathVariable("link") String link, ModelMap model) {
		
//		List<Postagem> postagens = postagemService.findByCategoria(link);
//		model.addAttribute("postagens", postagens);
		Page<Postagem> page = postagemService.findByPaginationByCategoria(pagina - 1, 5, link);
		model.addAttribute("page", page);
		model.addAttribute("urlPagination", "/categoria/" + link + "/page");
		
		return new ModelAndView("posts", model);
	}

	@RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
	public ModelAndView pageHome(@PathVariable("page") Integer pagina, ModelMap model) {
		Page<Postagem> page = postagemService.findByPagination(pagina -1, 5);
		
		model.addAttribute("page", page);
		model.addAttribute("urlPagination", "/page");
		
		return new ModelAndView("posts", model);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(ModelMap model) {
//		List<Postagem> postagens = postagemService.findAll();
//		model.addAttribute("postagens", postagens);
		
		Page<Postagem> page = postagemService.findByPagination(0, 5);
		model.addAttribute("page", page);
		model.addAttribute("urlPagination", "/page");

		return new ModelAndView("posts", model);
	}

}
