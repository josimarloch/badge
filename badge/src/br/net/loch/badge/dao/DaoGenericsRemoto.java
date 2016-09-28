/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.net.loch.badge.dao;

/**
 *
 * @author Josimar
 */


import br.net.loch.badge.beans.Usuario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class DaoGenericsRemoto<T>  {

    static Session session = null;
    protected Class clazz;

    public DaoGenericsRemoto() {
        
    }

    protected Session getsession() {
       // session = HibernateConfiguration.getSessionFactory().getCurrentSession();
        if (session == null) {
            session = HibernateConfigurationRemoto.getSessionFactory().openSession();
            return session;
        }else if(!session.isConnected()) {
            session = HibernateConfigurationRemoto.getSessionFactory().openSession();
            return session;
        }else{
            return session;
            
        }
    }

  
    public void save(T o) {

        getsession().saveOrUpdate(o);
        getsession().flush();
        
    }

    public void update(T o) {
        getsession().update(o);
        getsession().flush();
    }


    public void remover(T o) {
        session = HibernateConfiguration.getSessionFactory().openSession();
        session.delete(o);
        session.flush();
    }

    
    public T obterPorId(int id) {
        T objeto = null;
        if (id > 0) {
            session = getsession();
            Query select = session.createQuery(
                    "From " + clazz.getSimpleName() + " where id = " + id);
            objeto = (T) select.uniqueResult();
            session.flush();
        }
        return objeto;
    }
    public T obterPorId(long id) {
        T objeto = null;
        if (id > 0) {
            session = getsession();
            Query select = session.createQuery(
                    "From " + clazz.getSimpleName() + " where id = " + id);
            objeto = (T) select.uniqueResult();
            session.flush();
        }
        return objeto;
    }
    public T obterPorId(String id) {
        T objeto = null;
        if (id !=null) {
            session = getsession();
            Query select = session.createQuery(
                    "From " + clazz.getSimpleName() + " where id = " + id);
            objeto = (T) select.uniqueResult();
            session.flush();
        }
        return objeto;
    }

 
    public List<T> listar(String filtro) {
        List<T> lista = null;
        if (filtro != null) {
            session = HibernateConfigurationRemoto.getSessionFactory().openSession();
            Query query = session.createQuery(filtro);
            lista = query.list();
            session.flush();
        }
        return lista;
    }

 
    public List<T> listar() {
        session = HibernateConfigurationRemoto.getSessionFactory().openSession();
        Query query = session.createQuery("From " + clazz.getSimpleName());
        List<T> lista = query.list();
        session.flush();
        return lista;
    }
    public List<T> getPorUsuario(Usuario usuario ) {
        session = HibernateConfiguration.getSessionFactory().openSession();
        Query query = session.createQuery(" select o From " + clazz.getSimpleName()+" o where o.usuario.id = "+usuario.getId());
        List<T> lista = query.list();
        session.flush();
        return lista;
    }
}
