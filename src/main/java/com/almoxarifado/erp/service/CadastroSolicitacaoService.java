package com.almoxarifado.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.almoxarifado.erp.model.Solicitacao;
import com.almoxarifado.erp.model.StatusSolicitacao;
import com.almoxarifado.erp.repository.Solicitacoes;
import com.almoxarifado.erp.util.FacesMessages;
import com.almoxarifado.erp.util.Transacional;


public class CadastroSolicitacaoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FacesMessages mensages;
	
	@Inject
	private Solicitacoes solicitacoes;
		
	@Transacional
	public void salvar(Solicitacao solicitacao) {
		solicitacoes.guardar(solicitacao);
	}
		
	@Transacional
	public void excluir(Solicitacao solicitacaoSelecionada){
		if(solicitacaoSelecionada.getStatusSolicitacao().equals(StatusSolicitacao.ATENDIDA)){
			mensages.error("Não é permitido excluir solicitação atendida");
		}else if(solicitacaoSelecionada.getStatusSolicitacao().equals(StatusSolicitacao.SEPARADA)){
			mensages.error("Não pode excluir solicitação separada");
		}else {
		solicitacoes.remover(solicitacaoSelecionada);
		mensages.info("Solicitação "+solicitacaoSelecionada.getOrdemServico()+" excluida com sucesso.");
		solicitacaoSelecionada = null;
	}
	}
}