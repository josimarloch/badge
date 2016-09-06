/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.loch.badge.dao;

import br.net.loch.badge.beans.Carteirinha;
import br.net.loch.badge.beans.Usuario;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author josimar
 */
public class UsuarioDaoSQLite extends DaoSqliteGenerico<Usuario> {

    public UsuarioDaoSQLite() {
        super(Usuario.class);
    }

    public Usuario findByEmail(String email) {
        Query query = em.createQuery("select u FROM Usuario u where u.email=:email ");
        query.setParameter("email", email);
        return (Usuario) query.getSingleResult();
    }

    @Override
    public void save(Usuario usuario){
        Usuario findByEmail = findByEmail(usuario.getEmail());
        if (findByEmail != null) {
            new Exception("ERRO: ja existe um usuario cadastrado com este email!");
        } else {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        }
    }

}
