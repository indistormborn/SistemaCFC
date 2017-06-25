package sistemacfc.src.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

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
    protected HashMap<Integer, Teoricas> planoDeEnsino;
    protected Curso curso;
    protected ArrayList<Aluno> alunos;
    protected Professor professor;

    public Turma() {
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

    public HashMap<Integer, Teoricas> getPlanoDeEnsino() {
        return planoDeEnsino;
    }

    public void setPlanoDeEnsino(HashMap<Integer, Teoricas> planoDeEnsino) {
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

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public boolean verificarVagasNaturma() {
        if (alunos.size() >= 30) {
            return false;
        } else {
            return true;
        }
    }

    public void setAlunoToAlunos(Aluno aluno) {
        alunos.add(aluno);
    }

    public int getQuantidadeDeAulas() {
//        RN02 - A duração do curso para retirada de uma habilitação do 
//        tipo A ou B deve ser de 45 hora/aula de conteúdo teórico e 25 hora/aula de prática em veículos
//        RN04 - [...] Cada aula teórica em um periodo equivale à 5 horas/aula
        int nroAulas = 0;
        if (curso.getTipo().equals("A") || curso.getTipo().equals("B")) {
            nroAulas = 45 / 5;
        } else {
            nroAulas = 0;
        }

        return nroAulas;
    }

    public String[] setDatasAulasParaTela() {
        Calendar c = Calendar.getInstance();
        c.setTime(dataInicio);
        String[] datas = new String[getQuantidadeDeAulas()];
         SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
         datas[0]=format.format(c.getTime());;
        for(int i=1; i<getQuantidadeDeAulas(); i++){
            c.add(Calendar.DATE, +1);
            datas[i]=format.format(c.getTime());;
        }
        return datas;
    }

    public void setAulaToPlanoDeEnsino(Integer aulaCod, Aulas aula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
