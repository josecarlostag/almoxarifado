package com.almoxarifado.erp.model;

public enum StatusEquipamento {
	
	 
	Disponível("Disponível para solicitações"),
	Separado("Separado para solicitação"),
	Utilizado("Em utilização");
	
	private String descricao;

	StatusEquipamento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}

