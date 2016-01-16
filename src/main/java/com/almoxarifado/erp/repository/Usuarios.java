package com.almoxarifado.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.almoxarifado.erp.model.Usuario;

public class Usuarios  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Usuario porId(Long id) {
		return manager.find(Usuario.class, id);
	}
	
	public List<Usuario> todos() { 
		return manager.createQuery("from Usuario ORDER BY nomeUsuario ASC ", Usuario.class).getResultList();		
	}
		
	public Usuario guardar(Usuario usuario) {
		return manager.merge(usuario);
	}
	
	public void remover(Usuario usuario) {
		usuario = porId(usuario.getId());
		manager.remove(usuario);
	}		
}
