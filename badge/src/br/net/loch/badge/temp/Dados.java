/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.net.loch.badge.temp;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author josimar
 */
public class Dados {
private static  HashMap<String, Object> dados = new HashMap<>();
    public Dados() {
       
    }

    public static Object get(String key) {
        return dados.get(key);
    }

    public static void put(String key, Object dado) {
        Dados.dados.put(key, dado);
    }
    
    
}
