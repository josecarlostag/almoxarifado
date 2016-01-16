package com.almoxarifado.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.almoxarifado.erp.model.Equipamento;
import com.almoxarifado.erp.model.StatusEquipamento;
import com.almoxarifado.erp.repository.Equipamentos;
import com.almoxarifado.erp.repository.Saidas;
import com.almoxarifado.erp.repository.SaidasEquipamentos;
import com.almoxarifado.erp.util.FacesMessages;
import com.almoxarifado.erp.util.Transacional;


public class CadastroEquipamentoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	protected FacesMessages mensages;
	
	@Inject Equipamentos equipamentos;
	
	@Inject
	private Saidas saidas;
	
	@Inject 
	SaidasEquipamentos saidasEquipamentos;
		
	@Transacional
	public void salvar(Equipamento equipamento) {
		
		
		equipamentos.guardar(equipamento);
	}
	
	@Transacional
	public void excluir(Equipamento equipamentoSelecionado) {
		if(saidas.contar(equipamentoSelecionado) >= 1){
			mensages.error("Não é permitido excluir equipamento usado em saída");
		}else {
	//	equipamentos.remover(equipamentoSelecionado);
		mensages.info("Equipamento patrimonio "+ equipamentoSelecionado.getPatrimonio()+" excluido com sucesso.");
		equipamentoSelecionado = null;
		}
	}
	
	@Transacional
	public void selecionar(Equipamento equipamentoEdicao) {
		// TODO Auto-generated method stub
		equipamentoEdicao.setStatusEquipamento(StatusEquipamento.Separado);
		equipamentos.guardar(equipamentoEdicao);
		
	}
}
