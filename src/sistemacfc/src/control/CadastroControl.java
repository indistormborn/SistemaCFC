/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacfc.src.control;

import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import sistemacfc.src.dao.UsuarioDAO;
import sistemacfc.src.model.Instrutor;
import sistemacfc.src.model.Professor;
import sistemacfc.src.model.Usuario;
import sistemacfc.src.views.TelaCadastramento;

/**
 *
 * @author Indiara
 */
public class CadastroControl {

    private UsuarioDAO usuarioDAO;
    private TelaCadastramento telaCadastro;

    public CadastroControl() {
        this.usuarioDAO = new UsuarioDAO();
        this.telaCadastro = new TelaCadastramento(this);

    }

    public void cadastrarUsuario(String nome, String cpf, String login, String senha, String tipo) throws SQLException, ClassNotFoundException {
        Usuario user;
        String confirmacao = "Cadastro realizado com sucesso!";
        if (isCamposPreenchidos()) {
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

    public boolean isCamposPreenchidos() {
        String campo1 = telaCadastro.getNomeUsuario().getText();
        String campo2 = telaCadastro.getCpfUsuario().getText();
        String campo3 = telaCadastro.getLoginUsuario().getText();
        String campo4 = telaCadastro.getSenhaUsuario().getText();
        String campo5 = telaCadastro.getConfirmarSenha().getText();
        String campo6 = telaCadastro.getTipoUsuario().getSelectedItem().toString();

        ArrayList<String> campos = new ArrayList<>();
        campos.add(campo1);
        campos.add(campo2);
        campos.add(campo3);
        campos.add(campo4);
        campos.add(campo5);
        campos.add(campo6);

        for (String campo : campos) {
            if (campo.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean isSenhasIguais() {
        if (!telaCadastro.getSenhaUsuario().getText().equals(telaCadastro.getConfirmarSenha().getText())) {
            return false;
        }
        return true;
    }

    public boolean isLoginDisponivel() throws SQLException, ClassNotFoundException {
        String loginVerificar = telaCadastro.getLoginUsuario().getText();
        Usuario user = usuarioDAO.selectByLogin(loginVerificar);
        if (user==null) {
            return true;
        } else if (user.getLogin().equals(loginVerificar)) {
            return false;
        }
        return true;
    }

    public void inicia() {
        telaCadastro.setVisible(true);
    }

//    public List getTodosTextField(JPanel panel) {
//        List<JTextField> list = new ArrayList<JTextField>();
//        Component[] components = panel.getComponents();
//
//        for (Component component : components) {
//            if (component.getClass().equals(JTextField.class)) {
//                list.add((JTextField) component);
//            }
//        }
//        return null;
//    }
}
