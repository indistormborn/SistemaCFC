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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import sistemacfc.src.dao.AlunoDAO;
import sistemacfc.src.dao.UsuarioDAO;
import sistemacfc.src.model.Administrador;
import sistemacfc.src.model.Instrutor;
import sistemacfc.src.model.Professor;
import sistemacfc.src.model.Usuario;
import sistemacfc.src.views.TelaInicial;
import sistemacfc.src.views.TelaLogin;

/**
 *
 * @author Indiara
 */
public class PrincipalControl {

    private UsuarioDAO usuarioDAO;
    private AlunoDAO alunoDAO;
    private TelaLogin telaLogin;
    private Usuario usuarioSessao;
    private String role;
    private TelaInicial telaInicial;
    private CadastroControl cadastro;
    private AgendaControl agenda;
    private AulasControl aulas;

    public PrincipalControl() {

        this.usuarioDAO = new UsuarioDAO();
        this.alunoDAO = new AlunoDAO();
        this.telaLogin = new TelaLogin(this);
        this.usuarioSessao = new Administrador();
        this.telaInicial = new TelaInicial(this);
        this.cadastro = new CadastroControl(this);
        this.agenda = new AgendaControl(this);
        this.aulas = new AulasControl(this);
    }

    public void inicia(){
        telaLogin.setVisible(true);
    }
    
    public void efetuarLogin(String login, String senha) throws ClassNotFoundException, SQLException {
        Usuario user = usuarioDAO.verificaLoginAndSenha(login, senha);
        if (user != null) {
            this.usuarioSessao = user;
            setRole();
            telaLogin.dispose();
            telaInicial.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(telaLogin, "Login ou senha estão incorretos", "ERRO DE LOGIN", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void efetuarLogout(){
        if (usuarioSessao != null) {
            this.usuarioSessao = null;
            telaInicial.dispose();
            telaLogin.setVisible(true);
        }
    }

    public void setRole(){
        if(usuarioSessao instanceof Administrador){
            this.role="adm";
        }else if(usuarioSessao instanceof Professor){
            this.role="pro";
        }else if(usuarioSessao instanceof Instrutor){
            this.role="ins";
        }
    }
    
    private boolean isCamposPreenchidos(Container container) {
        ArrayList<String> campos = new ArrayList<>();

        for (Component c : container.getComponents()) {
            if (c instanceof JTextField) {
                String text = ((JTextField) c).getText();
                campos.add(text);
            } else if (c instanceof JComboBox) {
                String text = ((JComboBox) c).getSelectedItem().toString();
                campos.add(text);
            }
        }

        for (String campo : campos) {
            if (campo.isEmpty()) {
                return false;
            }
        }

        return true;
    }
    
    public String exibirNomeAluno(String cpf) throws ClassNotFoundException, SQLException{
        return alunoDAO.getAlunoByCPF(cpf).getNome();
    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }
    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
    public AlunoDAO getAlunoDAO() {
        return alunoDAO;
    }
    public void setAlunoDAO(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
    }
    public TelaLogin getTelaLogin() {
        return telaLogin;
    }
    public void setTelaLogin(TelaLogin telaLogin) {
        this.telaLogin = telaLogin;
    }
    public Usuario getUsuarioSessao() {
        return usuarioSessao;
    }
    public void setUsuarioSessao(Usuario usuarioSessao) {
        this.usuarioSessao = usuarioSessao;
    }
    public TelaInicial getTelaInicial() {
        return telaInicial;
    }
    public void setTelaInicial(TelaInicial telaInicial) {
        this.telaInicial = telaInicial;
    }
    public CadastroControl getCadastro() {
        return cadastro;
    }
    public void setCadastro(CadastroControl cadastro) {
        this.cadastro = cadastro;
    }
    public AgendaControl getAgenda() {
        return agenda;
    }
    public void setAgenda(AgendaControl agenda) {
        this.agenda = agenda;
    }
    public AulasControl getAulas() {
        return aulas;
    }
    public void setAulas(AulasControl aulas) {
        this.aulas = aulas;
    }
   
    
    
}
