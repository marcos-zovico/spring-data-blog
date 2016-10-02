package com.msouza.blog.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.msouza.blog.entity.Usuario;

public class UsuarioValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Usuario.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		Usuario usuario = (Usuario) target;

		if (usuario.getNome() != null) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "nome",
					"Este campo � obrigat�rio");
		}

		if (usuario.getEmail() != null) {

			Pattern pattern = Pattern.compile(".+@.+\\..+");
			Matcher matcher = pattern.matcher(usuario.getEmail());

			if (!matcher.matches()) {
				errors.rejectValue("email", "email",
						"Inseira um e-mail valido.");
			}

		}

		if (usuario.getSenha() != null) {

			if (usuario.getSenha().length() > 8
					|| usuario.getSenha().length() < 5) {
				errors.rejectValue("senha", "senha",
						"A senha deve conter entre 8 e cinco caraxteres.");
			}
		}
		
		if (usuario.getFile() != null) {
			
			if (usuario.getFile().getSize() == 0) {
				errors.rejectValue("file", "file", "Selecione uma mensagem ate 100kb.");
			}
		}

	}

}
