package com.almoxarifado.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.almoxarifado.erp.model.Equipamento;
import com.almoxarifado.erp.model.Saida;
import com.almoxarifado.erp.model.SaidaEquipamento;
import com.almoxarifado.erp.model.StatusEquipamento;
import com.almoxarifado.erp.repository.SaidasEquipamentos;
import com.almoxarifado.erp.service.CadastroEquipamentoService;
import com.almoxarifado.erp.service.CadastroSaidaEquipamentoService;
import com.almoxarifado.erp.service.CadastroSaidaService;
import com.almoxarifado.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoSaidaEquipamentosBean  implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	
	@Inject
	private FacesMessages messages;
		
	@Inject
	private SaidasEquipamentos saidasEquipamentos;
			
	@Inject
	private CadastroSaidaEquipamentoService cadastroSaidaEquipamento;
		
	@Inject 
	private CadastroSaidaService cadastroSaida;
	
	@Inject
	Saida saidaEdicao;
	
	@Inject
	private GestaoSaidasBean saidaBean;
	
	@Inject
	private Equipamento equipamentoEdicao;
	
	@Inject 
	private CadastroEquipamentoService cadastroEquipamento;
	
	private List<SaidaEquipamento> todasSaidasEquipamentos;
	private List<SaidaEquipamento> selecionada;
	
	private SaidaEquipamento saidaEquipamentoEdicao = new SaidaEquipamento();
	private SaidaEquipamento saidaEquipamentoSelecionada;		
	
							
	public void prepararNovoCadastro(){
		saidaEquipamentoEdicao = new SaidaEquipamento();		
		setEquipamentoEdicao(saidaEquipamentoEdicao.getEquipamento());		
	}
		
	public void consultar() {
		todasSaidasEquipamentos = saidasEquipamentos.todasSaidasEquipamentos();			
	}
		
	public void salvar(){
		Saida saidaEdicao = saidaBean.getSaidaEdicao();
		
		saidaEquipamentoEdicao.setEquipamento(equipamentoEdicao);
		saidaEquipamentoEdicao.setSaida(saidaEdicao);
		equipamentoEdicao.setStatusEquipamento(StatusEquipamento.Utilizado);
		cadastroEquipamento.salvar(equipamentoEdicao);		
		cadastroSaidaEquipamento.salvar(saidaEquipamentoEdicao);
		consultar();		
	}
	
	public void excluir(){
		cadastroSaidaEquipamento.excluir(saidaEquipamentoSelecionada);
		messages.info("Equipamento excluido com sucesso.");				
		consultar();		
	}
	
	public List<SaidaEquipamento> getTodasSaidasEquipamentos() {
		
		return todasSaidasEquipamentos;
	}
	
	public List<SaidaEquipamento> getSelecionada() {
		saidaEdicao = saidaBean.getSaidaEdicao();
		if(saidaEdicao.getId() != null){
		selecionada = saidasEquipamentos.selecionados(saidaEdicao) ;
		}
		return selecionada;
	}
	
	public List<SaidaEquipamento> getSelecionada(Saida saidaEdicao) {
		this.selecionada = saidasEquipamentos.selecionados(saidaEdicao) ;
		consultar();
		return selecionada;
		
	}
		
	public SaidasEquipamentos getSaidasEquipamentos() {
		return saidasEquipamentos;
	}

	public SaidaEquipamento getSaidaEquipamentoEdicao() {		
		return this.saidaEquipamentoEdicao;
	}
		
	public void setSaidaEquipamentoEdicao(SaidaEquipamento saidaEquipamentoEdicao) {
		this.saidaEquipamentoEdicao = saidaEquipamentoEdicao;
	}
	
	public SaidaEquipamento getSaidaEquipamentoSelecionada() {
		return saidaEquipamentoSelecionada;
	}

	public CadastroSaidaService getCadastroSaida() {
		return cadastroSaida;
	}

	public Saida getSaidaEdicao() {
		return saidaEdicao;
	}

	public void setSaidaEdicao(Saida saidaEdicao) {
		this.saidaEdicao = saidaEdicao;
	}

	public Equipamento getEquipamentoEdicao() {
		return equipamentoEdicao;
	}

	public void setEquipamentoEdicao(Equipamento equipamentoEdicao) {
		this.equipamentoEdicao = equipamentoEdicao;
	}

		
}