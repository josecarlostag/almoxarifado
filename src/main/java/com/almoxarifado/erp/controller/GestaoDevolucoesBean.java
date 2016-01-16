package com.almoxarifado.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.almoxarifado.erp.model.Devolucao;
import com.almoxarifado.erp.model.DevolucaoEquipamento;
import com.almoxarifado.erp.model.Equipamento;
import com.almoxarifado.erp.model.Solicitacao;
import com.almoxarifado.erp.model.StatusSolicitacao;
import com.almoxarifado.erp.repository.Devolucoes;
import com.almoxarifado.erp.service.CadastroDevolucaoService;
import com.almoxarifado.erp.service.CadastroSolicitacaoService;
import com.almoxarifado.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoDevolucoesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Devolucoes devolucoes;
	
	
	private Solicitacao solicitacaoEdicao;
		
	@Inject 
	private CadastroSolicitacaoService cadastroSolicitacao;
	
	@Inject 
	private CadastroDevolucaoService cadastroDevolucao;
		
	@Inject
	private FacesMessages messages;
		
	private List<Devolucao> todasDevolucoes;
	private Devolucao devolucaoEdicao = new Devolucao();
	private Devolucao devolucaoSelecionada;
	
	@Inject
	DevolucaoEquipamento devolucaoEquipamento;
	GestaoEquipamentosBean EquipamentoBean;
	
	@Inject
	Equipamento equipamentoEdicao;
	
	
	public void prepararNovoCadastro(){	
		devolucaoEdicao = new Devolucao();		
	}
	
	public void consultar() {			
		todasDevolucoes = devolucoes.todasDevolucoes();
	}
	
	public void abrir(){
		solicitacaoEdicao = devolucaoEdicao.getSolicitacao();
		devolucaoEdicao = devolucoes.seleciona(solicitacaoEdicao);
		
		if(devolucaoEdicao == null ){
		prepararNovoCadastro();	
		devolucaoEdicao.setSolicitacao(solicitacaoEdicao);	
		devolucaoEdicao.setUsuario(solicitacaoEdicao.getUsuario());
		devolucaoEdicao.setDataDevolucao(solicitacaoEdicao.getDataSolicitacao());
	    cadastroDevolucao.alterar(devolucaoEdicao);
		consultar();
		}	
		consultar();	 	
	}
				
	public void salvar(){
		cadastroDevolucao.salvar(devolucaoEdicao);
		solicitacaoEdicao = devolucaoEdicao.getSolicitacao();
		solicitacaoEdicao.setStatusSolicitacao(StatusSolicitacao.ATENDIDA);
		cadastroSolicitacao.salvar(solicitacaoEdicao);
		consultar();
				
		messages.info("Devolução "+solicitacaoEdicao.getOrdemServico()+" salva com sucesso!");
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-devolucao:msgs", "frm-devolucao:devolucoes-table"));
	}
	
	public void excluir(){
		cadastroDevolucao.excluir(devolucaoSelecionada);				
		consultar();
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-devolucao:msgs", "frm-devolucao:devolucoes-table"));
	}
		
	

	public List<Devolucao> getTodasDevolucoes() {
		return todasDevolucoes;
	}

	public Devolucao getDevolucaoEdicao() {
		return devolucaoEdicao;
	}

	public void setDevolucaoEdicao(Devolucao devolucaoEdicao) {
		this.devolucaoEdicao = devolucaoEdicao;
	}

	public Devolucao getDevolucaoSelecionada() {
		return devolucaoSelecionada;
	}

	public void setDevolucaoSelecionada(Devolucao devolucaoSelecionada) {
		this.devolucaoSelecionada = devolucaoSelecionada;
	}		
}
