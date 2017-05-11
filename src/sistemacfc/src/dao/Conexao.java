/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacfc.src.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Indiara
 */
public class Conexao {
   private String driver = "org.postgresql.Driver";
   private String user = "postgres";   
   private String senha = "stef1986";
   private String url = "jdbc:postgresql://localhost:5432/aps";
   
      
   public Connection novaConexao() throws ClassNotFoundException, SQLException{
    Class.forName(driver);
    Connection con = null;
    con = (Connection) DriverManager.getConnection(url, user, senha);
    System.out.println("Conexão realizada com sucesso.");
    return con;
   }
}
