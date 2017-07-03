
package sistemacfc.src.model;


/**
 * @author Indiara
 * @version 1.0
 * @created 21-jun-2017 01:52:47
 */
public class Veiculo {

	protected int ano;
	protected boolean disponibilidade;
	protected String placa;
	protected String tipo;

	public Veiculo(){

	}

	public void finalize() throws Throwable {

	}

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
        
        
}