package com.almoxarifado.erp.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.almoxarifado.erp.util.SampleEntity;

@Entity 
@Table(name = "Usuario")
public class Usuario  implements Serializable, SampleEntity{ 
	
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Enumerated(EnumType.STRING) 
	private PerfilUsuario perfil;
	
	@NotEmpty
	@Column(name = "login", nullable = false, length = 18)
	private String login;
	
	@NotEmpty
	@Column(name = "senha", nullable = false, length = 8)
	private String senha;
	
	@Column(name = "nomeUsuario", nullable = false, length = 30)
	private String nomeUsuario;
	
	@Column(name = "matricula", nullable = false, length = 8)
	private String matricula;
	
	@Column(name = "email", nullable = false, length = 30)
	private String email;
	
	@Column(name = "rg", nullable = false, length = 20)
	private String rg;
	
	@NotEmpty
	@Column(name = "empresa", nullable = false, length = 12)
	private String empresa;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PerfilUsuario getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilUsuario perfil) {
		this.perfil = perfil;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@Override 
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
		} 
	
	@Override 
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Usuario other = (Usuario) obj;
		if (id == null) { if (other.id != null)
			return false;
		} else if (!id.equals(other.id)) return false; 
		return true;
	}

	
}
