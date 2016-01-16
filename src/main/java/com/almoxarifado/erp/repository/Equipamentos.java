package com.almoxarifado.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.almoxarifado.erp.model.Entrada;
import com.almoxarifado.erp.model.Equipamento;
import com.almoxarifado.erp.model.Usuario;

public class Equipamentos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	@Inject
	private Equipamento equipamentoEdicao;
	
	public List<Equipamento> todosEquipamentos() { 
		return manager.createQuery("FROM Equipamento ORDER BY patrimonio ASC", Equipamento.class).getResultList();			
	}
	
	public List<Equipamento> disponiveis() {
		return manager.createQuery("FROM Equipamento WHERE statusEquipamento = 'Disponível' ORDER BY patrimonio ASC", Equipamento.class).getResultList();
	}
	
	public List<Equipamento> utilizados() {
		return manager.createQuery("FROM Equipamento WHERE statusEquipamento = 'Utilizado' ORDER BY patrimonio ASC", Equipamento.class).getResultList();
	}
		
	public Equipamento guardar(Equipamento equipamento) {
		return manager.merge(equipamento);
	}
	
	public void remover(Equipamento equipamento) {
		
		manager.remove(this.equipamentoEdicao);
	}

	public int contar(Usuario usuarioSelecionado) {
		int conta=0;
		for (int i = 0; i < todosEquipamentos().size(); i++) {
			if(todosEquipamentos().get(i).getUsuario().equals(usuarioSelecionado)){
				conta++;
			}			
		}		
		return conta;
	}

	public int contar(Entrada entradaSelecionada) {
		int conta=0;
		for (int i = 0; i < todosEquipamentos().size(); i++) {
			if(todosEquipamentos().get(i).getEntrada().equals(entradaSelecionada)){
				conta++;
			}			
		}		
		return conta;
	}

	public Equipamento getEquipamentoEdicao() {
		return equipamentoEdicao;
	}

	public void setEquipamentoEdicao(Equipamento equipamentoEdicao) {
		this.equipamentoEdicao = equipamentoEdicao;
	}		
}
