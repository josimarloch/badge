/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.net.loch.badge.dao;

import br.net.loch.badge.beans.Usuario;

/**
 *
 * @author josimar
 */
public class DaoUsuario extends DaoGenerics<Usuario>{

    public DaoUsuario() {
        clazz = Usuario.class;
    }
    
}
