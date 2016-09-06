/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.net.loch.badge;

import br.net.loch.badge.beans.Usuario;
import br.net.loch.badge.dao.DaoSqliteGenerico;

/**
 *
 * @author josimar
 */
public class Testes {
    public static void main(String[] args) {
        Usuario usuario = new Usuario();
        usuario.setEmail("josimarlochc2mgmail.com");
        usuario.setSenha("123");
        DaoSqliteGenerico daoU = new DaoSqliteGenerico(Usuario.class);
        daoU.save(usuario);
        System.out.println("Usuario "+daoU.listAll());
    }
}
