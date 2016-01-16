package com.almoxarifado.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.almoxarifado.erp.model.Devolucao;
import com.almoxarifado.erp.model.DevolucaoEquipamento;
import com.almoxarifado.erp.model.Equipamento;
import com.almoxarifado.erp.model.Solicitacao;
import com.almoxarifado.erp.model.StatusEquipamento;
import com.almoxarifado.erp.repository.DevolucoesEquipamentos;
import com.almoxarifado.erp.service.CadastroDevolucaoEquipamentoService;
import com.almoxarifado.erp.service.CadastroDevolucaoService;
import com.almoxarifado.erp.service.CadastroEquipamentoService;
import com.almoxarifado.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoDevolucaoEquipamentosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FacesMessages messages;
	
	@Inject
	private DevolucoesEquipamentos devolucoesEquipamentos;
	
	@Inject
	private CadastroDevolucaoEquipamentoService cadastroDevolucaoEquipamento;
	
	@Inject 
	CadastroDevolucaoService cadastroDevolucao;
		
	@Inject
	private Devolucao devolucaoEdicao;


		
	@Inject 
	private Equipamento equipamentoEdicao;
	
	
	@Inject
	private Solicitacao solicitacaoEdicao;
	
	
		
	@Inject 
	private CadastroEquipamentoService cadastroEquipamento;
	
	@Inject
	private GestaoDevolucoesBean devolucaoBean;
		
	private List<DevolucaoEquipamento> todasDevolucoesEquipamentos;
	private List<DevolucaoEquipamento> selecionada;
	
	private DevolucaoEquipamento devolucaoEquipamentoEdicao = new DevolucaoEquipamento();
	private DevolucaoEquipamento devolucaoEquipamentoSelecionado;
		
	public void prepararNovoCadastro(){
		devolucaoEquipamentoEdicao = new DevolucaoEquipamento();
		setEquipamentoEdicao(devolucaoEquipamentoEdicao.getEquipamento());
	}
	
	public void consultar() {			
		todasDevolucoesEquipamentos = devolucoesEquipamentos.todasDevolucoesEquipamento();
	}
			
	public void salvar(){
		Devolucao devolucaoEdicao = devolucaoBean.getDevolucaoEdicao();
		
		devolucaoEquipamentoEdicao.setEquipamento(equipamentoEdicao);
		devolucaoEquipamentoEdicao.setDevolucao(devolucaoEdicao);
		equipamentoEdicao.setStatusEquipamento(StatusEquipamento.Disponível);
		cadastroEquipamento.salvar(equipamentoEdicao);
		cadastroDevolucaoEquipamento.salvar(devolucaoEquipamentoEdicao);
		consultar();
	}
	
	public void excluir(){
		cadastroDevolucaoEquipamento.excluir(devolucaoEquipamentoSelecionado);
		messages.info("Entrada excluida com sucesso.");
		devolucaoEquipamentoSelecionado = null;		
		consultar();		
	}
		
	public List<DevolucaoEquipamento> getTodasDevolucoessEquipamentos() {
		return todasDevolucoesEquipamentos;
	}
	
	public List<DevolucaoEquipamento> getSelecionada() {
		devolucaoEdicao = devolucaoBean.getDevolucaoEdicao();
		if(devolucaoEdicao.getId() != null){
		selecionada = devolucoesEquipamentos.selecionados(devolucaoEdicao);	
		}
		return selecionada;
	}
	
	public List<DevolucaoEquipamento> getSelecionada(Devolucao devolucaoEdicao) {
		this.selecionada = devolucoesEquipamentos.selecionados(devolucaoEdicao) ;
		consultar();
		return selecionada;
	}

	public DevolucaoEquipamento getDevolucaoEquipamentoEdicao() {
		return devolucaoEquipamentoEdicao;
	}

	public void setDevolucaoEquipamentoEdicao(DevolucaoEquipamento devolucaoEquipamentoEdicao) {
		this.devolucaoEquipamentoEdicao = devolucaoEquipamentoEdicao;
	}

	public DevolucaoEquipamento getDevolucaoEquipamentoSelecionado() {
		return devolucaoEquipamentoSelecionado;
	}

	public void setDevolucaoEquipamentoSelecionado(DevolucaoEquipamento devolucaoEquipamentoSelecionado) {
		this.devolucaoEquipamentoSelecionado = devolucaoEquipamentoSelecionado;
	}


	public Equipamento getEquipamentoEdicao() {
		return equipamentoEdicao;
	}

	public void setEquipamentoEdicao(Equipamento equipamentoEdicao) {
		this.equipamentoEdicao = equipamentoEdicao;
	}

	public Devolucao getDevolucaoEdicao() {
		return devolucaoEdicao;
	}

	public void setDevolucaoEdicao(Devolucao devolucaoEdicao) {
		this.devolucaoEdicao = devolucaoEdicao;
	}

	public Solicitacao getSolicitacaoEdicao() {
		return solicitacaoEdicao;
	}
	
	
		
}
