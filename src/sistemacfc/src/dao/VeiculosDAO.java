/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacfc.src.dao;

import java.util.ArrayList;
import sistemacfc.src.model.Veiculo;

/**
 *
 * @author Indiara
 */
public class VeiculosDAO {
    
    
    public Veiculo getVeiculoByPlaca(String placa){
        return new Veiculo();
    }
    
    public ArrayList<Veiculo> getVeiculosByCurso(String curso){
        return new ArrayList<>();
    }
    
    public ArrayList<Veiculo> getVeiculosDisponiveis(){
        return new ArrayList<>();
    }
    
}
