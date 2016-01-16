package com.almoxarifado.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.almoxarifado.erp.model.Cidade;
import com.almoxarifado.erp.repository.Cidades;
import com.almoxarifado.erp.repository.Clientes;
import com.almoxarifado.erp.repository.Fornecedores;
import com.almoxarifado.erp.util.FacesMessages;
import com.almoxarifado.erp.util.Transacional;

public class CadastroCidadeService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FacesMessages mensages;
	
	@Inject
	private Cidades cidades;
	
	@Inject
	private Clientes clientes;
	
	@Inject
	private Fornecedores fornecedores;
			
	@Transacional
	public void salvar(Cidade cidade) {		
		this.cidades.guardar(cidade);
	}
	
	@Transacional 
	public void excluir(Cidade cidadeSelecionada){
			
		if(fornecedores.contar(cidadeSelecionada) >= 1){
			mensages.error("Não é permitido excluir cidade usada por fornecedor");
		}else if(clientes.contar(cidadeSelecionada) >=1){
			mensages.error("Não é permitido excluir cidade usada por cliente");
		}else {
		
		this.cidades.remover(cidadeSelecionada);
		mensages.info("Cidade "+cidadeSelecionada.getNomeCidade()+" "+cidadeSelecionada.getEstado()+" removida com sucesso!");
		cidadeSelecionada = null;
		} 
	}
}