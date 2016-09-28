/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.net.loch.badge.sincronizacao;

import br.net.loch.badge.beans.Carteirinha;
import br.net.loch.badge.dao.DaoCarteirinha;
import br.net.loch.badge.dao.DaoCarteirinhaRemota;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author josimar
 */
public class Sincronizacao {

    public void geraSincronizacaoBilateral() {

    }

    public void geraSincronizacaoServidorCliente() {
        DaoCarteirinhaRemota dcr = new DaoCarteirinhaRemota();
        DaoCarteirinha dcl = new DaoCarteirinha();
        geraSincronizacaoClienteServidor();
        if(checaStatus()){
            System.out.println("Sincronizado...");
        }
        List<Carteirinha> cLocal = dcl.listar();
        cLocal.stream().forEach((cl) -> {
            dcl.remover(cl);
        });
        List<Carteirinha> cRemoto = dcr.listar();
        for (Carteirinha cr : cRemoto) {
            cr.setId(null);
            dcl.save(cr);
        }
        
    }

    private boolean checaStatus() {
        DaoCarteirinhaRemota dcr = new DaoCarteirinhaRemota();
        DaoCarteirinha dcl = new DaoCarteirinha();
        return Objects.equals(dcr.getTotal(), dcl.getTotal());

    }

    public void geraSincronizacaoClienteServidor() {
        DaoCarteirinha dcl = new DaoCarteirinha();
        List<Carteirinha> nSincronizadas = dcl.getByStatus(false);
        DaoCarteirinhaRemota dcr = new DaoCarteirinhaRemota();

        System.out.println(nSincronizadas.size() + " carteirinhas não sincronizadas");
        for (Carteirinha c : nSincronizadas) {
            int id = c.getId();
            c.setId(null);
            c.setSincronizado(true);
            System.out.println("Salvando carteirinha remotamente..." + c.getNome());
            dcr.save(c);
            c.setId(id);
            System.out.println("Salvando Localmente "+c.getNome());
            dcl.save(c);

        }

    }

    public static void main(String[] args) {
        new Sincronizacao().geraSincronizacaoServidorCliente();
    }
}
