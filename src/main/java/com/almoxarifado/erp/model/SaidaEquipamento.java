package com.almoxarifado.erp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.almoxarifado.erp.util.SampleEntity;

@Entity 
@Table(name = "SaidaEquipamento")
public class SaidaEquipamento implements Serializable, SampleEntity{ 
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
			
	@NotNull
	@ManyToOne(optional = false) 
	@JoinColumn(name = "equipamento_id")
	private Equipamento equipamento;
	
	@NotNull
	@ManyToOne(optional = false) 
	@JoinColumn(name = "saida_id")
	private Saida saida;
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}
	

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}
	
	public Saida getSaida() {
		return saida;
	}

	public void setSaida(Saida saida) {
		this.saida = saida;
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
		SaidaEquipamento other = (SaidaEquipamento) obj;
		if (id == null) { if (other.id != null)
			return false;
		} else if (!id.equals(other.id)) return false; 
		return true;
	}
	
	

}
