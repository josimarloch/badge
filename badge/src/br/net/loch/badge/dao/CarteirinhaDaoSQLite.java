/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.loch.badge.dao;

import br.net.loch.badge.beans.Carteirinha;
import java.util.List;
import javax.persistence.Query;


/**
 *
 * @author josimar
 */
public class CarteirinhaDaoSQLite extends DaoSqliteGenerico<Carteirinha>{

    public CarteirinhaDaoSQLite() {
        super(Carteirinha.class);
    }
    public List<Carteirinha> findByNome(String nome){
        Query query = em.createQuery("select c FROM Carteirinha c where c.nome =:nome ");
        query.setParameter("nome", nome);
       return query.getResultList();
    }


}
