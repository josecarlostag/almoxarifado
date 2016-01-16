package com.almoxarifado.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.almoxarifado.erp.model.Cliente;
import com.almoxarifado.erp.repository.Clientes;
import com.almoxarifado.erp.repository.Solicitacoes;
import com.almoxarifado.erp.util.FacesMessages;
import com.almoxarifado.erp.util.Transacional;


public class CadastroClienteService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FacesMessages mensages;
	
	@Inject
	private Clientes clientes;
	
	@Inject
	private Solicitacoes solicitacoes;
		
	@Transacional
	public void salvar(Cliente cliente) {
		clientes.guardar(cliente);
	}
	
	@Transacional
	public void excluir(Cliente clienteSelecionado) {
		if(solicitacoes.contar(clienteSelecionado) >= 1){
			mensages.error("Não é permitido excluir cliente usado em Solicitação");
		}else {
		 clientes.remover(clienteSelecionado);	
		mensages.info("Cliente "+ clienteSelecionado.getNomeCliente()+" excluido com sucesso.");
		clienteSelecionado = null;
		}
	}

}