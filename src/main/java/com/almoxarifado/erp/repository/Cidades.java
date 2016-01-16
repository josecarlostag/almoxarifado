package com.almoxarifado.erp.repository;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import javax.inject.Inject;
import javax.interceptor.InterceptorBinding;
import javax.persistence.EntityManager;

import com.almoxarifado.erp.model.Cidade;
import com.almoxarifado.erp.model.Usuario;
import com.almoxarifado.erp.util.Transacional;

public class Cidades implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	private Cidade[] cidades  = new Cidade[1000];

	

	public Cidade porId(Long id) {
		return manager.find(Cidade.class, id);
	}
	
	public List<Cidade> todasCidades() { 
		return manager.createQuery("from Cidade ORDER BY nomeCidade ASC", Cidade.class).getResultList();				
	}
	
	@Transacional
	public Cidade guardar(Cidade cidade) {
		return manager.merge(cidade);
	}
	
	public void remover(Cidade cidade) {
		cidade = porId(cidade.getId());
		manager.remove(cidade);
	}

	public Cidade cidade(Cidade cidade) {
	for (int i = 0; i < this.cidades.length; i++) {
	      if (cidade == this.cidades[i]) {
	        return this.cidades[i];
	      }
	    }
	    return null;
	  }
	
	public boolean contem(Cidade cidade) {
	    for (int i = 0; i < this.cidades.length; i++) {
	      if (cidade == this.cidades[i]) {
	        return true;
	      }
	    }
	    return false;
	  }

	public Cidade[] getCidades() {
		return cidades;
	}

	public void setCidades(Cidade[] cidades) {
		this.cidades = cidades;
	}
	
	@InterceptorBinding 
	@Retention(RetentionPolicy.RUNTIME) 
	@Target
	({ ElementType.TYPE, ElementType.METHOD })
	public @interface Transactional { 
		
	}

	public int contar(Usuario usuarioSelecionado) {
		int conta=0;
		for (int i = 0; i < todasCidades().size(); i++) {
			if(todasCidades().get(i).getUsuario().equals(usuarioSelecionado)){
				conta++;
			}			
		}		
		return conta;
	}
}
