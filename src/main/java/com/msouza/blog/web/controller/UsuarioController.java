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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.msouza.blog.entity.Avatar;
import com.msouza.blog.entity.Perfil;
import com.msouza.blog.entity.Usuario;
import com.msouza.blog.service.AvatarService;
import com.msouza.blog.service.Usuarioservice;
import com.msouza.blog.web.editor.PerfilEditorSuport;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private Usuarioservice usuarioservice;
	
	@Autowired
	private AvatarService avatarService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Perfil.class, new PerfilEditorSuport());
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listUsuarios(ModelMap model){
		
		List<Usuario> usuarios = usuarioservice.findAll();
		model.addAttribute("usuarios", usuarios);
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
	public String save(@ModelAttribute("usuario") Usuario usuario,
					   @RequestParam(value = "file", required = false) MultipartFile file){
		
		Avatar avatar = avatarService.getAvatarByUpload(file);
		usuario.setAvatar(avatar);
		usuarioservice.save(usuario);
		
		
		return "redirect:/usuario/perfil/"+ usuario.getId();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm(@ModelAttribute("usuario") Usuario usuario){
		return new ModelAndView("usuario/cadastro");
	}
}