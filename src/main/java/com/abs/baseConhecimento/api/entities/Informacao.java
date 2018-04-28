package com.abs.baseConhecimento.api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Informacao implements Serializable{
	private static final long serialVersionUID = 1L;

	private long id;
	private String descricao;
	private Date dataCriacao;
	private Date dataAtualizacao;
	private Topico topico;
	
	public Informacao() {
		
	}

	public Informacao(long id, String descricao, Topico topico) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.topico = topico;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(nullable=false,columnDefinition="LONGTEXT")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(nullable=false)
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Column(nullable=false)
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="topico_id")
	public Topico getTopico() {
		return topico;
	}

	public void setTopico(Topico topico) {
		this.topico = topico;
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
		Informacao other = (Informacao) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Informacao [id=");
		builder.append(id);
		builder.append(", descricao=");
		builder.append(descricao);
		builder.append(", dataCriacao=");
		builder.append(dataCriacao);
		builder.append(", dataAtualizacao=");
		builder.append(dataAtualizacao);
		builder.append(", topico=");
		builder.append(topico);
		builder.append("]");
		return builder.toString();
	}
	
}
