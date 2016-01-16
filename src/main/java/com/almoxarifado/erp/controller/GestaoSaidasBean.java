package com.almoxarifado.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.almoxarifado.erp.model.Saida;
import com.almoxarifado.erp.model.SaidaEquipamento;
import com.almoxarifado.erp.model.Solicitacao;
import com.almoxarifado.erp.repository.Saidas;
import com.almoxarifado.erp.service.CadastroSaidaService;
import com.almoxarifado.erp.service.CadastroSolicitacaoService;

@Named
@ViewScoped
public class GestaoSaidasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Saidas saidas;
		
	@Inject
	private CadastroSaidaService cadastroSaida;
			
	@Inject 
	CadastroSolicitacaoService cadastroSolicitacao;
			
	@Inject
	Solicitacao solicitacaoEdicao;
	
	
	@Inject
	private GestaoSaidaEquipamentosBean saidaEquipamentoBean ;
	
	private List<Saida> todasSaidas;
	
	private Saida saidaEdicao = new Saida();
	private Saida saidaSelecionada;

	SaidaEquipamento saidaEquipamento;		
	
	public void prepararNovoCadastro(){
		saidaEdicao = new Saida();
				
	}
	
	public void consultar() {			
		todasSaidas = saidas.todasSaidas();	
	}
	
	public void abrir(){
		solicitacaoEdicao = saidaEdicao.getSolicitacao();
		saidaEdicao = saidas.seleciona(solicitacaoEdicao);
		
		if(saidaEdicao == null ){
		prepararNovoCadastro();	
		saidaEdicao.setSolicitacao(solicitacaoEdicao);	
		saidaEdicao.setUsuario(solicitacaoEdicao.getUsuario());
		saidaEdicao.setDataSaida(solicitacaoEdicao.getDataSolicitacao());
	    cadastroSaida.alterar(saidaEdicao);
		consultar();
		}
	
		saidaEquipamentoBean.getSelecionada(saidaEdicao);	
		consultar();	 	
	}
		
	public void salvar() {
		
		cadastroSaida.salvar(saidaEdicao);					
		consultar();
		
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-saida:msgs", "frm-saida:saidas-table"));
	}
			
	public void excluir(){		
		cadastroSaida.excluir(saidaSelecionada);
		consultar();
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-saida:msgs", "frm-saida:saidas-table"));
	}
	
	public List<Saida> getTodasSaidas() {
		return todasSaidas;
	}
	
	
	public Saida getSaidaEdicao() {
		return saidaEdicao;		
	}

	public void setSaidaEdicao(Saida saidaEdicao) {
		this.saidaEdicao = saidaEdicao;
	}

	public Saida getSaidaSelecionada() {
		return saidaSelecionada;
	}

	public void setSaidaSelecionada(Saida saidaSelecionada) {
		this.saidaSelecionada = saidaSelecionada;
	}

	public Solicitacao getSolicitacaoEdicao() {
		return solicitacaoEdicao;
	}

	public void setSolicitacaoEdicao(Solicitacao solicitacaoEdicao) {
		this.solicitacaoEdicao = solicitacaoEdicao;
	}
	
}