package com.msouza.blog.web.editor;


import java.beans.PropertyEditorSupport;

import com.msouza.blog.entity.Perfil;

public class PerfilEditorSuport extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text.equals("ADMIN")) {
			super.setValue(Perfil.ADMIN);
		} else if (text.equals("AUTOR")) {
			super.setValue(Perfil.AUTOR);
		} else {
			super.setValue(Perfil.LEITOR);
		}
		
		System.out.println("=================" + text +"==================");
	}

}
