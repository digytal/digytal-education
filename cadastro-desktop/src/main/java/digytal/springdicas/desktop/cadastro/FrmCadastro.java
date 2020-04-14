package digytal.springdicas.desktop.cadastro;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.springdicas.api.model.cadastro.Cadastro;
import digytal.springdicas.desktop.comum.PainelPessoa;
import open.digytal.util.desktop.Formulario;
import open.digytal.util.desktop.ss.SSBotao;


public abstract class FrmCadastro extends Formulario {
	private PainelPessoa painelPessoa = new PainelPessoa();
	private SSBotao bSalvar = new SSBotao();
	private SSBotao bFechar= new SSBotao();
	protected JTabbedPane tCampos = new JTabbedPane();
	public FrmCadastro(JPanel dadosComplementares) {
		this();
		tCampos.addTab("Dados Complementares", dadosComplementares);
	}
	public FrmCadastro() {
		setTitulo("Cadastro");
		setDescricao("Inclusão e Alteração");
		tCampos.addTab("Dados Pessoais", painelPessoa);
		
		GridBagConstraints gbc_tCampos = new GridBagConstraints();
		gbc_tCampos.weighty = 1.0;
		gbc_tCampos.weightx = 1.0;
		gbc_tCampos.anchor = GridBagConstraints.NORTHEAST;
		gbc_tCampos.fill = GridBagConstraints.BOTH;
		gbc_tCampos.gridx = 0;
		gbc_tCampos.gridy = 0;
		getConteudo().add(tCampos, gbc_tCampos);
		
		getRodape().add(bSalvar);
		getRodape().add(bFechar);
		
		bSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarCadastro();
			}
		});
		
		bFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		
		bSalvar.setText("Salvar");
		bFechar.setText("Fechar");
	}
	public void setEntidade(Object entidade) {
		this.entidade = entidade;
		Cadastro cadastro = getEntidade();
		this.painelPessoa.setEntidade(cadastro.getPessoa(), cadastro.getEndereco().getContato(), cadastro.getEndereco().getCobranca());
	}
	public abstract void salvar();
	private void salvarCadastro() {
		Cadastro entidade = getEntidade();
		
		entidade.setPessoa(painelPessoa.getEntidade());
		entidade.getEndereco().setContato(painelPessoa.getContato());
		entidade.getEndereco().setCobranca(painelPessoa.getCobranca());	
		salvar();
	}
	
}
