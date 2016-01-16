package com.almoxarifado.erp.service;

import java.io.Serializable;
import java.util.Arrays;

import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import com.almoxarifado.erp.controller.GestaoSaidasBean;
import com.almoxarifado.erp.model.Equipamento;
import com.almoxarifado.erp.model.Saida;
import com.almoxarifado.erp.model.SaidaEquipamento;
import com.almoxarifado.erp.repository.SaidasEquipamentos;
import com.almoxarifado.erp.util.FacesMessages;
import com.almoxarifado.erp.util.Transacional;


public class CadastroSaidaEquipamentoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	protected FacesMessages mensages;
	
	@Inject
	private Saida saidaEdicao;
	GestaoSaidasBean saidaBean;
	
	@Inject
	private SaidasEquipamentos saidasEquipamentos;
	
	@Inject
	private SaidaEquipamento  saidaEquipamentoEdicao;
	
	@Inject
	private Equipamento equipamentoEdicao;
		
	@Transacional
	public void salvar(SaidaEquipamento saidaEquipamento) {
	
		saidaEquipamentoEdicao.setEquipamento(equipamentoEdicao);
		saidaEquipamentoEdicao.setSaida(saidaEdicao);
		
		saidasEquipamentos.guardar(saidaEquipamento);
		mensages.info("Equipamento separado com sucesso!");
		RequestContext.getCurrentInstance().update(
		Arrays.asList("msgs-dialog:msgs", "frm-saida:equipamento-selecionado"));
	}
	
	@Transacional
	public void excluir(SaidaEquipamento saidaEquipamento) {
		saidasEquipamentos.remover(saidaEquipamento);
	}		
}