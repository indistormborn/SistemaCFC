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
import java.util.Collection;
import sistemacfc.src.model.Curso;
import sistemacfc.src.model.Turma;

/**
 *
 * @author Indiara
 */
public class TurmaDAO {
    
    private Conexao conexao;
    
    public TurmaDAO(){
        this.conexao = new Conexao();
    }
    
    public Collection getTurmasByCursoAndPeriodo(String curso, String periodo) throws ClassNotFoundException, SQLException{
        Collection<Turma> turmas = new ArrayList<>();
        Connection conn = conexao.novaConexao();
        String query = "SELECT * FROM turmas WHERE curso='"+curso+"'AND periodo='"+periodo+"'";
        PreparedStatement stm = conn.prepareStatement(query);
        ResultSet set = stm.executeQuery();
        while(set.next()){
            Turma turma = new Turma();
            turma.setCodigo(set.getInt("codigo"));
            turmas.add(turma);
            
        }
        return turmas;
    }
    
    
    
    public Collection getTurmasByProfessor (String login) throws ClassNotFoundException, SQLException{
        Collection<Turma> turmas = new ArrayList<>();
        Connection conn = conexao.novaConexao();
        String query = "SELECT * FROM turmas WHERE professor='"+login+"'";
        PreparedStatement stm = conn.prepareStatement(query);
        ResultSet set = stm.executeQuery();
        while(set.next()){
            Turma turma = new Turma();
            turma.setCodigo(set.getInt("codigo"));
            turma.setPeriodo(set.getString("periodo"));
            turma.setDataInicio(set.getDate("datainicio"));
            turma.setDataTermino(set.getDate("datatermino"));
            turmas.add(turma);
            
        }
        return turmas;
    }
    
    public Turma getTurmaByCodigo(Integer codigo) throws ClassNotFoundException, SQLException{
        Turma turma = new Turma();
        Connection conn = conexao.novaConexao();
        String query = "SELECT * FROM turmas WHERE codigo='"+codigo+"'";
        PreparedStatement stm = conn.prepareStatement(query);
        ResultSet set = stm.executeQuery();
        while(set.next()){
            turma.setCodigo(set.getInt("codigo"));
            turma.setPeriodo(set.getString("periodo"));
            turma.setDataInicio(set.getDate("datainicio"));
            turma.setDataTermino(set.getDate("datatermino"));
            turma.setCurso(new Curso(set.getString("curso")));
        }
        return turma;
    }
    
    
}
