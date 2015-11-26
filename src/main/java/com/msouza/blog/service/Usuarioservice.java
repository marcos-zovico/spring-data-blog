package com.msouza.blog.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.msouza.blog.entity.Avatar;
import com.msouza.blog.entity.Usuario;
import com.msouza.blog.repository.UsuarioRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class Usuarioservice {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Transactional(readOnly = false)
	public void save(Usuario usuario){
		if (usuario.getDataCadastro() == null) {
			usuario.setDataCadastro(LocalDate.now());
		}
		
		String hash = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(hash);
		
		repository.save(usuario);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id){
		repository.delete(id);
	}
	
	public Usuario findById(Long id){
		return repository.findOne(id);
	}
	
	public Usuario findByEmail(String email){
		return repository.findByEmail(email);
	}
	
	public Usuario findByEmail(Avatar avatar){
		return repository.findByAvatar(avatar);
	}
	
	public List<Usuario> findAll(){
		return repository.findAll();
	}
}
