package com.almoxarifado.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.almoxarifado.erp.model.Cidade;
import com.almoxarifado.erp.model.Fornecedor;
import com.almoxarifado.erp.model.Usuario;


public class Fornecedores  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Fornecedor porId(Long id) {
		return manager.find(Fornecedor.class, id);
	}
	
	public List<Fornecedor> todos() { 
		
		return manager.createQuery("from Fornecedor ORDER BY nomeFornecedor ASC", Fornecedor.class).getResultList();		
	}
	Fornecedor fornecedor = new Fornecedor();
	public Fornecedor fornecedorDaCidade(Cidade cidade) {
		Fornecedor fornecedor = new Fornecedor();
			
			
		for (int i = 0; i < this.todos().size(); i++) {
			if(fornecedor.getCidade().equals(todos().get(i).getCidade()))
			fornecedor.equals(this.todos().get(i).getCidade().equals(cidade));
			todos().get(i).equals(fornecedor.getCidade());
		}
		return fornecedor;
    }
	
	public int contar(){
		int conta=0;
		for (int i = 0; i < todos().size(); i++) {
			conta++;
		}
		
		return conta;		
	}
	
	public Long contaFornecedores(Long id) {
		String cidade= Long.toString(id);
		String sql = "FROM Fornecedor WHERE cidade_id = "+cidade;
		List<Fornecedor> fornecedores = manager.createQuery(sql, Fornecedor.class).getResultList();
		long conta = 0;
		for (int i = 0; i < fornecedores.size(); i++) {
			if(fornecedores.get(i).getCidade().equals(cidade))
				conta++;
		}
			return conta;
	}
	public Fornecedor guardar(Fornecedor fornecedor) {
		return manager.merge(fornecedor);
	}
		
	public void remover(Fornecedor fornecedor) {
		fornecedor = porId(fornecedor.getId());
		manager.remove(fornecedor);
	}
	
	public int contar(Cidade cidade){
		int conta=0;
		for (int i = 0; i < todos().size(); i++) {
			if(todos().get(i).getCidade().equals(cidade)){
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