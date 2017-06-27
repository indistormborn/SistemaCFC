package sistemacfc.src.model;

/**
 * @author Indiara
 * @version 1.0
 * @created 22-mai-2017 21:01:41
 */
public abstract class Aulas {

	protected Integer codigo;
	protected String materia;
	public Curso m_Curso;

	public Aulas(){

	}
        
	public void finalize() throws Throwable {

	}

    public Integer getCodigo() {
        return codigo;
    }
}//end Aulas