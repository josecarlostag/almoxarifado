package com.almoxarifado.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import com.almoxarifado.erp.model.Equipamento;
import com.almoxarifado.erp.model.Saida;
import com.almoxarifado.erp.model.SaidaEquipamento;
import com.almoxarifado.erp.model.Solicitacao;
import com.almoxarifado.erp.model.Usuario;


public class Saidas implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Saida porId(Long id) {
		return manager.find(Saida.class, id);
	}
	
	Session session;	
	public List<Saida> todasSaidas() { 
		return manager.createQuery("from Saida ORDER BY solicitacao ASC", Saida.class).getResultList();		
	}
		
	@SuppressWarnings("unchecked")
	public List <Saida> saidaTodosEquipamentos(List<Equipamento> equipamentos) {
		Criteria criteria = session.createCriteria(Saida.class, "s");
		Conjunction c = Restrictions.conjunction();
		for (Equipamento e : equipamentos) {
		    c.add(Subqueries.exists(
		      DetachedCriteria.forClass(SaidaEquipamento.class, "se")
		        .setProjection(Projections.id())
		        .add(Restrictions.eqProperty("s.id", "se.saida.id"))
		        .add(Restrictions.eq("se.equipamento",e))));
		  }
		  criteria.add(c);
		  return criteria.list();
	}
		
	public Saida guardar(Saida saida) {
		return manager.merge(saida);
	}
	
	public void remover(Saida saida) {
		saida = porId(saida.getId());
		manager.remove(saida);
	}
	
	public int contar(Usuario usuarioSelecionado) {
		int conta=0;
		for (int i = 0; i < todasSaidas().size(); i++) {
			if(todasSaidas().get(i).getUsuario().equals(usuarioSelecionado)){
				conta++;
			}			
		}
		
		return conta;
	}
	
	public int contar(Equipamento equipamentoSelecionado) {
		int conta=0;
		for (int i = 0; i < todasSaidas().size(); i++) {
			if(todasSaidas().get(i).getSolicitacao().equals(equipamentoSelecionado)){
				conta++;
			}			
		}		
		return conta;
	}

	public Saida seleciona(Solicitacao solicitacaoEdicao) {
		Saida saida = new Saida();
		for (int i = 0; i <todasSaidas().size(); i++) {
			saida = todasSaidas().get(i);		
			if(saida.getSolicitacao().equals(solicitacaoEdicao) == true){
				saida = todasSaidas().get(i);
				return saida;
			}			
		}
		return null;
	}

}
