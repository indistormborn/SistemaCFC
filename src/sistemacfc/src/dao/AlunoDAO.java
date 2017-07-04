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
import sistemacfc.src.model.Aluno;
import sistemacfc.src.model.Aulas;
import sistemacfc.src.model.Historico;
import sistemacfc.src.model.Praticas;
import sistemacfc.src.model.Prova;
import sistemacfc.src.model.Teoricas;

/**
 *
 * @author Indiara
 * CONTROLA AS TABELAS ALUNOS, HISTORICOAULAS, HISTORICOPROVAS
 */
public class AlunoDAO {
    
    private Conexao conexao;
    
    public AlunoDAO(){
        this.conexao=new Conexao();
    }
    
    public Aluno getAlunoByCPF(String cpf) throws ClassNotFoundException, SQLException{
        Aluno aluno = null;
        Connection conn = conexao.novaConexao();
        String query = "SELECT * FROM alunos WHERE cpf='"+cpf+"'";
        PreparedStatement stm = conn.prepareStatement(query);
        ResultSet set = stm.executeQuery();
        if(set.next()){
            aluno = new Aluno();
            aluno.setCPF(set.getString("cpf"));
            aluno.setNome(set.getString("nome"));
        }
        conn.close();
        return aluno;
    }
    
    public ArrayList<Aluno> getAlunosByTurma(Integer codigo) throws SQLException, ClassNotFoundException{
        ArrayList<Aluno> alunos = new ArrayList<>();
        Connection conn = conexao.novaConexao();
        String query = "SELECT * FROM alunos WHERE turma='"+codigo+"'";
        PreparedStatement stm = conn.prepareStatement(query);
        ResultSet set = stm.executeQuery();
        while(set.next()){
            Aluno aluno = new Aluno();
            aluno.setNome(set.getString("nome"));
            alunos.add(aluno);
        }
        conn.close();
        return alunos;
    }
    
    public void setTurmaToAluno(String cpf, Integer turma) throws ClassNotFoundException, SQLException{
        Connection conn = conexao.novaConexao();
        String sql = "UPDATE alunos SET turma="+turma+" WHERE aluno='"+cpf+"'";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.execute();
        conn.close();
        
    }
    
    public ArrayList<Historico> getHistoricoAulasPraticas(String aluno) throws SQLException, ClassNotFoundException{
        ArrayList<Historico> historico = new ArrayList<>();
        Connection conn = conexao.novaConexao();
        String query = "SELECT * FROM historicoaulas WHERE tipo='pratica' AND aluno='"+aluno+"'";
        PreparedStatement stm = conn.prepareStatement(query);
        ResultSet set = stm.executeQuery();
        while(set.next()){
            Historico h = new Historico();
            Aulas aula = new Praticas();
            aula.setCodigo(set.getInt("aula"));
            h.setAula(aula);
            h.setData(set.getDate("data"));
            h.setFrequencia(set.getInt("frequencia"));
            historico.add(h);
        }
        conn.close();
        return historico;
        
    }
    
    public ArrayList<Historico> getHistoricoAulasTeoricas(String aluno) throws ClassNotFoundException, SQLException{
        ArrayList<Historico> historico = new ArrayList<>();
        Connection conn = conexao.novaConexao();
        String query = "SELECT * FROM historicoaula WHERE aluno='"+aluno+"' AND tipoAula='teorica'";
        PreparedStatement stm = conn.prepareStatement(query);
        ResultSet set = stm.executeQuery();
        while(set.next()){
            Historico h = new Historico();
            Aulas aula = new Teoricas();
            aula.setCodigo(set.getInt("aula"));
            h.setAula(aula);
            h.setData(set.getDate("data"));
            h.setFrequencia(set.getInt("frequencia"));
            historico.add(h);
        }
        conn.close();
        return historico;
    }
    
    public ArrayList<Historico> getHistoricoProvas(String aluno) throws ClassNotFoundException, SQLException{
        ArrayList<Historico> historico = new ArrayList<>();
        Connection conn = conexao.novaConexao();
        String query = "SELECT * FROM historicoprova WHERE aluno='"+aluno+"'";
        PreparedStatement stm = conn.prepareStatement(query);
        ResultSet set = stm.executeQuery();
        while(set.next()){
            Historico h = new Historico();
            Prova prova = new Prova();
            prova.setCodigo(set.getInt("prova"));
            h.setProva(prova);
            h.setData(set.getDate("data"));
            h.setFrequencia(set.getInt("frequencia"));
            historico.add(h);
        }
        conn.close();
        return historico;
    }
    
    public Prova getProvaAprovadaByTipo(String aluno, String tipo) throws ClassNotFoundException, SQLException {
        Prova prova = null;
        Connection conn = conexao.novaConexao();
        String query = "SELECT * FROM historicoprovas WHERE aluno='"+aluno+"'AND tipo='"+tipo+"'";
        PreparedStatement stm = conn.prepareStatement(query);
        ResultSet set = stm.executeQuery();
        if(set.next()){
            prova = new Prova ();
            prova.setCodigo(set.getInt("prova"));
        }
        conn.close();
        return prova;
    }
    
    public void setProvaToAluno(String prova, String aluno) throws ClassNotFoundException, SQLException{
        Connection conn = conexao.novaConexao();
        String sql = "INSERT INTO historicoprovas (prova, aluno) VALUES (?, ?)";
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, Integer.parseInt(prova));
        stm.setString(2, aluno);
        stm.execute();
        conn.close();
    } 
   
    
}
