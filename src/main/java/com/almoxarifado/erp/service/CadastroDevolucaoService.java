package com.almoxarifado.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.almoxarifado.erp.model.Devolucao;
import com.almoxarifado.erp.model.Solicitacao;
import com.almoxarifado.erp.model.StatusSolicitacao;
import com.almoxarifado.erp.repository.Devolucoes;
import com.almoxarifado.erp.repository.DevolucoesEquipamentos;
import com.almoxarifado.erp.repository.Solicitacoes;
import com.almoxarifado.erp.util.FacesMessages;
import com.almoxarifado.erp.util.Transacional;


public class CadastroDevolucaoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FacesMessages mensages;
	
	@Inject 
	DevolucoesEquipamentos devolucoesEquipamentos;
	
	@Inject
	private Devolucoes devolucoes;
	
	@Inject
	private Solicitacoes solicitacoes;
	private Solicitacao solicitacaoEdicao;
		
	@Transacional
	public void salvar(Devolucao devolucao) {
		
		devolucoes.guardar(devolucao);
	}
	
	@Transacional
	public void excluir(Devolucao devolucaoSelecionada) {
		if(devolucoesEquipamentos.contar(devolucaoSelecionada) >= 1){
			mensages.error("Não é permitido excluir devolução usada em equipamentos");
		}else{
		devolucoes.remover(devolucaoSelecionada);
		solicitacaoEdicao = devolucaoSelecionada.getSolicitacao();
		solicitacaoEdicao.setStatusSolicitacao(StatusSolicitacao.ABERTA);
		solicitacoes.guardar(solicitacaoEdicao);
		mensages.info("Devolução "+ solicitacaoEdicao.getOrdemServico()+" excluida com sucesso.");
		devolucaoSelecionada = null;
		}
	}

	@Transacional
	public void alterar(Devolucao devolucao) {
		devolucoes.guardar(devolucao);		
	}
}