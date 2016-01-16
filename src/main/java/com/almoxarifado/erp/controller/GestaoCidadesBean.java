package com.almoxarifado.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.almoxarifado.erp.model.Cidade;
import com.almoxarifado.erp.model.Estado;
import com.almoxarifado.erp.repository.Cidades;
import com.almoxarifado.erp.service.CadastroCidadeService;
import com.almoxarifado.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoCidadesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroCidadeService cadastraCidade;
	
	@Inject
	private FacesMessages messages;

	@Inject
	private Cidades cidades;
			
	private List<Cidade> todasCidades;
	private Cidade cidadeEdicao = new Cidade();
	private Cidade cidadeSelecionada = new Cidade();
	
	
	
	public void prepararNovoCadastro() {
		cidadeEdicao = new Cidade();
	}
		
	public Estado[] getEstados() { 
		return Estado.values(); 
	}
	
	public void salvar(){		
		cadastraCidade.salvar(cidadeEdicao);		
		consultar();		
		messages.info("Cidade "+cidadeEdicao.getNomeCidade()+" "+cidadeEdicao.getEstado()+" salvo com sucesso.");
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-cidade:msgs", "frm-cidade:cidades-table"));
	}
	
	public void consultar() {			
		todasCidades = cidades.todasCidades();	
	}
		
	public void excluir(){				
		cadastraCidade.excluir(cidadeSelecionada);
		consultar();
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-cidade:msgs", "frm-cidade:cidades-table"));
	}
	
	public List<Cidade> getTodasCidades() {			
		return todasCidades;
	}
			
	public List<Cidade> getCidades() {
		consultar();
		return todasCidades;
	}

	public Cidade getCidadeEdicao() {
		return cidadeEdicao;
	}
	
	public void setCidadeEdicao(Cidade cidadeEdicao) {
		this.cidadeEdicao = cidadeEdicao;
	}

	public Cidade getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(Cidade cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}	
}