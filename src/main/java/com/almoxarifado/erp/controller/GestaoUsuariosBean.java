package com.almoxarifado.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.almoxarifado.erp.model.PerfilUsuario;
import com.almoxarifado.erp.model.Usuario;
import com.almoxarifado.erp.repository.Usuarios;
import com.almoxarifado.erp.service.CadastroUsuarioService;
import com.almoxarifado.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios;
	
	@Inject
	private CadastroUsuarioService cadastraUsuario;
	
	@Inject
	private FacesMessages messages;
	
	private List<Usuario> todosUsuarios;
	private Usuario usuarioEdicao = new Usuario();
	private Usuario usuarioSelecionado;
			
	public void consultar() {			
		todosUsuarios = usuarios.todos();		
	}
	
	public void prepararNovoCadastro() {
		usuarioEdicao = new Usuario();
	}
	
	public void salvar(){
		cadastraUsuario.salvar(usuarioEdicao);		
		consultar();		
		messages.info("Usuario " +usuarioEdicao.getLogin()+ " salvo com sucesso.");
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-usuario:msgs", "frm-usuario:usuarios-table"));
	}
	
	public void excluir(){
		cadastraUsuario.excluir(usuarioSelecionado);				
		consultar();
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-usuario:msgs", "frm-usuario:usuarios-table"));
	}
	
	public PerfilUsuario[] getPerfilUsuario() {
		return PerfilUsuario.values();
	}
	
	public List<Usuario> getTodosUsuarios() {
		return todosUsuarios;
	}
	
	public List<Usuario> getUsuarios() {
		consultar();
		return todosUsuarios;
	}
	
	public Usuario getUsuarioEdicao() {
		return usuarioEdicao;
	}

	public void setUsuarioEdicao(Usuario usuarioEdicao) {
		this.usuarioEdicao = usuarioEdicao;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
}
