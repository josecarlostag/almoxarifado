package com.almoxarifado.erp.model;

public enum PerfilUsuario {
	
	ADMIN("Administrador do sistema"),
	USU("Usuário do sistema"),
	TESTE("Teste do sistema");
	
	private String descricao;

	PerfilUsuario(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
