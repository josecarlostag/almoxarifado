package com.almoxarifado.erp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.almoxarifado.erp.util.SampleEntity;

@Entity 
@Table(name = "Equipamento")
public class Equipamento implements Serializable, SampleEntity{ 
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	@Column(name = "serie", nullable = false, length = 15)
	private String serie;
	
	@NotNull
	@NotEmpty
	@Column(name = "patrimonio", nullable = false, length = 10)
	private String patrimonio;
	
	@Column(name = "descricao", nullable = false, length = 40)
	private String descricao;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private CategoriaEquipamento categoria;
	
	@Column(name = "marca", nullable = false, length = 10)
	private String marca;
	
	@Column(name = "modelo", nullable = false, length = 10)
	private String modelo;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusEquipamento statusEquipamento;
	
	@NotNull
	@ManyToOne(optional = false) 
	@JoinColumn(name = "entrada_id")
	private Entrada entrada;
	
	@NotNull
	@ManyToOne(optional = false) 
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public CategoriaEquipamento getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEquipamento categoria) {
		this.categoria = categoria;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public StatusEquipamento getStatusEquipamento() {
		return statusEquipamento;
	}

	public void setStatusEquipamento(StatusEquipamento statusEquipamento) {
		this.statusEquipamento = statusEquipamento;
	}

	public Entrada getEntrada() {
		return entrada;
	}

	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
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
		Equipamento other = (Equipamento) obj;
		if (id == null) { if (other.id != null)
			return false;
		} else if (!id.equals(other.id)) return false; 
		return true;		
	}
}
