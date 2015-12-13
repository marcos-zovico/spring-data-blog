package com.msouza.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.msouza.blog.entity.Categoria;
import com.msouza.blog.repository.CategoriaRepository;
import com.msouza.blog.util.MyReplaceString;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	
	public List<Categoria> findAll(){
		return repository.findAll();
	}
	
	public Categoria findByDescricao(String descricao){
		return repository.findByDescricao(descricao);
	}
	
	public Categoria findById(Long id){
		return repository.findOne(id);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id){
		repository.delete(id);
	}
	
	@Transactional(readOnly = false)
	public void saveOrUpdate(Categoria categoria){
		String permaLink = MyReplaceString.formatarPermaLink(categoria.getDescricao());
		categoria.setPermaLink(permaLink);
		
		repository.save(categoria);
	}
	
	

}
