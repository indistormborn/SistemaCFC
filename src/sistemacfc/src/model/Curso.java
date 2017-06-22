package sistemacfc.src.model;

import java.util.ArrayList;


/**
 * @author Indiara
 * @version 1.0
 * @created 22-mai-2017 21:01:52
 */
public class Curso {

	protected ArrayList<String> conteudo;
	protected String tipo;
       

	public Curso(){

	}
        public Curso(String tipo){
            this.tipo = tipo;
        }

    public ArrayList<String> getConteudo() {
        return conteudo;
    }

    public void setConteudo(ArrayList<String> conteudo) {
        this.conteudo = conteudo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

	
}//end Curso