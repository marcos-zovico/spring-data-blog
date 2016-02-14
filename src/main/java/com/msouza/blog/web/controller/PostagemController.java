package com.msouza.blog.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.msouza.blog.entity.Postagem;
import com.msouza.blog.service.CategoriaService;
import com.msouza.blog.service.PostagemService;
import com.msouza.blog.web.editor.CategoriaEditorSupport;

@Controller
@RequestMapping("postagem")
public class PostagemController {

	@Autowired
	private PostagemService postagemService;

	@Autowired
	private CategoriaService categoriaService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(List.class, new CategoriaEditorSupport(
				List.class, categoriaService));
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView preUpdate(@PathVariable("id") Long id, ModelMap model) {

		Postagem postagem = postagemService.findById(id);
		model.addAttribute("postagem", postagem);
		model.addAttribute("categorias", categoriaService.findAll());

		return new ModelAndView("postagem/cadastro", model);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id) {
		postagemService.delete(id);

		return "redirect:/postagem/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listPostagens(ModelMap model) {

		model.addAttribute("postagens", postagemService.findAll());

		return new ModelAndView("postagem/list");

	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("postagem") Postagem postagem) {

		postagemService.saveOrUpdate(postagem);

		return "redirect:/postagem/list";

	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView cadastro(@ModelAttribute("postagem") Postagem postagem) {
		ModelAndView view = new ModelAndView("postagem/cadastro");
		view.addObject("categorias", categoriaService.findAll());
		return view;
	}
}
