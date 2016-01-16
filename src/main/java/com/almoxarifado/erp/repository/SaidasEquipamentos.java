package com.almoxarifado.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.almoxarifado.erp.model.Saida;
import com.almoxarifado.erp.model.SaidaEquipamento;

public class SaidasEquipamentos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public SaidaEquipamento porId(Long id) {
		return manager.find(SaidaEquipamento.class, id);
	}
	
	public List<SaidaEquipamento> todasSaidasEquipamentos() { 
		return manager.createQuery("FROM SaidaEquipamento", SaidaEquipamento.class).getResultList();		
	}
			
	public List<SaidaEquipamento> selecionados(Saida saida) {
				
		String sql = "FROM SaidaEquipamento where saida = "+saida.getId();
		return manager.createQuery(sql, SaidaEquipamento.class).getResultList();
		
	}
	
	public SaidaEquipamento guardar(SaidaEquipamento saidaEquipamento) {
		return manager.merge(saidaEquipamento);
	}
	
	public void remover(SaidaEquipamento saidaEquipamento) {
		saidaEquipamento = porId(saidaEquipamento.getId());
		manager.remove(saidaEquipamento);
	}

	public int contar(Saida saidaSelecionada) {
		int conta=0;
		for (int i = 0; i < todasSaidasEquipamentos().size(); i++) {
			if(todasSaidasEquipamentos().get(i).getEquipamento().equals(saidaSelecionada)){
				conta++;
			}			
		}		
		return conta;
	}

	public SaidaEquipamento equipamentosSaida(SaidaEquipamento saidaEquipamento) {
		
		for (int i = 0; i < todasSaidasEquipamentos().size(); i++) {
			SaidaEquipamento selecionada = new SaidaEquipamento();
			if(todasSaidasEquipamentos().set(i, selecionada) == saidaEquipamento){
					return selecionada;	
				}
		}
		return null;
		
	}	
}