package sistemacfc.src.model;


import java.util.Collection;




/**
 * @author Indiara
 * @version 1.0
 * @created 07-mai-2017 23:23:59
 */
public class Professor extends Usuario {

	public Collection<Turma> turma;

    public Professor(String cpf, String login, String nome, String senha) {
        super(cpf, login, nome, senha);
    }

	public Professor(){
            
        }
	
}//end Professor