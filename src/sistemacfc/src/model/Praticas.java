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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getHorario() {
        return horario;
    }

    public void setHorario(int horario) {
        this.horario = horario;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

    public Aluno getM_Aluno() {
        return m_Aluno;
    }

    public void setM_Aluno(Aluno m_Aluno) {
        this.m_Aluno = m_Aluno;
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(Instrutor instrutor) {
        this.instrutor = instrutor;
    }

   

	
}//end Praticas