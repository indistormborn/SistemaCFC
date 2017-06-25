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
import sistemacfc.src.model.Administrador;
import sistemacfc.src.model.Instrutor;
import sistemacfc.src.model.Professor;
import sistemacfc.src.model.Usuario;

/**
 *
 * @author Indiara
 */
public class UsuarioDAO {

    private Conexao conexao;

    public UsuarioDAO() {
        this.conexao = new Conexao();
    }

    public void insert(Usuario user, String role) throws ClassNotFoundException, SQLException {
        try {
            Connection conn = conexao.novaConexao();
            String sql = "INSERT INTO usuario VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, user.getNome());
            stm.setString(2, user.getCpf());
            stm.setString(3, user.getLogin());
            stm.setString(4, user.getSenha());
            stm.setString(5, role);
            stm.execute();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
    
    public Usuario verificaLoginAndSenha(String login, String senha) throws ClassNotFoundException, SQLException{
        Usuario user = null;
        Connection conn = conexao.novaConexao();
        String query = "SELECT * FROM usuario WHERE login='"+login+"' AND senha='"+senha+"'";
        PreparedStatement stm = conn.prepareStatement(query);
        ResultSet set = stm.executeQuery();
        if(set.next()){
            if (set.getString("tipo").equals("ins")) {
                    user = new Instrutor();
                    user.setNome(set.getString("nome"));
                    user.setCpf(set.getString("cpf"));
                    user.setLogin(set.getString("login"));
                    user.setSenha(set.getString("senha"));
                } else if (set.getString("tipo").equals("pro")) {
                    user = new Professor();
                    user.setNome(set.getString("nome"));
                    user.setCpf(set.getString("cpf"));
                    user.setLogin(set.getString("login"));
                    user.setSenha(set.getString("senha"));
                }else{
                    user = new Administrador();
                    user.setNome(set.getString("nome"));
                    user.setCpf(set.getString("cpf"));
                    user.setLogin(set.getString("login"));
                    user.setSenha(set.getString("senha"));
                }
        }
        return user;
    }

    public Usuario selectByLogin(String log) throws SQLException, ClassNotFoundException {
        Usuario user = null;
        try {
            Connection conn = conexao.novaConexao();
            String query = "SELECT * FROM usuario WHERE login='" + log + "'";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet set = stm.executeQuery();
            if (set.next()) {
                if (set.getString("tipo").equals("ins")) {
                    user = new Instrutor();
                    user.setNome(set.getString("nome"));
                    user.setCpf(set.getString("cpf"));
                    user.setLogin(set.getString("login"));
                    user.setSenha(set.getString("senha"));
                } else if (set.getString("tipo").equals("pro")) {
                    user = new Professor();
                    user.setNome(set.getString("nome"));
                    user.setCpf(set.getString("cpf"));
                    user.setLogin(set.getString("login"));
                    user.setSenha(set.getString("senha"));
                }else{
                    user = new Administrador();
                    user.setNome(set.getString("nome"));
                    user.setCpf(set.getString("cpf"));
                    user.setLogin(set.getString("login"));
                    user.setSenha(set.getString("senha"));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    public Instrutor getInstrutorByVeiculo(String placa){
        return new Instrutor();
    }
}
