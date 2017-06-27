package sistemacfc.src.model;

import java.util.Date;

/**
 * @author Indiara
 * @version 1.0
 * @created 22-mai-2017 21:01:49
 */
public class Praticas extends Aulas {

	protected Date data;
	protected int horario;
	protected boolean reservado;
	public Aluno m_Aluno;
        public Instrutor instrutor;

        
        
	public Praticas(){

	}

    public Instrutor getInstrutor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	
}//end Praticas