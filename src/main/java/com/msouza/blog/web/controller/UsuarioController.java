package com.msouza.blog.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.msouza.blog.entity.Avatar;
import com.msouza.blog.entity.Perfil;
import com.msouza.blog.entity.Usuario;
import com.msouza.blog.service.AvatarService;
import com.msouza.blog.service.UsuarioService;
import com.msouza.blog.web.editor.PerfilEditorSupport;
import com.msouza.blog.web.validator.UsuarioValidator;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioservice;
	
	@Autowired
	private AvatarService avatarService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Perfil.class, new PerfilEditorSupport());
		binder.setValidator(new UsuarioValidator());
	}
	
	@RequestMapping(value = "/sort/{order}/{field}/page/{page}")
	public ModelAndView pageUsuario(@PathVariable("page") Integer pagina, 
									@PathVariable("order") String order, 
									@PathVariable("field") String field){
		
		ModelAndView view = new ModelAndView("usuario/list");
		Page<Usuario> page = usuarioservice.findByPaginationOrdeByField(pagina - 1, 5, field, order);
		view.addObject("page", page );
		view.addObject("urlPagination", "/usuario/sort/" + order + "/" + field + "/page");
		
		return view;
	}
	
	@RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
	public ModelAndView pageUsuarios(@PathVariable("page") Integer pagina){
		
		ModelAndView view = new ModelAndView("usuario/list");
		
		Page<Usuario> page = usuarioservice.findByPagination(pagina - 1, 5);
		
		view.addObject("page", page);
		view.addObject("urlPagination", "/usuario/page");
		
		return view;
	}
	
	@RequestMapping(value = {"/update/senha/{id}", "/update/senha"},
					method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView updateSenha(@PathVariable("id") Optional<Long> id,
									@ModelAttribute("usuario") Usuario usuario){
		
		ModelAndView view = new ModelAndView();
		
		if (id.isPresent()) {
			usuario = usuarioservice.findById(id.get());
			view.addObject("usuario", usuario);
			view.setViewName("usuario/atualizar");
			return view;
		}
		
		usuarioservice.updateSenha(usuario);
		view.setViewName("redirect:/usuario/perfil/" + usuario.getId());
		
		return view;
		
	}
	
	@RequestMapping(value = {"/update/{id}", "/update"},
					method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView update(@PathVariable("id") Optional<Long> id, @ModelAttribute("usuario") Usuario usuario){
		ModelAndView view = new ModelAndView();
		
		if (id.isPresent()) {
			usuario = usuarioservice.findById(id.get());
			view.addObject("usuario", usuario);
			view.setViewName("usuario/atualizar");
			return view;
		}
		
		usuarioservice.updateNameAndEmail(usuario);
		
		view.setViewName("redirect:/usuario/perfil/" + usuario.getId());
		return view;
	}
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listUsuarios(ModelMap model){
		
		//List<Usuario> usuarios = usuarioservice.findAll();
		//model.addAttribute("usuarios", usuarios);
		
		Page<Usuario> page = usuarioservice.findByPagination(0, 5);
		model.addAttribute("page", page);
		model.addAttribute("urlPagination", "usuario/page");
	
		return new ModelAndView("usuario/list", model);
		
	}
	
	
	@RequestMapping(value = "/perfil/{id}", method = RequestMethod.GET)
	public ModelAndView perfil(@PathVariable("id") Long id){
		
		ModelAndView view = new ModelAndView();
		Usuario usuario = usuarioservice.findById(id);
		view.addObject("usuario", usuario);
		view.setViewName("usuario/perfil");
		
		return view;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("usuario")@Validated Usuario usuario, BindingResult result){
		
		if (result.hasErrors()) {
			return "usuario/cadastro";
		}
		
		Avatar avatar = avatarService.getAvatarByUpload(usuario.getFile());
		usuario.setAvatar(avatar);
		usuarioservice.save(usuario);
		
		
		return "redirect:/usuario/perfil/"+ usuario.getId();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm(@ModelAttribute("usuario") Usuario usuario){
		return new ModelAndView("usuario/cadastro");
	}
}
