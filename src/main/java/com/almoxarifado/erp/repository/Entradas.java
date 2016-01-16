package com.almoxarifado.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.almoxarifado.erp.model.Entrada;
import com.almoxarifado.erp.model.Fornecedor;
import com.almoxarifado.erp.model.Usuario;

public class Entradas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	@Inject
	private Entrada entradaEdicao;
	
	public List<Entrada> todasEntradas() { 
		return manager.createQuery("from Entrada ORDER BY solicitacao ASC", Entrada.class).getResultList();		
	}
	
	public Entrada guardar(Entrada entrada) {
		return manager.merge(entrada);
	}
	
	public void remover(Entrada entrada) {
		
		manager.remove(this.entradaEdicao);
	}

	public int contar(Fornecedor fornecedor) {		
			int conta=0;
			for (int i = 0; i < todasEntradas().size(); i++) {
				if(todasEntradas().get(i).getFornecedor().equals(fornecedor)){
					conta++;
				}			
			}		
			return conta;		
		}

	public int contar(Usuario usuarioSelecionado) {
		int conta=0;
		for (int i = 0; i < todasEntradas().size(); i++) {
			if(todasEntradas().get(i).getUsuario().equals(usuarioSelecionado)){
				conta++;
			}			
		}		
		return conta;
	}

	public int contar(Entrada entradaSelecionada) {
		int conta=0;
		for (int i = 0; i < todasEntradas().size(); i++) {
			if(todasEntradas().get(i).getUsuario().equals(entradaSelecionada)){
				conta++;
			}			
		}		
		return conta;
	}

	public Entrada getEntradaEdicao() {
		return entradaEdicao;
	}

	public void setEntradaEdicao(Entrada entradaEdicao) {
		this.entradaEdicao = entradaEdicao;
	}
	
}
