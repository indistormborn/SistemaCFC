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
import sistemacfc.src.dao.UsuarioDAO;
import sistemacfc.src.model.Administrador;
import sistemacfc.src.model.Usuario;
import sistemacfc.src.views.TelaInicial;
import sistemacfc.src.views.TelaLogin;

/**
 *
 * @author Indiara
 */
public class PrincipalControl {

    private UsuarioDAO usuarioDAO;
    private TelaLogin telaLogin;
    private Usuario usuarioSessao;
    private TelaInicial telaInicial;
    private CadastroControl cadastro;
    private AgendaControl agenda;
    private AulasControl aulas;

    public PrincipalControl() {

        this.usuarioDAO = new UsuarioDAO();
        this.telaLogin = new TelaLogin(this);
        this.usuarioSessao = new Administrador();
        this.telaInicial = new TelaInicial(this);
        this.cadastro = new CadastroControl();
        this.agenda = new AgendaControl();
        this.aulas = new AulasControl();
    }

    public void efetuarLogin(String login, String senha) throws ClassNotFoundException, SQLException {
        Usuario user = usuarioDAO.verificaLoginAndSenha(login, senha);
        if (user != null) {
            this.usuarioSessao = user;
            telaLogin.dispose();
            telaInicial.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(telaLogin, "Login ou senha estão incorretos", "ERRO DE LOGIN", JOptionPane.ERROR_MESSAGE);
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

}
