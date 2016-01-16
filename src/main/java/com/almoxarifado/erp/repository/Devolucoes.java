package com.almoxarifado.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.almoxarifado.erp.model.Devolucao;
import com.almoxarifado.erp.model.Solicitacao;
import com.almoxarifado.erp.model.Usuario;

public class Devolucoes  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Devolucao porId(Long id) {
		return manager.find(Devolucao.class, id);
	}
	
	public List<Devolucao> todasDevolucoes() { 
		return manager.createQuery("from Devolucao ORDER BY solicitacao ASC", Devolucao.class).getResultList();		
	}
	
	public Devolucao guardar(Devolucao devolucao) {
		return manager.merge(devolucao);
	}
	
	public void remover(Devolucao devolucao) {
		devolucao = porId(devolucao.getId());
		manager.remove(devolucao);
	}

	public int contar(Usuario usuarioSelecionado) {
		int conta=0;
		for (int i = 0; i < todasDevolucoes().size(); i++) {
			if(todasDevolucoes().get(i).getUsuario().equals(usuarioSelecionado)){
				conta++;
			}			
		}
		
		return conta;
	}

	public Devolucao seleciona(Solicitacao solicitacaoEdicao) {
		Devolucao devolucao = new Devolucao();
		for (int i = 0; i <todasDevolucoes().size(); i++) {
			devolucao = todasDevolucoes().get(i);		
			if(devolucao.getSolicitacao().equals(solicitacaoEdicao) == true){
				devolucao = todasDevolucoes().get(i);
				return devolucao;
			}			
		}
		return null;
	}	
}
