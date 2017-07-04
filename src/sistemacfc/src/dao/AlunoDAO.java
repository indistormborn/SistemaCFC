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
import sistemacfc.src.model.Historico;
import sistemacfc.src.model.Prova;

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
        return alunos;
    }
    
    public void setTurmaToAluno(String cpf, Integer turma){
        
    }
    
    public ArrayList<Historico> getHistoricoAulasPraticas(String aluno){
        return new ArrayList<>();
    }
    
    public ArrayList<Historico> getHistoricoAulasTeoricas(String aluno){
        return new ArrayList<>();
    }
    
    public ArrayList<Historico> getHistoricoProvas(String aluno){
        return new ArrayList<>();
    }
    
    public Prova getProvaAprovadaByTipo(String aluno, String tipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public void setAulaToAluno(int codigoAula, String cpf ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
}
