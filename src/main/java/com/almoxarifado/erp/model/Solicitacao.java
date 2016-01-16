package com.almoxarifado.erp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;

import com.almoxarifado.erp.util.SampleEntity;



@Entity 
@Table(name = "Solicitacao")
public class Solicitacao implements Serializable, SampleEntity{ 
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Past
	@Temporal(TemporalType.DATE)
	@Column(name = "data_solicitacao")
	private Date dataSolicitacao;
	
	
	@NotEmpty
	@Column(name = "ordemServico", nullable = false, length = 10)
	private String ordemServico;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoSolicitacao tipo;
	
	
	@Column(name = "descricao", nullable = false, length = 300)
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private StatusSolicitacao statusSolicitacao;
	
	@NotNull
	@ManyToOne(optional = false) 
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne(optional = false) 
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public String getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(String ordemServico) {
		this.ordemServico = ordemServico;
	}

	public TipoSolicitacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoSolicitacao tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusSolicitacao getStatusSolicitacao() {
		return statusSolicitacao;
	}

	public void setStatusSolicitacao(StatusSolicitacao statusSolicitacao) {
		this.statusSolicitacao = statusSolicitacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Override 
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
		} 
	
	@Override 
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Solicitacao other = (Solicitacao) obj;
		if (id == null) { if (other.id != null)
			return false;
		} else if (!id.equals(other.id)) return false; 
		return true;
	}
}
