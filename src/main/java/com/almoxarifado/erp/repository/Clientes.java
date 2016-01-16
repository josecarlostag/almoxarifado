package com.almoxarifado.erp.repository;


import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.almoxarifado.erp.model.Cidade;
import com.almoxarifado.erp.model.Cliente;
import com.almoxarifado.erp.model.Usuario;

public class Clientes  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
		
	public Cliente porId(Long id) {
		return manager.find(Cliente.class, id);
	}
	
	public List<Cliente> clienteCidade(long id) { 
		String cidade= Long.toString(id);
		String sql = "FROM Cliente WHERE cidade-id = " + cidade;
		return manager.createQuery(sql, Cliente.class).getResultList();		
	}
	
	public List<Cliente> todos() { 
		return manager.createQuery("FROM Cliente ORDER BY nomeCliente ASC", Cliente.class).getResultList();		
	}
		
	public Cliente guardar(Cliente cliente) {		
		return manager.merge(cliente);
	}
	
	public void remover(Cliente cliente) {
		cliente = porId(cliente.getId());
		manager.remove(cliente);
	}
	
	
	public int contar(Cidade cidadeSelecionada){
		int conta=0;
		for (int i = 0; i < todos().size(); i++) {
			if(todos().get(i).getCidade().equals(cidadeSelecionada)){
				conta++;
			}			
		}
		
		return conta;		
	}

	public int contar(Usuario usuarioSelecionado) {
		int conta=0;
		for (int i = 0; i < todos().size(); i++) {
			if(todos().get(i).getUsuario().equals(usuarioSelecionado)){
				conta++;
			}			
		}
		
		return conta;
	}
}
