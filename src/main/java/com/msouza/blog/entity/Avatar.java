package com.msouza.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "avatares")
public class Avatar extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -4123534154934873961L;

	@Column(nullable = false)
	private String titulo;

	@Column(nullable = false)
	private String tipo;

	@Lob
	@Column(nullable = false)
	private byte[] avatar;

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}
	
	

}
