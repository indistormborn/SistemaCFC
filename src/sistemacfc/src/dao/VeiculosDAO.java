/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacfc.src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sistemacfc.src.model.Veiculo;

/**
 *
 * @author Indiara
 */
public class VeiculosDAO {
    
    private Conexao conexao;
    
    public VeiculosDAO(){
        this.conexao = new Conexao();
    }
    
    public Veiculo getVeiculoByPlaca(String placa){
        return new Veiculo();
    }
    
    public String getCursoByVeiculo(String placa){
        return null;
    }
    
    public ArrayList<Veiculo> getVeiculosByCurso(String curso) throws ClassNotFoundException, SQLException {
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        Connection conn = conexao.novaConexao();
        String query = "SELECT * FROM veiculos WHERE tipo='"+curso+"'";
        PreparedStatement stm = conn.prepareStatement(query);
        ResultSet set = stm.executeQuery();
        while(set.next()){
            Veiculo veiculo = new Veiculo();
            veiculo.setPlaca(set.getString("placa"));
            veiculos.add(veiculo);
        }
        conn.close();
        return veiculos;
    }
    
    public Veiculo getVeiculoByInstrutor(String instrutor){
        return null;
    }
    
    public ArrayList<Veiculo> getVeiculosDisponiveis(){
        return new ArrayList<>();
    }
    
}
