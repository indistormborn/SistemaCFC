package sistemacfc.src.model;
import java.util.Collection;
import java.util.Date;



/**
 * @author Indiara
 * @version 1.0
 * @created 22-mai-2017 21:01:28
 */
public class Historico {

	protected Date data;
	protected boolean frequencia; //esta variavel serve tbm para indicar o desempenho da prova true = aprovado and false=reprovado
	protected Prova prova;
	protected Aulas aulas;

	public Historico(){

	}

	public void finalize() throws Throwable {

	}
}//end Hist�rico