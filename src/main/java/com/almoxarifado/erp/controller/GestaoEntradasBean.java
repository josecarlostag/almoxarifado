package com.almoxarifado.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.almoxarifado.erp.model.Entrada;
import com.almoxarifado.erp.model.Solicitacao;
import com.almoxarifado.erp.model.StatusSolicitacao;
import com.almoxarifado.erp.repository.Entradas;
import com.almoxarifado.erp.service.CadastroEntradaService;
import com.almoxarifado.erp.service.CadastroSolicitacaoService;
import com.almoxarifado.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoEntradasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Entradas entradas;
		
	@Inject
	private CadastroEntradaService cadastroEntrada;
	
	@Inject CadastroSolicitacaoService cadastroSolicitacao;
	
	@Inject
	private FacesMessages messages;
	
	private List<Entrada> todasEntradas;
	private Entrada entradaEdicao = new Entrada();
	private Entrada entradaSelecionada;
	
	private Solicitacao solicitacaoEdicao;
	
	public void prepararNovoCadastro() {
		entradaEdicao = new Entrada();		
	}
	
	public void salvar(){
		cadastroEntrada.salvar(entradaEdicao);
		solicitacaoEdicao = entradaEdicao.getSolicitacao();
		solicitacaoEdicao.setStatusSolicitacao(StatusSolicitacao.ATENDIDA);
		cadastroSolicitacao.salvar(solicitacaoEdicao);
		consultar();
		messages.info("Entrada "+solicitacaoEdicao.getOrdemServico()+" salva com sucesso!");
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-entrada:msgs", "frm-entrada:entradas-table"));
	}
	
	public void excluir(){
		cadastroEntrada.excluir(entradaSelecionada);
		consultar();
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-entrada:msgs", "frm-entrada:entradas-table"));
	}
	
	public void consultar() {			
		todasEntradas = entradas.todasEntradas();
	}

	public List<Entrada> getTodasEntradas() {
		return todasEntradas;
	}
	
	public List<Entrada> getEntradas() {
		consultar();
		return todasEntradas;
	}

	public Entrada getEntradaEdicao() {		
		return entradaEdicao;
	}

	public void setEntradaEdicao(Entrada entradaEdicao) {
		this.entradaEdicao = entradaEdicao;
	}

	public Entrada getEntradaSelecionada() {
		return entradaSelecionada;
	}

	public void setEntradaSelecionada(Entrada entradaSelecionada) {
		this.entradaSelecionada = entradaSelecionada;
	}	
}