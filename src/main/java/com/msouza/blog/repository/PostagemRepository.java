package com.msouza.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msouza.blog.entity.Postagem;


public interface PostagemRepository extends JpaRepository<Postagem, Long>{

	Postagem findByPermaLink(String permaLink);
}
