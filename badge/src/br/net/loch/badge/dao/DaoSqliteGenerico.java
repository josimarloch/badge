/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.loch.badge.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author josimar
 * @param <T>
 */
public class DaoSqliteGenerico<T> {

    static EntityManagerFactory emf;
    EntityManager em;
    protected Class clazz;


    public void save(T o) {
        em.getTransaction().begin();
        em.persist(o);
        em.getTransaction().commit();

    }

    public void update(T o) {
        em.getTransaction().begin();
        em.merge(o);
        em.getTransaction().commit();

    }
    public void remove(T o) {
        em.getTransaction().begin();
        em.remove(o);
        em.getTransaction().commit();

    }
    public List listAll() {
       
        Query query = em.createQuery("select o from "+clazz.getSimpleName()+" o");
       return query.getResultList();

    }

    public DaoSqliteGenerico(Class clazz) {
        emf = Persistence.createEntityManagerFactory("badgePU");
        this.clazz = clazz;
        em = emf.createEntityManager();
    }

    public T findById(int id) {
        return (T) em.find(clazz, id);
    }

}
