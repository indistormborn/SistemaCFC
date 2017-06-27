/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacfc.src.dao;

import java.util.ArrayList;
import java.util.HashMap;
import sistemacfc.src.model.Aulas;
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
    
    public Aulas getAulasByCurso(String tipo){
        return new Teoricas();
    }
    
        public HashMap<Integer,Aulas> getPlanoDeEnsino(String turma){
        return new HashMap<Integer,Aulas>();
    }
    
    public Aulas getPraticasByInstrutor(String instrutorLogin){
        return new Praticas();
    }

    public Aulas getAulaByCodigo(String codigoAula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList<Aulas> getAulasByAluno(String cpfAluno){
        return null;
    }

    public void setAulaToPlanoDeEnsinoDaTurma(int parseInt, Integer codigo, int indexData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	public void setAlunoToPratica(String aluno) {
		// TODO Auto-generated method stub
		
	}
	
	public void updateStatusReservado(int aula, int status){
		
	}
    
    
}
