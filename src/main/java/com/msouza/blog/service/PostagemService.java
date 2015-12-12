package com.msouza.blog.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.msouza.blog.entity.Postagem;
import com.msouza.blog.repository.PostagemRepository;
import com.msouza.blog.util.MyReplaceString;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PostagemService {
	
	@Autowired
	private PostagemRepository repository;
	
	public Postagem findById(Long id){
		return repository.findOne(id);
	}
	
	public List<Postagem> findAll(){
		return repository.findAll();
	}
	
	public Postagem findPermaLink(String permaLink){
		return repository.findByPermaLink(permaLink);
	}
	
	
	@Transactional(readOnly = false)
	public void saveOrUpdate(Postagem postagem){
	
		if (postagem.getId() == null) {
			save(postagem);
		} else {
			update(postagem);
		}
	}

	private void update(Postagem postagem) {
		// TODO Auto-generated method stub
		
	}

	private void save(Postagem postagem) {
		String permaLink = MyReplaceString.formatarPermaLink(postagem.getTitulo());
		postagem.setPermaLink(permaLink);
		postagem.setDataPostagem(LocalDateTime.now());
		repository.save(postagem);
	}

}
