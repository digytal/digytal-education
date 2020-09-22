package digytal.desktop.form;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import digytal.desktop.util.Entidade;
import digytal.util.desktop.ss.SSBotao;
import digytal.util.desktop.ss.SSCabecalho;
import digytal.util.desktop.ss.SSGrade;
import digytal.util.desktop.ss.SSRodape;

public class FormularioConsulta extends JPanel {
	private SSCabecalho cabecalho = new SSCabecalho();
	private SSRodape rodape = new SSRodape();
	private SSBotao bSair = new SSBotao();
	private SSBotao bNovo = new SSBotao();
	private SSBotao bAlterar = new SSBotao();
	private SSGrade tabela = new SSGrade();
	private JScrollPane scroll;
	public FormularioConsulta() {
		setSize(400,400);
		cabecalho.setTitulo("Formulario Consulta");
		cabecalho.setDescricao("Exemplo de Formulario de Consulta");
		bSair.setText("Fechar");
		bAlterar.setText("Alterar");
		bNovo.setText("Novo");
		
		setLayout(new BorderLayout());
		add(cabecalho,BorderLayout.NORTH);
		add(rodape,BorderLayout.SOUTH);
		
		rodape.setAlinhamento(FlowLayout.LEFT);
		rodape.add(bNovo);
		rodape.add(bAlterar);
		rodape.add(bSair);
		
		
		tabela.getModeloTabela().addColumn("ID");
		tabela.getModeloTabela().addColumn("Nome");
		tabela.getModeloColuna().getColumn(0).setPreferredWidth(30);
		tabela.getModeloColuna().getColumn(1).setPreferredWidth(250);
		tabela.getModeloColuna().setCampo(0, "id");
		tabela.getModeloColuna().setCampo(1, "nome");
		
		scroll = new JScrollPane();
		scroll.setViewportView(tabela);
		add(scroll,BorderLayout.CENTER);
		
		List<Entidade> entidades = new ArrayList<Entidade>();
		entidades.add(new Entidade(1, "FRANK MARLON"));
		entidades.add(new Entidade(2, "RAIMUNDO BRANCO"));
		entidades.add(new Entidade(3, "GLEYSON SAMPAIO"));
		entidades.add(new Entidade(4, "RAFAEL ALUNO"));
		
		tabela.setValue(entidades);
		
	}
}
