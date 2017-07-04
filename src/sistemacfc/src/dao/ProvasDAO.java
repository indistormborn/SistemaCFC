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
import sistemacfc.src.model.Prova;

/**
 *
 * @author Indiara
 */
public class ProvasDAO {

    private Conexao conexao;

    public ProvasDAO() {
        this.conexao = new Conexao();
    }

    public ArrayList<Prova> getProvasByTipo(String tipo) throws ClassNotFoundException, SQLException {
        ArrayList<Prova> provas = new ArrayList<>();
        Connection conn = conexao.novaConexao();
        String query = "SELECT * FROM provas WHERE tipo='" + tipo + "' ORDER BY data";
        PreparedStatement stm = conn.prepareStatement(query);
        ResultSet set = stm.executeQuery();
        while (set.next()) {
            Prova prova = new Prova();
            prova.setCodigo(set.getInt("codigo"));
            prova.setData(set.getDate("data"));
            prova.setHora(String.valueOf(set.getInt("hora")));
            prova.setTipo(set.getString("tipo"));
            provas.add(prova);
        }
        return provas;
    }

    public Prova getProvaByData(String data) throws ClassNotFoundException, SQLException {
        Prova prova = null;
        Connection conn = conexao.novaConexao();
        String query = "SELECT * FROM provas WHERE data='" + data + "'";
        PreparedStatement stm = conn.prepareStatement(query);
        ResultSet set = stm.executeQuery();
        if (set.next()) {
            prova = new Prova();
            prova.setCodigo(set.getInt("codigo"));
            prova.setData(set.getDate("data"));
            prova.setHora(String.valueOf(set.getInt("hora")));
            prova.setTipo(set.getString("tipo"));
        
        }
        return prova;
    }
    
    public String getTipoUltimaProva(String cpf){
        return null;
    }
    
    public void atualizaDesempenho(String cpf, String desempenho){
        
    }
}
