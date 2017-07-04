package sistemacfc.src.model;
import java.util.Collection;
import java.util.Date;



/**
 * @author Indiara
 * @version 1.0
 * @created 22-mai-2017 21:01:28
 */
public class Historico {

    
        /*frequencia de boo pra int
         aulaT e aulaP add pq n da pra fazer downcastin*/
    
	protected Date data;
	protected int frequencia; //esta variavel serve tbm para indicar o desempenho da prova true = aprovado and false=reprovado
	protected Prova prova;
	protected Aulas aula;

	public Historico(){

	}

        public Aulas getAula(){
            return null;
        }
        
	public void finalize() throws Throwable {

	}

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    public Prova getProva() {
        return prova;
    }

    public void setProva(Prova prova) {
        this.prova = prova;
    }

    public void setAula(Aulas aula) {
        this.aula = aula;
    }

   

    
        
}//end Hist�rico