package com.msouza.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msouza.blog.entity.Avatar;
import com.msouza.blog.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);
	
	Usuario findByAvatar(Avatar avatar);
	
}
