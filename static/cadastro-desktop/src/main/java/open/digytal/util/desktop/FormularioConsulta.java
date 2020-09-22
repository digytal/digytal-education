package open.digytal.util.desktop;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import digytal.springdicas.api.enums.TipoOperacao;
import open.digytal.util.desktop.ss.SSBotao;
import open.digytal.util.desktop.ss.SSGrade;
import open.digytal.util.desktop.ss.SSMensagem;

public abstract class FormularioConsulta extends Formulario {
	private SSBotao cmdBuscar = new SSBotao();
	private SSGrade tabela = new SSGrade();
	private JScrollPane scroll;
	public FormularioConsulta() {
		super(new BorderLayout());
		init();
	}
	private void init(){
		cmdBuscar.setText("Buscar");

		scroll = new JScrollPane();
		scroll.setViewportView(tabela);
				
		cmdBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filtrar();
			}
		});
		
		getConteudo().add(scroll,BorderLayout.CENTER);
		this.tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		super.setAlinhamentoBotoes(FlowLayout.LEFT);
		super.setExibirNovo(false);
	}
	protected void setFiltros(JPanel filtros, int x, int y) {
		filtros.setBorder(BorderFactory.createEtchedBorder());
		GridBagConstraints gbc_bBuscar = new GridBagConstraints();
		gbc_bBuscar.insets = new Insets(0, 3, 3, 3);
		gbc_bBuscar.anchor = GridBagConstraints.SOUTHWEST;
		gbc_bBuscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_bBuscar.gridx = x;
		gbc_bBuscar.gridy = y;
		filtros.add(cmdBuscar, gbc_bBuscar);
		getConteudo().add(filtros,BorderLayout.NORTH);
	}
	public SSGrade getTabela() {
		return tabela;
	}
	
	
	
	protected JScrollPane getScroll() {
		return scroll;
	}
	protected void alterar(Formulario formulario){
		//alterar(formulario,false);
		Object entidade = getTabela().getLinhaSelecionada();
		if(entidade==null){
			SSMensagem.avisa("Selecione um registro");
			return;
		}
		exibir(formulario, TipoOperacao.ALTERACAO, entidade);
	}
	protected void incluir(Formulario formulario){
		exibir(formulario, TipoOperacao.INCLUSAO, null);
	}
	protected void incluir(Formulario formulario, Object entidade){
		exibir(formulario, TipoOperacao.INCLUSAO, entidade);
	}
	protected void exibir(Formulario formulario, TipoOperacao tipoOperacao, Object entidade){
		formulario.setTipoOperacao(tipoOperacao);
		formulario.setEntidade(entidade);
		this.exibir(formulario);
		
	}
	
	protected void filtrar() {
		
	}
	protected void exibirResultado(List lista) {
		if(lista==null || (lista!=null && lista.isEmpty()))
			SSMensagem.avisa("Nenhum registro encontrado");
		getTabela().setValue(lista);
	}
	@Override
	public void setEntidade(Object entidade) {
		// TODO Auto-generated method stub
		
	}

}
