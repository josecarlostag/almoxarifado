package com.almoxarifado.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.almoxarifado.erp.model.Fornecedor;
import com.almoxarifado.erp.repository.Entradas;
import com.almoxarifado.erp.repository.Fornecedores;
import com.almoxarifado.erp.util.FacesMessages;
import com.almoxarifado.erp.util.Transacional;


public class CadastroFornecedorService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Fornecedores fornecedores;
	
	@Inject
	private Entradas entradas;
		
	@Transacional
	public void salvar(Fornecedor fornecedor) {
		fornecedores.guardar(fornecedor);
	}
	
	@Inject
	private FacesMessages mensages;
		
	@Transacional
	public void excluir(Fornecedor fornecedorSelecionado) {
		if(entradas.contar(fornecedorSelecionado) >= 1){
			mensages.error("Não é permitido excluir fornecedor usado em solicitações");
		}else {
		fornecedores.remover(fornecedorSelecionado);
		mensages.info("Fornecedor "+ fornecedorSelecionado.getNomeFornecedor()+" excluido com sucesso.");
		fornecedorSelecionado = null;
	}
	}
}