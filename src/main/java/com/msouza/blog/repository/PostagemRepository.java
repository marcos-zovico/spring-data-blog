package com.msouza.blog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.msouza.blog.entity.Postagem;


public interface PostagemRepository extends JpaRepository<Postagem, Long>{

	Page<Postagem> findAllByOrderByDataPostagemDesc(Pageable pageable);
	
	Postagem findByPermaLink(String permaLink);

	List<Postagem> findByCategoriasPermaLink(String link);

	List<Postagem> findByAutorNome(String nome);

	Page<Postagem> findAllByCategoriasPermaLinkOrderByDataPostagemDesc(Pageable pageable, String permalink);
}
