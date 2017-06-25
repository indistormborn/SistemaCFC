/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacfc.src.control;

import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import sistemacfc.src.dao.AulaDAO;
import sistemacfc.src.dao.TurmaDAO;
import sistemacfc.src.dao.UsuarioDAO;
import sistemacfc.src.model.Aulas;
import sistemacfc.src.model.Professor;
import sistemacfc.src.model.Teoricas;
import sistemacfc.src.model.Turma;
import sistemacfc.src.model.Usuario;
import sistemacfc.src.views.TelaAulas;

/**
 *
 * @author Indiara
 */
public class AulasControl {

    private PrincipalControl principal;
    private TurmaDAO turmaDAO;
    private UsuarioDAO usuarioDAO;
    private AulaDAO aulasDAO;
    private TelaAulas telaAula;

    public AulasControl(PrincipalControl ctrl) {
        this.turmaDAO = new TurmaDAO();
        this.usuarioDAO = new UsuarioDAO();
        this.aulasDAO = new AulaDAO();
        this.telaAula = new TelaAulas(this);
        this.principal = ctrl;
    }

    public AulasControl() {

    }

    /*INTERAÇÕES DE TELA aba 'Suas Turmas'*/
    public DefaultComboBoxModel exibirTurmasProfessor(String professor) throws ClassNotFoundException, SQLException {
        Usuario usuario = usuarioDAO.selectByLogin(professor);
        DefaultComboBoxModel<String> codigos = new DefaultComboBoxModel<>();
        if (usuario instanceof Professor) {
            Collection<Turma> turmas = turmaDAO.getTurmasByProfessor(professor);
            codigos.addElement("<selecione>");
            for (Turma turma : turmas) {
                codigos.addElement(turma.getCodigo().toString());
            }
        }
        return codigos;
    }

    public String[] exibirInfosTurma(String codigo) throws ClassNotFoundException, SQLException {
        Integer cod = Integer.parseInt(codigo);
        Turma turma = turmaDAO.getTurmaByCodigo(cod);

        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dataInicio = formatter.format(turma.getDataInicio());
        String dataTermino = formatter.format(turma.getDataTermino());

        String[] infos = new String[4];
        infos[0] = turma.getPeriodo();
        infos[1] = dataInicio;
        infos[2] = dataTermino;
        infos[3] = turma.getCurso().getTipo();

        return infos;

    }

    public String exibirDataSelecionada() {
        int row = telaAula.getTabelaAulas().getSelectedRow();
        return telaAula.getTabelaAulas().getValueAt(row, 0).toString();
    }

    public DefaultTableModel exibirDatasAulas(String codigo) throws ClassNotFoundException, SQLException {
        Integer cod = Integer.parseInt(codigo);
        Turma turma = turmaDAO.getTurmaByCodigo(cod);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Data da aula");
        model.addColumn("Aula");

        for (int i = 0; i < 8; i++) {
            model.addRow(new Object[]{turma.setDatasAulasParaTela()[i], " "});
        }
        return model;
    }

    //FIM DAS INTERAÇÕES DE TELA
    /*CASO DE USO - DEFINIR PLANO DE ENSINO*/
 /*as datas sao exibidas em tela para melhor entendimento do professor
        mas cada data representa um numero de 0-7 representadno os 8 dias de aula do aluno
        (45 horas aula sendo 5 por dia, como definido nas RNs)
        o indexData representa a ordem que a aula sera adicionada no cronograma, 
        ou seja, na data q a aula sera ministrada*/
    
    public void adicionarAulaPlanoDeEnsino(int indexData, String codigoAula, String codigoTurma) throws ClassNotFoundException, SQLException {
        Integer aulaCod = Integer.parseInt(codigoAula);
        Aulas aula = aulasDAO.getAulaByCodigo(codigoAula);
        Turma turma = turmaDAO.getTurmaByCodigo(Integer.parseInt(codigoTurma));
        //fazer uma query q recupere TUDOOOOOOOO de uma turma no banco e transofrme em objeto
        HashMap<Integer, Teoricas> plano = turma.getPlanoDeEnsino();
        
        if (!plano.containsValue(aula)) {
            turma.setAulaToPlanoDeEnsino(aulaCod,aula);
            aulasDAO.setAulaToPlanoDeEnsinoDaTurma(Integer.parseInt(codigoAula),turma.getCodigo(),indexData);
            atualizaTabela();

        }

    }

    public TelaAulas getTelaAula() {
        return telaAula;
    }

    private void atualizaTabela() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
