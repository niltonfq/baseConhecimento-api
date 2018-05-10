package com.abs.baseConhecimento.api.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Categoria implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private Categoria parent;
	
	@SuppressWarnings("unused")
	private Long pai;
	private List<Categoria> subs = new ArrayList<>();
	private List<TopicoCategoria> topicoCategoriaList = new ArrayList<>();
	private Date dataCriacao;
	private Date dataAtualizacao;

	public Categoria() {
	}

	public Categoria(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable=false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "parent")
	public Categoria getParent() {
		return parent;
	}

	public void setParent(Categoria parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent")
	public List<Categoria> getSubs() {
		return subs;
	}

	public void setSubs(List<Categoria> subs) {
		this.subs = subs;
	}

	@OneToMany(mappedBy = "id.categoria")
	public List<TopicoCategoria> getTopicoCategoriaList() {
		return topicoCategoriaList;
	}

	public void setTopicoCategoriaList(List<TopicoCategoria> topicoCategoriaList) {
		this.topicoCategoriaList = topicoCategoriaList;
	}

	@JsonFormat(pattern="dd-MM-yyyy HH:mm")
	@Column(nullable=false)
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@JsonFormat(pattern="dd-MM-yyyy HH:mm")
	@Column(nullable=false)
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	
	@Transient
	public Long getPai() {
		if (parent != null) {
			return parent.getId();
		} else {
			return null;
		}
	}

	public void setPai(Long pai) {
		this.pai = pai;
	}

	@PreUpdate
    public void preUpdate() {
        dataAtualizacao = new Date();
    }
     
    @PrePersist
    public void prePersist() {
        final Date atual = new Date();
        dataCriacao = atual;
        dataAtualizacao = atual;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Categoria [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
