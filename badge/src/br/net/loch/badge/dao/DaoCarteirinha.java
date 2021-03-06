/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.net.loch.badge.dao;

import br.net.loch.badge.beans.Carteirinha;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author josimar
 */
public class DaoCarteirinha extends DaoGenerics<Carteirinha>{

    public DaoCarteirinha() {
        clazz = Carteirinha.class;
    }
    public List<Carteirinha> getByName(String name){
        Query query = getsession().createQuery("SELECT c FROM Carteirinha c WHERE c.nome LIKE :name");
        query.setParameter("name", "%"+name+"%");
        
        return query.list();
    }
    public Long getTotal(){
        Query query = getsession().createQuery("SELECT COUNT(c.id) FROM Carteirinha c");
        
        return (Long) query.uniqueResult();
    }
    public List<Carteirinha> getByStatus(boolean sicronizado){
        Query query = getsession().createQuery("SELECT c FROM Carteirinha c WHERE c.sincronizado= :status");
        query.setParameter("status", sicronizado);
        
        return query.list();
    }
    
}
