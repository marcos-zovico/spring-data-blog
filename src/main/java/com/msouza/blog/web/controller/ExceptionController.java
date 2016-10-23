package com.msouza.blog.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(Exception.class)
	public ModelAndView genericException(HttpServletRequest req, Exception ex){
		
		ModelAndView view = new ModelAndView("error");
		view.addObject("mensagem", "Ocorreu uma exceção durante a operação, tente novamente!");
		view.addObject("url", req.getRequestURL());
		view.addObject("excecao", ex);
		
		return view;
	}
	
	

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView maxUploadSizeExceededException(HttpServletRequest req, MaxUploadSizeExceededException ex){
		
		ModelAndView view = new ModelAndView("error");
		view.addObject("mensagem", "O arquivo selecionado não pode ser maior que 100kb.");
		view.addObject("url", req.getRequestURL());
		view.addObject("excecao", ex);
		
		return view;
	}
}
