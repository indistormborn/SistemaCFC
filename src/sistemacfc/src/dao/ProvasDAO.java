/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacfc.src.dao;

import java.util.ArrayList;
import sistemacfc.src.model.Prova;

/**
 *
 * @author Indiara
 */
public class ProvasDAO {
     private Conexao conexao;
    
    public ProvasDAO(){
        this.conexao=new Conexao();
    }
    
    public ArrayList<Prova> getProvasByTipo(){
        return null;
    }
}
