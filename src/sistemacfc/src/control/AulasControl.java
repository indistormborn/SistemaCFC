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
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import sistemacfc.src.dao.TurmaDAO;
import sistemacfc.src.model.Turma;
import sistemacfc.src.model.Usuario;
import sistemacfc.src.views.TelaAulas;

/**
 *
 * @author Indiara
 */
public class AulasControl {
    
    private Usuario usuarioSessao;
    private TurmaDAO turmaDAO;
    private TelaAulas telaAula;
    
    public AulasControl(CadastroControl ctrl) {
        this.usuarioSessao=ctrl.getUsuarioSessao();
        this.turmaDAO= new TurmaDAO();
        this.telaAula= new TelaAulas(this);
    }
    
    public AulasControl(){
        
    }
    
        /*INTERAÇÕES DE TELA aba 'Suas Turmas'*/

    
    public DefaultComboBoxModel exibirTurmasProfessor(String professor) throws ClassNotFoundException, SQLException{
        Collection<Turma> turmas = turmaDAO.getTurmasByProfessor(professor);
        DefaultComboBoxModel<String> codigos = new DefaultComboBoxModel<>();
                codigos.addElement("<selecione>");

        for(Turma turma : turmas){
            codigos.addElement(turma.getCodigo().toString());
        }
        return codigos;
    }
    
    public String[] exibirInfosTurma(String codigo) throws ClassNotFoundException, SQLException{
        Integer cod = Integer.parseInt(codigo);
        Turma turma = turmaDAO.getTurmaByCodigo(cod);
        
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dataInicio = formatter.format(turma.getDataInicio());
        String dataTermino = formatter.format(turma.getDataTermino());
        
        String[] infos = new String[4];
        infos[0]=turma.getPeriodo();
        infos[1]=dataInicio;
        infos[2]=dataTermino;
        infos[4]=turma.getCurso().getTipo();
        
        return infos;
        
    }
    
    public String exibirDataSelecionada(){
        int row = telaAula.getTabelaAulas().getSelectedRow();
        return telaAula.getTabelaAulas().getValueAt(row, 0).toString();
    }
    
//    public DefaultTableModel exibirDatasAulas(String codigo) throws ClassNotFoundException, SQLException{
//        Integer cod = Integer.parseInt(codigo);
//        Turma turma = turmaDAO.getTurmaByCodigo(cod);
//        
//        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
//        String dataInicio = formatter.format(turma.getDataInicio());
//        String dataTermino = formatter.format(turma.getDataTermino());
//        
//        Date d1 = turma.getDataInicio();
//        Date d2 = turma.getDataTermino();
//        
//        DefaultTableModel model = new DefaultTableModel();
//        model.addColumn("Data da aula");
//        model.addColumn("Aula");
//       
//        while(d1 < d2){
//        
//        }
//        return model;
//    }
//    
    
    
    
    
}
