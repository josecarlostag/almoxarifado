package com.almoxarifado.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.almoxarifado.erp.model.Devolucao;
import com.almoxarifado.erp.model.DevolucaoEquipamento;

public class DevolucoesEquipamentos  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public DevolucaoEquipamento porId(Long id) {
		return manager.find(DevolucaoEquipamento.class, id);
	}
	
	public List<DevolucaoEquipamento> todasDevolucoesEquipamento() { 
		return manager.createQuery("from DevolucaoEquipamento", DevolucaoEquipamento.class).getResultList();		
	}
	
	public DevolucaoEquipamento guardar(DevolucaoEquipamento devolucaoEquipamento) {
		return manager.merge(devolucaoEquipamento);
	}
	
	public void remover(DevolucaoEquipamento devolucaoEquipamento) {
		devolucaoEquipamento = porId(devolucaoEquipamento.getId());
		manager.remove(devolucaoEquipamento);
	}

	public int contar(Devolucao devolucaoSelecionada) {
		int conta=0;
		for (int i = 0; i < todasDevolucoesEquipamento().size(); i++) {
			if(todasDevolucoesEquipamento().get(i).getEquipamento().equals(devolucaoSelecionada)){
				conta++;
			}			
		}		
		return conta;
	}

	public List<DevolucaoEquipamento> selecionados(Devolucao devolucao) {
		String sql = "FROM DevolucaoEquipamento where devolucao = "+devolucao.getId();
		return manager.createQuery(sql, DevolucaoEquipamento.class).getResultList();
		
	}

	public DevolucaoEquipamento seleciona(Devolucao devolucaoEdicao) {
		DevolucaoEquipamento devolucaoEquipamento = new DevolucaoEquipamento();
		for (int i = 0; i < todasDevolucoesEquipamento().size(); i++) {
			devolucaoEquipamento = todasDevolucoesEquipamento().get(i);
			if(devolucaoEquipamento.getDevolucao().equals(devolucaoEdicao) == true){
				devolucaoEquipamento = todasDevolucoesEquipamento().get(i);
				return devolucaoEquipamento;
			}
		}
		return null;
	}
	
	
}
