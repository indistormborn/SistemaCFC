package sistemacfc.src.model;


/**
 * @author Indiara
 * @version 1.0
 * @created 07-mai-2017 23:23:51
 */
public abstract class Usuario {

	protected String cpf;
	protected String login;
	protected String nome;
	protected String senha;

	public Usuario(){

	}

    public Usuario(String nome, String cpf, String login, String senha) {
        this.cpf = cpf;
        this.login = login;
        this.nome = nome;
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
        
}