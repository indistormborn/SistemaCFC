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
    

    public CadastroControl() {
        
        this.alunoDAO = new AlunoDAO();
        this.turmaDAO=new TurmaDAO();
        this.telaCadastro = new TelaCadastramento(this);
        
    }
    
    

    /*UC02.07 - MATRICULAR ALUNO*/
    public String exibirNomeAluno(String cpf) throws ClassNotFoundException, SQLException{
        return alunoDAO.getAlunoByCPF(cpf).getNome();
    }
    
    public DefaultComboBoxModel exibirTurmas(String periodo, String curso) throws ClassNotFoundException, SQLException{
        Collection<Turma> turmas = turmaDAO.getTurmasByCursoAndPeriodo(curso, periodo);
        DefaultComboBoxModel<String> codigos = new DefaultComboBoxModel<>();
                codigos.addElement("<selecione>");

        for(Turma turma : turmas){
            codigos.addElement(turma.getCodigo().toString());
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
    
    
    
    public void cadastrarUsuario(String nome, String cpf, String login, String senha, String tipo) throws SQLException, ClassNotFoundException {
        Usuario user;
        String confirmacao = "Cadastro realizado com sucesso!";
        if (isCamposPreenchidos(telaCadastro.getCadastroUsuario())) {
            if (isSenhasIguais()) {
                if (isLoginDisponivel()) {
                    if (tipo.equals("Instrutor")) {
                        user = new Instrutor(nome, cpf, login, senha);
                        usuarioDAO.insert(user, "ins");
                        JOptionPane.showMessageDialog(telaCadastro, confirmacao);
                    } else if (tipo.equals("Professor")) {
                        user = new Professor(nome, cpf, login, senha);
                        usuarioDAO.insert(user, "pro");
                        JOptionPane.showMessageDialog(telaCadastro,confirmacao);
                    }else if(tipo.equals("Administrador")) {
                        user = new Professor(nome, cpf, login, senha);
                        usuarioDAO.insert(user, "adm");
                        JOptionPane.showMessageDialog(telaCadastro,confirmacao);
                    }
                } else {
                    JOptionPane.showMessageDialog(telaCadastro, "Login já cadastrado no sistema");
                }
            } else {
                JOptionPane.showMessageDialog(telaCadastro, "Confirmação de senha inválida!");

            }
        } else {
            JOptionPane.showMessageDialog(telaCadastro, "Todos os campos devem ser preenchidos!");
        }
    }

   

//    CADASTRO DE USUÁRIO
    private boolean isSenhasIguais() {
        if (!telaCadastro.getSenhaUsuario().getText().equals(telaCadastro.getConfirmarSenha().getText())) {
            return false;
        }
        return true;
    }
//    private boolean isLoginDisponivel() throws SQLException, ClassNotFoundException {
//        String loginVerificar = telaCadastro.getLoginUsuario().getText();
//        Usuario user = usuarioDAO.selectByLogin(loginVerificar);
//        if (user==null) {
//            return true;
//        } else if (user.getLogin().equals(loginVerificar)) {
//            return false;
//        }
//        return true;
//    }

    public void inicia() {
        telaCadastro.setVisible(true);
    }
    
    


}
