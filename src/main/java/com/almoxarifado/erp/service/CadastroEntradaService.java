package com.almoxarifado.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.almoxarifado.erp.model.Entrada;
import com.almoxarifado.erp.model.Equipamento;
import com.almoxarifado.erp.model.Solicitacao;
import com.almoxarifado.erp.model.StatusSolicitacao;
import com.almoxarifado.erp.repository.Entradas;
import com.almoxarifado.erp.repository.Equipamentos;
import com.almoxarifado.erp.repository.Solicitacoes;
import com.almoxarifado.erp.util.FacesMessages;
import com.almoxarifado.erp.util.Transacional;


public class CadastroEntradaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Entradas entradas;
	
	@Inject
	Equipamentos equipamentos;
	
	@Inject
	private Solicitacoes solicitacoes;
	
	@Inject 
	Equipamento equipamento;
	
	@Inject
	private Solicitacao solicitacao;
	
	@Inject
	private FacesMessages mensages;
		
	@Transacional
	public void salvar(Entrada entrada) {
		
		entradas.guardar(entrada);		
	}
	
	@Transacional
	public void excluir(Entrada entradaSelecionada) {
		solicitacao = entradaSelecionada.getSolicitacao();
		if(equipamentos.contar(entradaSelecionada) >= 1){
			mensages.error("Não é permitido excluir entrada usada em equipamentos");
		}else{
			
			solicitacao.setStatusSolicitacao(StatusSolicitacao.ABERTA);
			entradas.remover(entradaSelecionada);
			solicitacoes.guardar(solicitacao);
			
			entradaSelecionada = null;
			mensages.info("Entrada "+ solicitacao.getOrdemServico()+" excluida com sucesso.");	
		
		}
	}

}
