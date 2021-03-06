package com.msouza.blog.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.msouza.blog.entity.Avatar;
import com.msouza.blog.entity.Perfil;
import com.msouza.blog.entity.Usuario;
import com.msouza.blog.repository.UsuarioRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Page<Usuario> findByPaginationOrderByField(int page, int size, String field, String order){
		Sort sort = new Sort(new Order(Direction.fromString(order), field));
	
		return repository.findAll(new PageRequest(page, size, sort));
	
	}
	
	public Page<Usuario> findByPagination(int page, int size){
		Pageable pageable = new PageRequest(page, size);
		
		return repository.findAllByOrderByNomeAsc(pageable);
	}

	@Transactional(readOnly = false)
	public void updateNameAndEmail(Usuario usuario) {
		
		Usuario persistente = repository.findOne(usuario.getId());
		
		persistente.setNome(usuario.getNome());
		persistente.setEmail(usuario.getEmail());
		
		repository.save(persistente);
		
		// repository.updateNameAndEmail(usuario.getNome(), usuario.getEmail(), usuario.getId());
	}

	@Transactional(readOnly = false)
	public void save(Usuario usuario) {
		if (usuario.getDataCadastro() == null) {
			usuario.setDataCadastro(LocalDate.now());
		}

		String hash = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(hash);
		usuario.setPerfil(Perfil.LEITOR);

		repository.save(usuario);
	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(id);
	}

	public Usuario findById(Long id) {
		return repository.findOne(id);
	}

	public Usuario findByEmail(String email) {
		return repository.findByEmail(email);
	}

	public Usuario findByAvatar(Avatar avatar) {
		return repository.findByAvatar(avatar);
	}

	public List<Usuario> findAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = false)
	public void updateSenha(Usuario usuario) {
		
		Usuario persistente = repository.findOne(usuario.getId());
		
		String hash = new BCryptPasswordEncoder().encode(usuario.getSenha());
		
		persistente.setSenha(hash);
		
		repository.save(persistente);
		
//		repository.updateSenha(usuario.getSenha(), usuario.getId());

	}

	@Transactional
	public void updatePerfil(Usuario usuario) {
		
		Usuario persistente = repository.findOne(usuario.getId());
		persistente.setPerfil(usuario.getPerfil());
		
		repository.save(persistente);
	}

}
