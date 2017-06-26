/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacfc.src.control;

import java.awt.Component;
import java.awt.Container;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import sistemacfc.src.dao.AlunoDAO;
import sistemacfc.src.dao.TurmaDAO;
import sistemacfc.src.dao.UsuarioDAO;
import sistemacfc.src.model.Aluno;
import sistemacfc.src.model.Instrutor;
import sistemacfc.src.model.Professor;
import sistemacfc.src.model.Turma;
import sistemacfc.src.model.Usuario;
import sistemacfc.src.views.TelaCadastramento;
import sistemacfc.src.views.TelaInicial;
import sistemacfc.src.views.TelaLogin;

/**
 *
 * @author Indiara
 */
public class CadastroControl {

    
    private AlunoDAO alunoDAO;
    private TurmaDAO turmaDAO;
    private TelaCadastramento telaCadastro;
    private PrincipalControl principal;
    

    public CadastroControl(PrincipalControl prin) {
        
        this.alunoDAO = new AlunoDAO();
        this.turmaDAO=new TurmaDAO();
        this.telaCadastro = new TelaCadastramento(this);
        this.principal = prin;
        
    }
    
    

    /*UC02.07 - MATRICULAR ALUNO*/
       
    public DefaultComboBoxModel exibirTurmas(String periodo, String curso) throws ClassNotFoundException, SQLException{
        Collection<Turma> turmas = turmaDAO.getTurmasByCursoAndPeriodo(curso, periodo);
        DefaultComboBoxModel<String> codigos = new DefaultComboBoxModel<>();
                codigos.addElement("<selecione>");

        for(Turma turma : turmas){
            codigos.addElement(turma.getCodigo().toString());
        }
        if(codigos.getSize()==1){
           mensagemErro(telaCadastro,"Não existe nenhuma turma neste periodo pra este curso e/ou neste curso"); 
        }
        return codigos;
    }
    
    public DefaultListModel exibirAlunos(String codigo) throws SQLException, ClassNotFoundException{
        Integer turma = Integer.parseInt(codigo);
        Collection<Aluno> alunos = alunoDAO.getAlunosByTurma(turma);
        DefaultListModel<String> nomes = new DefaultListModel<>();
        for(Aluno aluno : alunos){
            nomes.addElement(aluno.getNome());
        }
        return nomes;
    }
    
    public void efetuarMatricula(String cpf, String codigo) throws ClassNotFoundException, SQLException{
        Aluno aluno = alunoDAO.getAlunoByCPF(cpf);
        Turma turma = turmaDAO.getTurmaByCodigo(Integer.parseInt(codigo));
        ArrayList<Aluno> alunos = alunoDAO.getAlunosByTurma(Integer.parseInt(codigo));
        turma.setAlunos(alunos);
        boolean existemVagas = turma.verificarVagasNaturma();
        if(existemVagas){
           turma.setAlunoToAlunos(aluno);
           aluno.setTurma(turma);
           Integer tcodigo = turma.getCodigo();
           alunoDAO.setTurmaToAluno(cpf,tcodigo);
           mensagemConfirmacao(telaCadastro,"Aluno matriculado com sucesso!");
        }else{
            mensagemErro(telaCadastro, "Impossivel matricular aluno! Turma atingiu o numero maximo de participantes");
        }
        
    }
    
    
    public void mensagemConfirmacao(Component tela, String msg){
       JOptionPane.showMessageDialog(tela, msg, "CONFIRMACAO", JOptionPane.INFORMATION_MESSAGE);
    }
    public void mensagemErro(Component tela, String msg){
        JOptionPane.showMessageDialog(tela,msg,"ERRO", JOptionPane.ERROR);
    }
    
    public PrincipalControl getControlePrincipal(){
        return principal;
    }

    
    
    


}
