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
import java.util.HashMap;

import sistemacfc.src.model.Aluno;
import sistemacfc.src.model.Aulas;
import sistemacfc.src.model.Curso;
import sistemacfc.src.model.Instrutor;
import sistemacfc.src.model.Teoricas;
import sistemacfc.src.model.Praticas;

/**
 *
 * @author Indiara
 * 
 */
public class AulaDAO {
    private Conexao conexao;
    
    public AulaDAO(){
        this.conexao=new Conexao();
    }
    
    public ArrayList<Aulas> getAulasByCurso(String tipo) throws ClassNotFoundException, SQLException{
        ArrayList<Aulas> aulas = new ArrayList<>();
    	Connection conn = conexao.novaConexao();
        String query = "SELECT * teoricas WHERE curso='" + tipo + "'";
        PreparedStatement stm = conn.prepareStatement(query);
        ResultSet set = stm.executeQuery();
        while(set.next()){
    		Aulas aula = new Teoricas();
    		aula.setCodigo(set.getInt("codigo"));
    		Curso curso = new Curso();
    		curso.setTipo(set.getString("curso"));
    		aula.setM_Curso(curso);
    		aula.setMateria(set.getString("materia"));
    		aulas.add(aula);
    	}
        return aulas;
    }
    
    public HashMap<Integer,Aulas> getPlanoDeEnsino(String turma){
        return new HashMap<Integer,Aulas>();
    }
    
    public ArrayList<Praticas> getPraticasByInstrutor(String instrutorLogin) throws SQLException, ClassNotFoundException{
    	ArrayList<Praticas> praticas = new ArrayList<>();
    	Connection conn = conexao.novaConexao();
    	String query = "SELECT * FROM praticas WHERE instrutor='" + instrutorLogin + "'";
    	PreparedStatement stm = conn.prepareStatement(query);
    	ResultSet set = stm.executeQuery();
    	while(set.next()){
    		Praticas pratica = new Praticas();
    		pratica.setData(set.getDate("data"));
    		pratica.setHorario(set.getInt("horario"));
    		Instrutor instrutor = new Instrutor();
    		instrutor.setLogin(set.getString("instrutor"));
    		pratica.setInstrutor(instrutor);
    		Aluno aluno = new Aluno();
    		aluno.setCPF(set.getString("aluno"));
    		pratica.setM_Aluno(aluno);
    		Curso curso = new Curso();
    		curso.setTipo(set.getString("curso"));
    		pratica.setM_Curso(curso);
    		pratica.setMateria(set.getString("materia"));
    		pratica.setReservado(set.getBoolean("reservado"));
    		praticas.add(pratica);
    	}
    	
    	return praticas;
    }
    public Aulas getAulaByCodigo(String codigoAula) throws ClassNotFoundException, SQLException{
    	Aulas aulas = null;
        Connection conn = conexao.novaConexao();
    	String query = "SELECT * FROM praticas WHERE codigo='" + codigoAula + "'";
    	PreparedStatement stm = conn.prepareStatement(query);
    	ResultSet set = stm.executeQuery();
    	if(set.next()){
    		Aulas aula = new Teoricas();
    		aula.setCodigo(set.getInt("codigo"));
    		Curso curso = new Curso();
    		curso.setTipo(set.getString("curso"));
    		aula.setM_Curso(curso);
    		aula.setMateria(set.getString("materia"));
    	}
        return aulas;
    }
    
    public ArrayList<Aulas> getAulasByAluno(String cpfAluno) throws ClassNotFoundException, SQLException{
        ArrayList<Aulas> aulas = new ArrayList<>();
        Connection conn = conexao.novaConexao();
    	String query = "SELECT * FROM praticas WHERE aluno='" + cpfAluno + "'";
    	PreparedStatement stm = conn.prepareStatement(query);
    	ResultSet set = stm.executeQuery();
    	while(set.next()){
    		Aulas aula = new Praticas();
    		aula.setCodigo(set.getInt("codigo"));
    		Curso curso = new Curso();
    		curso.setTipo(set.getString("curso"));
    		aula.setM_Curso(curso);
    		aula.setMateria(set.getString("materia"));
    		aulas.add(aula);
    	}
        return aulas;
    }

    public void setAulaToPlanoDeEnsinoDaTurma(int parseInt, Integer codigo, int indexData) throws ClassNotFoundException, SQLException {
    	Connection conn = conexao.novaConexao();
    	String query = "INSERT INTO plano VALUES (?, ?, ?)";
		 PreparedStatement stm = conn.prepareStatement(query);
		 stm.execute();
    }

	public void setAlunoToPratica(String pratica,String aluno) throws ClassNotFoundException, SQLException {
		 Connection conn = conexao.novaConexao();
		 String query = "UPDATE praticas SET aluno ='" + aluno + "' WHERE codigo='" + pratica + "'";
		 PreparedStatement stm = conn.prepareStatement(query);
		 stm.execute();
	}
	
	public void updateStatusReservado(int aula, int status) throws ClassNotFoundException, SQLException{
		 Connection conn = conexao.novaConexao();
		 String query = "UPDATE praticas SET reservado ='" + status + "' WHERE codigo='" + aula + "'";
		 PreparedStatement stm = conn.prepareStatement(query);
		 stm.execute();
	}
    
    
}