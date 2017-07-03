package sistemacfc.src.model;

import java.util.Date;

/**
 * @author Indiara
 * @version 1.0
 * @created 22-mai-2017 21:01:32
 */
public class Prova {

	protected int codigo;
	protected Date data;
        protected String hora;
	protected String tipo;

	public Prova(){

	}

	public void finalize() throws Throwable {

	}

    public Date getData() {
        return data;
    }

    public String getHorario() {
        return hora;
    }

    public int getCodigo() {
        return codigo;
    }
    

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}