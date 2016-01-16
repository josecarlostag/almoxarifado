package com.almoxarifado.erp.model;

public enum TipoSolicitacao {
	
	 
	DEVOLUÇÃO("Solicitação de devolução"),
	ENTRADA("Solicitação de entrada"),
	SAÍDA("Solicitação de saída");
	
	private String descricao;

	TipoSolicitacao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
