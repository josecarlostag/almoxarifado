package com.almoxarifado.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.almoxarifado.erp.model.Cliente;
import com.almoxarifado.erp.model.Saida;
import com.almoxarifado.erp.model.Solicitacao;
import com.almoxarifado.erp.model.Usuario;

public class Solicitacoes implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Solicitacao> solicitacoes;
	
	@Inject
	private Solicitacao solicitacaoEdicao;
	
	@Inject
	private EntityManager manager;
	
	public Solicitacao porId(Long id) {
		return manager.find(Solicitacao.class, id);
	}
	
	public List<Solicitacao> todasSolicitacoes() { 
		return manager.createQuery("from Solicitacao ORDER BY ordemServico ASC", Solicitacao.class).getResultList();		
	}
	
	public List<Solicitacao> todasSolicitacoesEntradas() {
		return manager.createQuery("from Solicitacao WHERE tipo = 'ENTRADA' AND statusSolicitacao = 'ABERTA'", Solicitacao.class).getResultList();		
	}
		
	public List<Solicitacao> todasSolicitacoesSaidas() { 
		return manager.createQuery("from Solicitacao WHERE tipo = 'SAÍDA'", Solicitacao.class).getResultList();		
	}
	
	public List<Solicitacao> todasSolicitacoesDevolucoes() { 
		return manager.createQuery("from Solicitacao WHERE tipo = 'DEVOLUÇÃO'", Solicitacao.class).getResultList();		
	}
		
	public Solicitacao guardar(Solicitacao solicitacao) {
		return manager.merge(solicitacao);
	}
		
	public void remover(Solicitacao solicitacao) {
		solicitacao = porId(solicitacao.getId());
		manager.remove(solicitacao);
	}

	public List<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(List<Solicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

	public int contar(Usuario usuarioSelecionado) {
		int conta=0;
		for (int i = 0; i < todasSolicitacoes().size(); i++) {
			if(todasSolicitacoes().get(i).getUsuario().equals(usuarioSelecionado)){
				conta++;
			}			
		}		
		return conta;
	}
	
	public Solicitacao solicitacaoSaida(Saida saidaSelecionada) {
			this.solicitacaoSaida(saidaSelecionada);
		return solicitacaoSaida(saidaSelecionada);
	}

	public int contar(Cliente clienteSelecionado) {
		int conta=0;
		for (int i = 0; i < todasSolicitacoes().size(); i++) {
			if(todasSolicitacoes().get(i).getCliente().equals(clienteSelecionado)){
				conta++;
			}			
		}		
		return conta;
	}

	public Solicitacao getSolicitacaoEdicao() {
		return solicitacaoEdicao;
	}

	public void setSolicitacaoEdicao(Solicitacao solicitacaoEdicao) {
		this.solicitacaoEdicao = solicitacaoEdicao;
	}
		
}
