/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemacfc.src.control;

import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import sistemacfc.src.dao.AlunoDAO;
import sistemacfc.src.dao.AulaDAO;
import sistemacfc.src.dao.ProvasDAO;
import sistemacfc.src.dao.VeiculosDAO;
import sistemacfc.src.model.Aluno;
import sistemacfc.src.model.Aulas;
import sistemacfc.src.model.Historico;
import sistemacfc.src.model.Instrutor;
import sistemacfc.src.model.Praticas;
import sistemacfc.src.model.Prova;
import sistemacfc.src.model.Teoricas;
import sistemacfc.src.model.Usuario;
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
    private VeiculosDAO veiculoDAO;
    private ProvasDAO provasDAO;

    public AgendaControl(PrincipalControl ctrl) {
        this.principal = ctrl;
        this.telaAgenda = new TelaAgendamento(this);
        this.aulaFaltante = new Teoricas();
        this.alunoDAO = new AlunoDAO();
        this.aulaDAO = new AulaDAO();
        this.veiculoDAO = new VeiculosDAO();
        this.provasDAO = new ProvasDAO();
    }

    /*AGENDAMENTO DE AULAS PRATICAS*/
    public DefaultTableModel exibeAulasTable(String instrutor) {
        return new DefaultTableModel();
    }

    public DefaultComboBoxModel exibeUmCurso(String placaVeiculo) {
        return null;
    }

    public DefaultComboBoxModel exibeUmCarro(String instrutor) {
        return null;
    }

    public DefaultComboBoxModel exibirCarros(String tipoCurso) {
        return null;
    }

    public String exibirNumeroDeAulas() {
        return "kk";
    }

    public Object exibirInfosTela(String cpf) {
        ArrayList<Historico> historico = alunoDAO.getHistoricoAulasPraticas(cpf);
        if (historico != null) {
            /*pega uma aula de exemplo para extrair o instrutor responsavle por ela*/
            Historico aulaHistorico = historico.get(0);
            String codigoAula = aulaHistorico.getAula().getCodigo().toString();
            /*pega a aula no banco pelo codigo*/
            Praticas pratica = (Praticas) aulaDAO.getAulaByCodigo(codigoAula);
            
            //AGORA ISSO SERA USADO PRA ATUALIZAR A TELA
            //pega o instrutor responsavel por aquela aula
            String instrutor = pratica.getInstrutor().getNome();
            //pega a placa do carro
            String placaCarro = veiculoDAO.getVeiculoByInstrutor(instrutor).getPlaca();
            //pega o curso que eh daquele carro
            String curso = veiculoDAO.getCursoByVeiculo(placaCarro);
            
            //SETANDO DADOS NA TELA
            telaAgenda.getInstrutorCarro().setText(instrutor);
            DefaultComboBoxModel cursoModel = exibeUmCurso(curso);
            telaAgenda.getTipoCarro().setModel(cursoModel);
            DefaultComboBoxModel carroModel = exibeUmCarro(placaCarro);
            telaAgenda.getTipoCarro().setModel(carroModel);
            
        } else {
           String tipoCurso = telaAgenda.getTipoCarro().getSelectedItem().toString();
            return exibirCarros(tipoCurso);
                       
        }
        return null;
    }

    public boolean verificaCursoTeoricoCompleto(String aluno) {
        ArrayList<Historico> historicoTeoricas = alunoDAO.getHistoricoAulasTeoricas(aluno);
        int teoricasSize = historicoTeoricas.size();
        Prova provaTeorica = alunoDAO.getProvaAprovadaByTipo(aluno, "teorica");

        if (teoricasSize == 24) {
            if (!provaTeorica.equals(null)) {
                return true;
            }

        } else {
            mensagemErro(telaAgenda, "Impossivel agendar aulas práticas enquanto curso teórico não for finalizado");
            return false;
        }
        return true;
    }

    public void getSelectedAulas(JTable entryTable) {
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

    public void agendarAulaPratica(String aluno, Collection aulas) throws ClassNotFoundException, SQLException {
        Aluno alunoObj = alunoDAO.getAlunoByCPF(aluno);
        ArrayList<Historico> historico = alunoDAO.getHistoricoAulasPraticas(aluno);
        int historicoSize = historico.size();
        ArrayList<Aulas> aulasReservadas = aulaDAO.getAulasByAluno(aluno);
        int reservasSize = aulasReservadas.size();
        int totalAulas = historicoSize + reservasSize;
        if (totalAulas < 24) {

            alunoObj.setAulasPraticas(aulas);

            aulaDAO.setAlunoToPratica(aluno);

            for (cada aula ) {
                aulaDAO.updateStatusReservado(aula, status);
            }

            mensagem de confirmacao
        } else {

            mensagem de erro que vai receber uma string
        }
    }

    //FIM DA TELA DE AGENDAMENTO DE PRATICAS
    //AGENDAMENTO DE AULAS TEORICAS
    public ArrayList<String> aulasFaltantes(String aluno) {
        return null;
        /*getplanodensinoturmaque o aluno eta matriculado*/
 /*get historico do aluno*/
        //verifica as materias que tem na turma do aluno que nao tem no historico dele
        //exibe
    }

    public String exibirAulasFaltantes() {
        return null;
    }

    public DefaultTableModel exibeTurmasDisponiveisParaReporAula() {
        return null;
    }

    public void agendarAulaTeorica(String aluno, String turma) {
        //so troca de turma o aluno, pra turma qu eira aplicar a primeira aula teorica perdida pelo mesmo
    }

    //FIM DO AGENDAMENTO DE AULAS TEORICAS
    //AGENDAMENTOD E PROVAS
    public void agendarProva(String tipo, String aluno) {

    }

    public void mensagemConfirmacao(Component tela, String msg) {
        JOptionPane.showMessageDialog(tela, msg, "CONFIRMACAO", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mensagemErro(Component tela, String msg) {
        JOptionPane.showMessageDialog(tela, msg, "ERRO", JOptionPane.ERROR);
    }

}
