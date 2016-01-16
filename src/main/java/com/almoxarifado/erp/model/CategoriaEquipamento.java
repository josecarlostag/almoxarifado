package com.almoxarifado.erp.model;

public enum CategoriaEquipamento {
	
	Monitor("Monitor de video"),
	Impressora("Impressora"),
	Notbook("Notbook"),
	Desktop("Desktop");
	
	private String descricao;

	CategoriaEquipamento(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
