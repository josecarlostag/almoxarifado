package com.almoxarifado.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.almoxarifado.erp.model.Fornecedor;
import com.almoxarifado.erp.repository.Fornecedores;
import com.almoxarifado.erp.service.CadastroFornecedorService;
import com.almoxarifado.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoFornecedoresBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Fornecedores fornecedores;
		
	@Inject
	private CadastroFornecedorService cadastraFornecedor;
	
	@Inject
	private FacesMessages messages;
	
	private List<Fornecedor> todosFornecedores;
	private List<Fornecedor> fornecedoresCidade;
	private Fornecedor fornecedorEdicao = new Fornecedor();
	private Fornecedor fornecedorSelecionado;
		
	public void consultar() {			
		todosFornecedores = fornecedores.todos();
	}
	
	public void prepararNovoCadastro(){
		fornecedorEdicao = new Fornecedor();
	}
		
	public void salvar(){
		cadastraFornecedor.salvar(fornecedorEdicao);
		consultar();
		messages.info("Fornecedor "+fornecedorEdicao.getNomeFornecedor() +" salvo com sucesso.");
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-fornecedor:msgs", "frm-fornecedor:fornecedores-table"));
	}
	
	public void excluir(){
		cadastraFornecedor.excluir(fornecedorSelecionado);		
		consultar();
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-fornecedor:msgs", "frm-fornecedor:fornecedores-table"));
	}
	
	public List<Fornecedor> getTodosFornecedores() {
		return todosFornecedores;
	}
	
	public List<Fornecedor> getFornecedores() {
		consultar();
		return todosFornecedores;
	}
	

	public Fornecedor getFornecedorEdicao() {
		return fornecedorEdicao;
	}

	public void setFornecedorEdicao(Fornecedor fornecedorEdicao) {
		this.fornecedorEdicao = fornecedorEdicao;
	}

	public Fornecedor getFornecedorSelecionado() {
		return fornecedorSelecionado;
	}

	public void setFornecedorSelecionado(Fornecedor fornecedorSelecionado) {
		this.fornecedorSelecionado = fornecedorSelecionado;
	}

	public List<Fornecedor> getFornecedoresCidade() {
		return fornecedoresCidade;
	}
}