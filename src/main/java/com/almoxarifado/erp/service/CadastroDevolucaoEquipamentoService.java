package com.almoxarifado.erp.service;

import java.io.Serializable;
import java.util.Arrays;

import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import com.almoxarifado.erp.model.Devolucao;
import com.almoxarifado.erp.model.DevolucaoEquipamento;
import com.almoxarifado.erp.model.Equipamento;
import com.almoxarifado.erp.repository.DevolucoesEquipamentos;
import com.almoxarifado.erp.util.FacesMessages;
import com.almoxarifado.erp.util.Transacional;

public class CadastroDevolucaoEquipamentoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	protected FacesMessages mensages;
	
	@Inject
	private DevolucoesEquipamentos devolucoesEquipamentos;
	
	@Inject
	private DevolucaoEquipamento devolucaoEquipamentoEdicao;
	
	@Inject
	private Devolucao devolucaoEdicao;
			
	@Inject 
	private Equipamento equipamentoEdicao;
		
	@Transacional
	public void salvar(DevolucaoEquipamento devolucaoEquipamento) {
	
		devolucaoEquipamentoEdicao.setDevolucao(devolucaoEdicao);
		devolucaoEquipamentoEdicao.setEquipamento(equipamentoEdicao);
		
		devolucoesEquipamentos.guardar(devolucaoEquipamento);
		mensages.info("Equipamento separado com sucesso!");
		RequestContext.getCurrentInstance().update(
				Arrays.asList("msgs-dialog:msgs", "frm-devolucao:equipamento-selecionado"));
	}
	
	@Transacional
	public void excluir(DevolucaoEquipamento devolucaoEquipamento) {
		devolucoesEquipamentos.remover(devolucaoEquipamento);
	}

}