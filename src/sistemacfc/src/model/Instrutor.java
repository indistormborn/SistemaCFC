package sistemacfc.src.model;


import sistemacfc.src.model.Veiculo;



/**
 * @author Indiara
 * @version 1.0
 * @created 07-mai-2017 23:24:02
 */
public class Instrutor extends Usuario {

	public Veiculo veiculo;
	//public Collection<Praticas> m_Praticas;

	public Instrutor(){

	}

    public Instrutor(String cpf, String login, String nome, String senha) {
        super(cpf, login, nome, senha);
    }

        
	
}