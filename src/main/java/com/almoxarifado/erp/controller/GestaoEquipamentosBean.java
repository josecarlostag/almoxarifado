package com.almoxarifado.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.almoxarifado.erp.model.CategoriaEquipamento;
import com.almoxarifado.erp.model.Equipamento;
import com.almoxarifado.erp.model.StatusEquipamento;
import com.almoxarifado.erp.repository.Equipamentos;
import com.almoxarifado.erp.service.CadastroEquipamentoService;
import com.almoxarifado.erp.service.CadastroSaidaEquipamentoService;
import com.almoxarifado.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoEquipamentosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroEquipamentoService cadastroEquipamento;
	
	@Inject CadastroSaidaEquipamentoService cadastroSaidaEquipamento;
	
	@Inject
	private FacesMessages messages;

	@Inject
	private Equipamentos equipamentos;
	
	private List<Equipamento> todosEquipamentos;
	private List<Equipamento> disponiveis;
	private List<Equipamento> utilizados;
	private List<Equipamento> selecionados;
	
	private Equipamento equipamentoEdicao = new Equipamento();

	private Equipamento equipamentoSelecionado;
		
	public void prepararNovoCadastro(){
		equipamentoEdicao = new Equipamento();
		equipamentoEdicao.setStatusEquipamento(StatusEquipamento.Disponível);
	}
	
	public void salvar(){
		cadastroEquipamento.salvar(equipamentoEdicao);		
		consultar();		
		messages.info("Equipamento "+equipamentoEdicao.getPatrimonio()+" salvo com sucesso");
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-equipamento:msgs", "frm-equipamento:equipamentos-table"));
	}
			
	public void excluir(){
		cadastroEquipamento.excluir(equipamentoSelecionado);			
		consultar();
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-equipamento:msgs", "frm-equipamento:equipamentos-table"));
	}
		
	public void consultar() {			
		todosEquipamentos = equipamentos.todosEquipamentos();
		disponiveis = equipamentos.disponiveis();
		utilizados = equipamentos.utilizados();
	}
	
	public List<Equipamento> getTodosEquipamentos() {
		return todosEquipamentos;
	}
			
	public List<Equipamento> getDisponiveis() {
		return disponiveis;
	}
	
	public List<Equipamento> getUtilizados() {
		return utilizados;
	}

	public List<Equipamento> getEquipamentos() {
		consultar();
		return todosEquipamentos;
	}
		
	

	public List<Equipamento> getSelecionados() {
		
		return selecionados;
	}

	
	public CategoriaEquipamento[] getCategoriaEquipamento(){
		return CategoriaEquipamento.values();
	}
	
	public StatusEquipamento[] getSatusEquipamento(){
		return StatusEquipamento.values();
	}
	
	public Equipamento getEquipamentoEdicao() {
		return equipamentoEdicao;
	}

	public void setEquipamentoEdicao(Equipamento equipamentoEdicao) {
		this.equipamentoEdicao = equipamentoEdicao;
	}

	public Equipamento getEquipamentoSelecionado() {
		return equipamentoSelecionado;
	}

	public void setEquipamentoSelecionado(Equipamento equipamentoSelecionado) {
		this.equipamentoSelecionado = equipamentoSelecionado;
	}

}
