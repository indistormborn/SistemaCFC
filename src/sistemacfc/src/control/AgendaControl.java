/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacfc.src.control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import sistemacfc.src.dao.AlunoDAO;
import sistemacfc.src.dao.AulaDAO;
import sistemacfc.src.model.Aluno;
import sistemacfc.src.model.Aulas;
import sistemacfc.src.model.Historico;
import sistemacfc.src.model.Teoricas;
import sistemacfc.src.views.TelaAgendamento;

/**
 *
 * @author Indiara
 */
public class AgendaControl {
    
    private PrincipalControl principal;
    private TelaAgendamento telaAgenda;
    private Aulas aulaFaltante;
    private AlunoDAO alunoDAO;
    private AulaDAO aulaDAO;
    
    public AgendaControl(PrincipalControl ctrl){
        this.principal=ctrl;
        this.telaAgenda = new TelaAgendamento(this);
        this.aulaFaltante=new Teoricas();
        this.alunoDAO = new AlunoDAO();
        this.aulaDAO = new AulaDAO();
    }
    
    /*AGENDAMENTO DE AULAS PRATICAS*/
    public DefaultTableModel exibeAulasTable(String instrutor){
        return new DefaultTableModel();
    }
    public String exibeNomeInstrutor(String placaVeiculo){
        return "palavra";
    }
    
    public String[] exibeInfosCarro(String instrutor){
        return new String[2];
    }
    
    public String exibirNumeroDeAulas(){
        return "kk";
    }
    
    public Object exibirInfosTela(){
        if(true){
            return exibeNomeInstrutor("placa do veiculo");
        }else{
            return exibeInfosCarro("logind o instrutor");
        }
    }
    
    public boolean verificaCursoTeoricoCompleto(String aluno){
     if(true){
         return true;
     }else{
         JOptionPane.showMessageDialog(telaAgenda, "Impossivel agendar aulas práticas enquanto curso teórico não for finalizado", "ERRO", JOptionPane.ERROR);
         return false;
     }  
    }
    
    public void getSelectedAulas( JTable entryTable) {
    DefaultTableModel model = (DefaultTableModel) entryTable.getModel();
    if (entryTable.getRowCount() > 0) {
        if (entryTable.getSelectedRowCount() > 0) {
            int selectedRow[] = entryTable.getSelectedRows();
            for (int i : selectedRow) {
                int id = Integer.parseInt(entryTable.getValueAt(i, 0).toString());
                double val1 = Double.parseDouble(entryTable.getValueAt(i, 1).toString());
                double val2 = Double.parseDouble(entryTable.getValueAt(i, 2).toString());
                model.removeRow(i);
            }
        }
    }
}
    
    public void agendarAulaPratica(String aluno, Collection aulas) throws ClassNotFoundException, SQLException{
    	ArrayList<Historico> historico = alunoDAO.getHistoricoAulasPraticas(aluno);
    	Aluno alunoObj = alunoDAO.getAlunoByCPF(aluno);
    	int historicoSize = historico.size();
        if(historicoSize < 24){
        	
        	alunoObj.setAulasPraticas(aulas);
        	
        	aulaDAO.setAlunoToPratica(aluno);
        	
        	for(cada aula){
        		aulaDAO.updateStatusReservado(aula, status);
        	}
        	
        	mensagem de confirmacao
        }
        else{
        	
        	mensagem de erro que vai receber uma string
        }
    }
    
    //FIM DA TELA DE AGENDAMENTO DE PRATICAS
    
    
    //AGENDAMENTO DE AULAS TEORICAS
   
    public ArrayList<String> aulasFaltantes(String aluno){
        return null;
    /*getplanodensinoturmaque o aluno eta matriculado*/
        /*get historico do aluno*/
        //verifica as materias que tem na turma do aluno que nao tem no historico dele
    //exibe
    }
    
    public String exibirAulasFaltantes(){
        return null;
    }
    
    public DefaultTableModel exibeTurmasDisponiveisParaReporAula(){
        return null;
    }
    
    
    public void agendarAulaTeorica(String aluno, String turma){
        //so troca de turma o aluno, pra turma qu eira aplicar a primeira aula teorica perdida pelo mesmo
    }
    
    //FIM DO AGENDAMENTO DE AULAS TEORICAS
    
    
    
    //AGENDAMENTOD E PROVAS
    public void agendarProva(String tipo, String aluno){
        
    }
    
    
    
    
}

