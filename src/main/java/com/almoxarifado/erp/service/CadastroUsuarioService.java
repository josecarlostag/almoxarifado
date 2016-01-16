package com.almoxarifado.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.almoxarifado.erp.model.Usuario;
import com.almoxarifado.erp.repository.Cidades;
import com.almoxarifado.erp.repository.Clientes;
import com.almoxarifado.erp.repository.Devolucoes;
import com.almoxarifado.erp.repository.Entradas;
import com.almoxarifado.erp.repository.Equipamentos;
import com.almoxarifado.erp.repository.Fornecedores;
import com.almoxarifado.erp.repository.Saidas;
import com.almoxarifado.erp.repository.Solicitacoes;
import com.almoxarifado.erp.repository.Usuarios;
import com.almoxarifado.erp.util.FacesMessages;
import com.almoxarifado.erp.util.Transacional;


public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios;
	
	@Inject
	private FacesMessages mensages;
	
	@Inject
	private Cidades cidades;
	
	@Inject
	private Clientes clientes;
	
	@Inject
	private Devolucoes devolucoes;
	
	@Inject
	private Entradas entradas;
	
	@Inject
	private Equipamentos equipamentos;
	
	@Inject
	private Fornecedores fornecedores;
	
	@Inject
	private Saidas saidas;
	
	
	@Inject
	private Solicitacoes solicitacoes;
	
	
			
	@Transacional
	public void salvar(Usuario usuario) {
		usuarios.guardar(usuario);
	}
	
	@Transacional
	public void excluir(Usuario usuarioSelecionado) {
		if(cidades.contar(usuarioSelecionado) >= 1){
			mensages.error("Não é permitido excluir usuario usado em cidade");
		}else if(clientes.contar(usuarioSelecionado) >=1){
			mensages.error("Não é permitido excluir usuario usado em cliente");
		}else if(devolucoes.contar(usuarioSelecionado) >= 1){
			mensages.error("Não é permitido excluir usuario usado em devolução");
		}else if(entradas.contar(usuarioSelecionado) >=1){
			mensages.error("Não é permitido excluir cusuario usado em entrada");
		}else if(equipamentos.contar(usuarioSelecionado) >= 1){
			mensages.error("Não é permitido excluir usuario usado em equipamento");
		}else if(fornecedores.contar(usuarioSelecionado) >=1){
			mensages.error("Não é permitido excluir usuario usado em fornecedor");
		}else if(saidas.contar(usuarioSelecionado) >= 1){
			mensages.error("Não é permitido excluir usuario usado em saída");
		}else if(solicitacoes.contar(usuarioSelecionado) >=1){
			mensages.error("Não é permitido excluir cusuario usado em solicitação");
		}else {
		usuarios.remover(usuarioSelecionado);
		mensages.info("Usuário "+ usuarioSelecionado.getNomeUsuario()+" excluido com sucesso.");
		usuarioSelecionado = null;
	}

	}	
}

