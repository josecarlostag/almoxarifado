package com.almoxarifado.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.almoxarifado.erp.model.Cliente;
import com.almoxarifado.erp.repository.Clientes;
import com.almoxarifado.erp.service.CadastroClienteService;
import com.almoxarifado.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoClientesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroClienteService cadastraCliente;
	
	@Inject
	private FacesMessages messages;

	@Inject
	private Clientes clientes;
	
	
	private Cliente clienteEdicao = new Cliente();
	private Cliente clienteSelecionado;
	private List<Cliente> todosClientes;

	
	public void prepararNovoCadastro() {
		clienteEdicao = new Cliente();
	}
	
	public void salvar(){		
		cadastraCliente.salvar(clienteEdicao);		
		consultar();		
		messages.info("Cliente "+clienteEdicao.getNomeCliente()+" salvo com sucesso.");
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-cliente:msgs", "frm-cliente:clientes-table"));
	}
	
	public void excluir(){
		cadastraCliente.excluir(clienteSelecionado);		
		consultar();
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-cliente:msgs", "frm-cliente:clientes-table"));
	}
	
	public List<Cliente> getTodosClientes() {	
		return todosClientes;
	}
	
	public List<Cliente> getClientes() {
		consultar();
		return todosClientes;
	}
		
	public void consultar() {			
		todosClientes = clientes.todos();
	}
	// Usa este metodo para chamar cliente. 
	public Cliente getClienteEdicao() {
		return clienteEdicao;
	}

	public void setClienteEdicao(Cliente clienteEdicao) {
		this.clienteEdicao = clienteEdicao;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

}