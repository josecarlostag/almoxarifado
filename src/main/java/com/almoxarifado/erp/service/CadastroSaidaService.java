package com.almoxarifado.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.almoxarifado.erp.model.Saida;
import com.almoxarifado.erp.model.Solicitacao;
import com.almoxarifado.erp.model.StatusSolicitacao;
import com.almoxarifado.erp.model.Usuario;
import com.almoxarifado.erp.repository.Saidas;
import com.almoxarifado.erp.repository.SaidasEquipamentos;
import com.almoxarifado.erp.repository.Solicitacoes;
import com.almoxarifado.erp.util.FacesMessages;
import com.almoxarifado.erp.util.Transacional;

public class CadastroSaidaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Saidas saidas;
	
	@Inject
	private FacesMessages mensages;
	
	@Inject 
	private SaidasEquipamentos saidasEquipamentos;
	
	@Inject 
	private CadastroSolicitacaoService cadastroSolicitacao;
				
	@Inject
	private Usuario usuario;
		
	@Inject
	private Solicitacoes solicitacoes;
	private Solicitacao solicitacaoEdicao;
	
		
	@Transacional
	public void salvar(Saida saidaEdicao)  {
		
		solicitacaoEdicao = saidaEdicao.getSolicitacao();		
		solicitacaoEdicao.setStatusSolicitacao(StatusSolicitacao.ATENDIDA);
		cadastroSolicitacao.salvar(solicitacaoEdicao);		
		saidas.guardar(saidaEdicao);	
		mensages.info("Saida "+solicitacaoEdicao.getOrdemServico()+" salva com sucesso!");		
	}
	
	@Transacional
	public void alterar(Saida saida) {
		saidas.guardar(saida);		
	}
	
	@Transacional
	public void excluir(Saida saidaSelecionada) {	
		
		if(saidasEquipamentos.contar(saidaSelecionada) >= 1){
			mensages.error("Não é permitido excluir saída usada em equipamentos");
		}

		else if(usuario == null){
			mensages.error("Usuário sem permissão para esta ação.");
		}
		
		else{	
		saidas.remover(saidaSelecionada);
		solicitacaoEdicao = saidaSelecionada.getSolicitacao();
		solicitacaoEdicao.setStatusSolicitacao(StatusSolicitacao.ABERTA);
		solicitacoes.guardar(solicitacaoEdicao);		
		mensages.info("Saida "+ solicitacaoEdicao.getOrdemServico()+" excluida com sucesso.");
		
		}
		saidaSelecionada = null;
	}

}