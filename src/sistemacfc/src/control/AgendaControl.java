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
import sistemacfc.src.model.Veiculo;
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

    public PrincipalControl getPrincipal(){
        return principal;
    }
    
    /*AGENDAMENTO DE AULAS PRATICAS*/
    public DefaultTableModel exibeAulasTable(String instrutor) {
        ArrayList<Praticas> aulas = aulaDAO.getPraticasByInstrutor(instrutor);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Codigo");
        model.addColumn("Data");
        model.addColumn("Horario");
        
        for(Praticas aula : aulas){
            Integer c = aula.getCodigo();
            String d = aula.getData().toString();
            String h = String.valueOf(aula.getHorario());
            model.addRow(new Object[]{c,d,h});
        }
        
        return model;
    }

    public DefaultComboBoxModel exibeUmCurso(String placaVeiculo) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        String curso = veiculoDAO.getCursoByVeiculo(placaVeiculo);
        model.addElement(curso);
        return model;
    }

    public DefaultComboBoxModel exibeUmCarro(String instrutor) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        String placa = veiculoDAO.getVeiculoByInstrutor(instrutor).getPlaca();
        model.addElement(placa);
        return model;
    }

    public DefaultComboBoxModel exibirCarros(String tipoCurso) {
        ArrayList<Veiculo> veiculos = veiculoDAO.getVeiculosByCurso(tipoCurso);
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("<selecione>");
        
        for(Veiculo carro : veiculos){
            model.addElement(carro.getPlaca());
        }
        
        return model;
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
            return (DefaultComboBoxModel) exibirCarros(tipoCurso);
                       
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

    public ArrayList<Praticas> getSelectedAulas(JTable entryTable) {
        ArrayList<Praticas> aulas = new ArrayList<>();
        int rows[] = entryTable.getSelectedRows();
        
        for(int i=0; i < rows.length; i++){
            String codigo = entryTable.getValueAt(rows[i], 0).toString();
            Praticas pratica = (Praticas) aulaDAO.getAulaByCodigo(codigo);
            aulas.add(pratica);
        }
        
        return aulas;
    }

    public void agendarAulaPratica(String aluno, ArrayList<Praticas> aulas) throws ClassNotFoundException, SQLException {
        Aluno alunoObj = alunoDAO.getAlunoByCPF(aluno);
       
        ArrayList<Historico> historico = alunoDAO.getHistoricoAulasPraticas(aluno);
        int historicoSize = historico.size();
        
        ArrayList<Aulas> aulasReservadas = aulaDAO.getAulasByAluno(aluno);
        int reservasSize = aulasReservadas.size();
        
        int totalAulas = historicoSize + reservasSize;

        if (totalAulas < 24) {
            alunoObj.setAulasPraticas(aulas);
            for (Aulas aula : aulas) {
                aulaDAO.setAlunoToPratica(String.valueOf(aula.getCodigo()),aluno);
                aulaDAO.updateStatusReservado(aula.getCodigo(), 1);
            }
            this.mensagemConfirmacao(telaAgenda, "Aulas agendadas com sucesso!");
        } else {
            this.mensagemErro(telaAgenda, "Impossivel reservar este numero de aulas práticas para o aluno, verifique quantas aulas faltam para a conclusão do curso");

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
    
    
    
    
    
    
    
//AGENDAMENTO DE PROVAS
    public void agendarProva(String cpf, String data) throws ClassNotFoundException, SQLException {
        Prova prova = provasDAO.getProvaByData(data);
        String codigo = String.valueOf(prova.getCodigo());
        if(!prova.equals(null)){
            alunoDAO.setProvaToAluno(codigo, cpf);
            mensagemConfirmacao(telaAgenda, "Prova agendada com sucesso!");
        }

    }
    
    public DefaultTableModel exibirProvas(String tipo) throws ClassNotFoundException, SQLException{
        ArrayList<Prova> provas = provasDAO.getProvasByTipo(tipo);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Data");
        model.addColumn("Horario");
        
        for(Prova prova : provas){
            String d = prova.getData().toString();
            String h = prova.getHorario();
            model.addRow(new Object[]{d,h});
        }
        
        return model;
    }
    
    public String verificarCPF(String cpf) throws ClassNotFoundException, SQLException{
        Aluno aluno = alunoDAO.getAlunoByCPF(cpf);
        if(aluno==null){
            mensagemErro(telaAgenda, "CPF não encontrado no sistema!");
        }else{
           return principal.exibirNomeAluno(cpf);
        }
        return null;
    }
//FIM DO AGENDAMENTO DE PROVAS
    

    
    
    
    
    
    
    public void mensagemConfirmacao(Component tela, String msg) {
        JOptionPane.showMessageDialog(tela, msg, "CONFIRMACAO", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mensagemErro(Component tela, String msg) {
        JOptionPane.showMessageDialog(tela, msg, "ERRO", JOptionPane.ERROR_MESSAGE);
    }

    public TelaAgendamento getTelaAgenda() {
        return telaAgenda;
    }

}
