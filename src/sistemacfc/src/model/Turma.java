package sistemacfc.src.model;

import java.util.Collection;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Indiara
 */
public class Turma {
	protected Integer codigo;
	protected Date dataInicio;
	protected Date dataTermino;
	protected boolean ehAtiva;
	protected int marcadorCronograma;
	protected String periodo;
	protected Collection<Teoricas> planoDeEnsino;
	protected Curso curso;
        protected Collection<Aluno> alunos;
        protected Professor professor;

        
        public Turma(){
            }
        
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public boolean isEhAtiva() {
        return ehAtiva;
    }

    public void setEhAtiva(boolean ehAtiva) {
        this.ehAtiva = ehAtiva;
    }

    public int getMarcadorCronograma() {
        return marcadorCronograma;
    }

    public void setMarcadorCronograma(int marcadorCronograma) {
        this.marcadorCronograma = marcadorCronograma;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Collection<Teoricas> getPlanoDeEnsino() {
        return planoDeEnsino;
    }

    public void setPlanoDeEnsino(Collection<Teoricas> planoDeEnsino) {
        this.planoDeEnsino = planoDeEnsino;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Collection<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Collection<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

        
    
}
