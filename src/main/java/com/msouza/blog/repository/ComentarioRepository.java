package com.msouza.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msouza.blog.entity.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
