package com.msouza.blog.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	
	public Page<Postagem> findByPagination(int page, int size){
		Pageable pageable = new PageRequest(page, size);
		return repository.findAllByOrderByDataPostagemDesc(pageable);
	}
	
	public Postagem findById(Long id){
		return repository.findOne(id);
	}
	
	public List<Postagem> findAll(){
		return repository.findAll();
	}
	
	public Postagem findByPermaLink(String permaLink){
		return repository.findByPermaLink(permaLink);
	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(id);
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
		Postagem persistente = repository.findOne(postagem.getId());
		
		if (!persistente.getTitulo().equals(postagem.getTitulo())) {
			persistente.setTitulo(postagem.getTitulo());
		}
		
		if (!persistente.getTexto().equals(postagem.getTexto())) {
			persistente.setTexto(postagem.getTexto());
		}
		
		if (persistente.getCategorias() != postagem.getCategorias()) {
			persistente.setCategorias(postagem.getCategorias());
		}
		
		repository.save(persistente);
		
	}

	private void save(Postagem postagem) {
		String permaLink = MyReplaceString.formatarPermaLink(postagem.getTitulo());
		postagem.setPermaLink(permaLink);
		postagem.setDataPostagem(LocalDateTime.now());
		repository.save(postagem);
	}

	public List<Postagem> findByCategoria(String link) {
		
		return repository.findByCategoriasPermaLink(link);
	}

	public List<Postagem> findByAutor(String nome) {
		return repository.findByAutorNome(nome);
	}

	public Page<Postagem> findByPaginationByCategoria(int page, int size, String permalink) {
		Pageable pageable = new PageRequest(page, size);
		return repository
				.findAllByCategoriasPermaLinkOrderByDataPostagemDesc(pageable, permalink);
	}

	public Page<Postagem> findByPaginationByAutor(int page, int size, Long id) {
	Pageable pageable = new PageRequest(page, size);
	return repository.findAllByAutorIdOrderByDataPostagemDesc(pageable, id);
		
	}

	public Page<Postagem> findByTexto(int page, int size, String texto) {
		return repository.findByTextoContainingIgnoreCaseOrderByDataPostagemDesc(texto, new PageRequest(page, size));
	}


}
