package sistemacfc.src.model;
import java.util.ArrayList;
import java.util.Collection;



/**
 * @author Indiara
 * @version 1.0
 * @created 22-mai-2017 21:01:22
 */
public class Aluno {

	protected String CPF;
	protected String email;
	protected String endereco;
	protected String nome;
	protected String RG;
	protected String telefone;
	protected Collection<Historico> historico;
	protected Turma turma;
        protected Collection<Praticas> aulasPraticas;
        
 /*       
        */
        
	public Aluno(){
            this.historico=new ArrayList<>();
            this.aulasPraticas=new ArrayList<>();

	}

    public Aluno(String CPF, String email, String endereco, String nome, String RG, String telefone) {
        this.CPF = CPF;
        this.email = email;
        this.endereco = endereco;
        this.nome = nome;
        this.RG = RG;
        this.telefone = telefone;
    }

    /*25 hroas aula sendo cada aula pratica considerada 1h temos 25 aulas logo limite max eh 25*/
    public boolean verificarPraticasAgendadas(){
    	//for(Aluno aluno : alunos)
        return true;
    }
    
    public ArrayList<Aulas> getTeoricasFaltantes(){
        return new ArrayList<>();
    }
    
    public ArrayList<Aulas> getPraticasFaltantes(){
        return new ArrayList<>();
    }
    
    public boolean verificarFrequenciaTeorica(){
        return true;
    }
    
    public boolean verificarFrequenciaPratica(){
        return true;
    }
    
    public boolean verificarDesempenhoProvaTeorica(){
        return true;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Collection<Historico> getHistorico() {
        return historico;
    }

    public void setHistorico(Collection<Historico> historico) {
        this.historico = historico;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Collection<Praticas> getAulasPraticas() {
        return aulasPraticas;
    }

    public void setAulasPraticas(Collection<Praticas> aulasPraticas) {
        this.aulasPraticas = aulasPraticas;
    }
        
        
    
	
}//end Aluno