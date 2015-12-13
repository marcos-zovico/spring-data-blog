package com.msouza.blog.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name =  "categorias")
public class Categoria extends AbstractPersistable<Long> {


	private static final long serialVersionUID = -8015991876502059285L;

	@Column(nullable = false, unique = true, length = 30)
	private String descricao;
	
	@Column(nullable = false, unique = true, length = 30)
	private String permaLink;
	
	@ManyToMany
	@JoinTable(
			name = "postagens_has_categorias",
			joinColumns = @JoinColumn(name = "categoria_id"),
			inverseJoinColumns = @JoinColumn(name = "postagem_id")
	)
	private List<Postagem> postagens;
	
	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPermaLink() {
		return permaLink;
	}

	public void setPermaLink(String permaLink) {
		this.permaLink = permaLink;
	}

	public List<Postagem> getPostagens() {
		return postagens;
	}

	public void setPostagens(List<Postagem> postagens) {
		this.postagens = postagens;
	}
	
	
}
