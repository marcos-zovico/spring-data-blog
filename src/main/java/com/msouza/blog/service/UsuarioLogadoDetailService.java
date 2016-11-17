package com.msouza.blog.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.msouza.blog.entity.Usuario;
import com.msouza.blog.entity.UsuarioLogado;

@Service
public class UsuarioLogadoDetailService  implements UserDetailsService {
	
	private static final Logger LOG = Logger.getLogger(UsuarioLogadoDetailService.class);

	@Autowired
	private UsuarioService service;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario;
		try {
			usuario = service.findByEmail(username);
			LOG.info("Usuário encontrado ["+ username + "]");
		} catch (Exception e) {
			LOG.error("Usuário não encontrado ["+ username + "]");
			throw new UsernameNotFoundException("Usuário [ " + username + "] não encontrado no sistema!");
		}
		
		return new UsuarioLogado(usuario);
	}

}
