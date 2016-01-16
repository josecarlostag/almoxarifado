package com.almoxarifado.erp.model;

public enum StatusSolicitacao {
	
	 
	ABERTA("Solicitação aberta"),
	SEPARADA("Solicitação separada"),
	ATENDIDA("Solicitação atendida");
	
	private String descricao;

	StatusSolicitacao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}

