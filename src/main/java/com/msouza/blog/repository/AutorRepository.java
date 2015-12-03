package com.msouza.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msouza.blog.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

	
	Autor findByNome(String nome);
}
