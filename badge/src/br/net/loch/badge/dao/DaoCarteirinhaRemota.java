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
public class DaoCarteirinhaRemota extends DaoGenericsRemoto<Carteirinha> {

    public DaoCarteirinhaRemota() {
        clazz = Carteirinha.class;
    }

    public List<Carteirinha> getNotByCliente(String cliente) {
        Query query = getsession().createQuery("SELECT c FROM Carteirinha c WHERE c.cliente != :serial");
        query.setParameter("serial", cliente);

        return query.list();
    }

    public Long getTotal() {
        Query query = getsession().createQuery("SELECT COUNT(c.id) FROM Carteirinha c");

        return (Long) query.uniqueResult();
    }

}
