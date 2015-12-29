package com.msouza.blog.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "comentarios")
public class Comentario extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 7751443601921669475L;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String texto;

	@Column(name = "data_comentario")
	private LocalDateTime dataComentario;

	@ManyToOne(fetch = FetchType.EAGER)
	private Postagem postagem;
	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuario;

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getDataComentario() {
		return dataComentario;
	}

	public void setDataComentario(LocalDateTime dataComentario) {
		this.dataComentario = dataComentario;
	}

	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
