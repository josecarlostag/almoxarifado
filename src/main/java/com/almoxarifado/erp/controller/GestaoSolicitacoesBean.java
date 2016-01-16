package com.almoxarifado.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.almoxarifado.erp.model.Solicitacao;
import com.almoxarifado.erp.model.StatusSolicitacao;
import com.almoxarifado.erp.model.TipoSolicitacao;
import com.almoxarifado.erp.repository.Solicitacoes;
import com.almoxarifado.erp.service.CadastroSolicitacaoService;
import com.almoxarifado.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoSolicitacoesBean extends GestaoUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;
		
	@Inject
	private CadastroSolicitacaoService cadastroSolicitacao;
	
	@Inject
	private FacesMessages mensages;
	
	@Inject
	private Solicitacoes solicitacoes;
	
	private List<Solicitacao> todasSolicitacoes;
	private List<Solicitacao> solicitacoesSaida;
	private List<Solicitacao> solicitacoesEntrada;
	private List<Solicitacao> solicitacoesDevolucoes;
	
	private Solicitacao solicitacaoEdicao = new Solicitacao();
	private Solicitacao solicitacaoSelecionada;
			
	public void prepararNovoCadastro() {
		solicitacaoEdicao = new Solicitacao();
		solicitacaoEdicao.setStatusSolicitacao(StatusSolicitacao.ABERTA);
	}
		
	public void salvar() {		
		cadastroSolicitacao.salvar(solicitacaoEdicao);		
		consultar();		
		mensages.info("Solicitacao "+solicitacaoEdicao.getOrdemServico()+" salva com sucesso!");
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-solicitacao:msgs", "frm-solicitacao:solicitacoes-table"));
	}
		
	public void excluir() {
		cadastroSolicitacao.excluir(solicitacaoSelecionada);						
		consultar();
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-solicitacao:msgs", "frm-solicitacao:solicitacoes-table"));
	}
	
	
	public void consultar(){
		todasSolicitacoes = solicitacoes.todasSolicitacoes();	
	}
	
	public void consultarSaida(){
		todasSolicitacoes = solicitacoes.todasSolicitacoesSaidas();	
	}
	
	public void consultarDevolucao(){
		todasSolicitacoes = solicitacoes.todasSolicitacoesDevolucoes();
	}
	
	public void consultarEntrada(){
		todasSolicitacoes = solicitacoes.todasSolicitacoesEntradas();
	}
	
	public List<Solicitacao> getTodasSolicitacoes() {
		return todasSolicitacoes;
	}
		
	public List<Solicitacao> getSolicitacoesSaida() {
		this.solicitacoesSaida = solicitacoes.todasSolicitacoesSaidas();
		return this.solicitacoesSaida;
	}

	public List<Solicitacao> getSolicitacoesEntrada() {
		this.solicitacoesEntrada = solicitacoes.todasSolicitacoesEntradas();
		return solicitacoesEntrada;
	}

	public List<Solicitacao> getSolicitacoesDevolucoes() {
		this.solicitacoesDevolucoes = solicitacoes.todasSolicitacoesDevolucoes();
		return solicitacoesDevolucoes;
	}

	public List<Solicitacao> getSolicitacoes() {
		consultar();
		return todasSolicitacoes;
	}
	
			
	public TipoSolicitacao[] getTipoSolicitacao() {
		return TipoSolicitacao.values();
	}
	
	public StatusSolicitacao[] getStatusSolicitacao() {
		return StatusSolicitacao.values();
	}

	public Solicitacao getSolicitacaoEdicao() {	
		return solicitacaoEdicao;			
	}
	

	public void setSolicitacaoEdicao(Solicitacao solicitacaoEdicao) {
		this.solicitacaoEdicao = solicitacaoEdicao;
	}

	public Solicitacao getSolicitacaoSelecionada() {
		return solicitacaoSelecionada;
	}

	public void setSolicitacaoSelecionada(Solicitacao solicitacaoSelecionada) {
		this.solicitacaoSelecionada = solicitacaoSelecionada;
	}
		
}	