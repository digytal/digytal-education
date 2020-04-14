package digytal.springdicas.desktop.comum;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import digytal.springdicas.api.model.cadastro.Endereco;
import digytal.springdicas.api.model.cadastro.Pessoa;
import open.digytal.util.desktop.ss.SSCampoDataHora;
import open.digytal.util.desktop.ss.SSCampoMascara;
import open.digytal.util.desktop.ss.SSCampoTexto;
import open.digytal.util.desktop.ss.util.SSFormatador;
import open.digytal.util.desktop.ss.util.SSTexto;
import open.digytal.util.desktop.ss.util.SSValidacao;

public class PainelPessoa extends JPanel {
	private PainelEndereco painelContato = new PainelEndereco();
	private PainelEndereco painelCobranca = new PainelEndereco();
	
	private SSCampoMascara cCpfCnpj = new SSCampoMascara();
	private SSCampoTexto cTipo = new SSCampoTexto();
	private SSCampoTexto cRgIm = new SSCampoTexto();
	private SSCampoTexto cCnhIe = new SSCampoTexto();
	private SSCampoTexto cNomeRazao = new SSCampoTexto();
	private SSCampoTexto cNomeFantasia = new SSCampoTexto();
	private SSCampoTexto cEmail = new SSCampoTexto();
	private SSCampoMascara cCelular = new SSCampoMascara();
	private SSCampoMascara cCelular2 = new SSCampoMascara();
	private SSCampoDataHora cAniversario = new SSCampoDataHora();
	
	private Pessoa entidade;
	
	public PainelPessoa() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		
		
		cCpfCnpj.setColunas(15);
		cCpfCnpj.setRotulo("CPF");
		GridBagConstraints gbc_cCpfCnpj = new GridBagConstraints();
		gbc_cCpfCnpj.anchor = GridBagConstraints.NORTHEAST;
		gbc_cCpfCnpj.insets = new Insets(3, 3, 0, 0);
		gbc_cCpfCnpj.fill = GridBagConstraints.HORIZONTAL;
		gbc_cCpfCnpj.gridx = 0;
		gbc_cCpfCnpj.gridy = 0;
		add(cCpfCnpj, gbc_cCpfCnpj);
		
		
		cTipo.setColunas(10);
		cTipo.setRotulo("Tipo");
		GridBagConstraints gbc_cTipo = new GridBagConstraints();
		gbc_cTipo.anchor = GridBagConstraints.NORTHEAST;
		gbc_cTipo.insets = new Insets(3, 3, 0, 0);
		gbc_cTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_cTipo.gridx = 1;
		gbc_cTipo.gridy = 0;
		add(cTipo, gbc_cTipo);
		
		
		
		cRgIm.setRotulo("RG");
		GridBagConstraints gbc_cRgIm = new GridBagConstraints();
		gbc_cRgIm.anchor = GridBagConstraints.NORTHEAST;
		gbc_cRgIm.insets = new Insets(3, 3, 0, 0);
		gbc_cRgIm.fill = GridBagConstraints.HORIZONTAL;
		gbc_cRgIm.gridx = 2;
		gbc_cRgIm.gridy = 0;
		add(cRgIm, gbc_cRgIm);
		
		
		cCnhIe.setRotulo("CNH");
		GridBagConstraints gbc_cCnhIe = new GridBagConstraints();
		gbc_cCnhIe.anchor = GridBagConstraints.NORTHEAST;
		gbc_cCnhIe.insets = new Insets(3, 3, 0, 3);
		gbc_cCnhIe.fill = GridBagConstraints.HORIZONTAL;
		gbc_cCnhIe.gridx = 3;
		gbc_cCnhIe.gridy = 0;
		add(cCnhIe, gbc_cCnhIe);
		
		
		cNomeRazao.setRotulo("Nome");
		GridBagConstraints gbc_cNomeRazao = new GridBagConstraints();
		gbc_cNomeRazao.weightx = 1.0;
		gbc_cNomeRazao.anchor = GridBagConstraints.NORTHEAST;
		gbc_cNomeRazao.gridwidth = 4;
		gbc_cNomeRazao.insets = new Insets(3, 3, 0, 3);
		gbc_cNomeRazao.fill = GridBagConstraints.HORIZONTAL;
		gbc_cNomeRazao.gridx = 0;
		gbc_cNomeRazao.gridy = 1;
		add(cNomeRazao, gbc_cNomeRazao);
		
		
		cNomeFantasia.setRotulo("Apelido");
		GridBagConstraints gbc_cNomeFantasia = new GridBagConstraints();
		gbc_cNomeFantasia.anchor = GridBagConstraints.NORTHEAST;
		gbc_cNomeFantasia.gridwidth = 3;
		gbc_cNomeFantasia.insets = new Insets(3, 3, 0, 0);
		gbc_cNomeFantasia.fill = GridBagConstraints.HORIZONTAL;
		gbc_cNomeFantasia.gridx = 0;
		gbc_cNomeFantasia.gridy = 2;
		add(cNomeFantasia, gbc_cNomeFantasia);
		
		
		cEmail.setRotulo("E-mail");
		GridBagConstraints gbc_cEmail = new GridBagConstraints();
		gbc_cEmail.gridwidth = 2;
		gbc_cEmail.weightx = 1.0;
		gbc_cEmail.anchor = GridBagConstraints.NORTHEAST;
		gbc_cEmail.insets = new Insets(3, 3, 3, 0);
		gbc_cEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_cEmail.gridx = 0;
		gbc_cEmail.gridy = 3;
		add(cEmail, gbc_cEmail);
		
		
		cCelular.setColunas(15);
		cCelular.setRotulo("Celular");
		GridBagConstraints gbc_cCelular = new GridBagConstraints();
		gbc_cCelular.anchor = GridBagConstraints.NORTHEAST;
		gbc_cCelular.insets = new Insets(3, 3, 3, 0);
		gbc_cCelular.fill = GridBagConstraints.HORIZONTAL;
		gbc_cCelular.gridx = 2;
		gbc_cCelular.gridy = 3;
		add(cCelular, gbc_cCelular);
		
		
		cCelular2.setColunas(15);
		cCelular2.setRotulo("Celular 2");
		GridBagConstraints gbc_cCelular2 = new GridBagConstraints();
		gbc_cCelular2.anchor = GridBagConstraints.NORTHEAST;
		gbc_cCelular2.insets = new Insets(3, 3, 3, 3);
		gbc_cCelular2.fill = GridBagConstraints.HORIZONTAL;
		gbc_cCelular2.gridx = 3;
		gbc_cCelular2.gridy = 3;
		add(cCelular2, gbc_cCelular2);
		
		
		cAniversario.setRotulo("Aniversário");
		GridBagConstraints gbc_cAniversario = new GridBagConstraints();
		gbc_cAniversario.insets = new Insets(3, 3, 0, 3);
		gbc_cAniversario.anchor = GridBagConstraints.NORTHEAST;
		gbc_cAniversario.fill = GridBagConstraints.HORIZONTAL;
		gbc_cAniversario.gridx = 3;
		gbc_cAniversario.gridy = 2;
		add(cAniversario, gbc_cAniversario);
		
		JTabbedPane tEnderecos = new JTabbedPane();
		tEnderecos.addTab("Contato", painelContato);
		tEnderecos.addTab("Cobrança", painelCobranca);
		
		
		GridBagConstraints gbc_tEnderecos = new GridBagConstraints();
		gbc_tEnderecos.gridwidth = 4;
		gbc_tEnderecos.weighty = 1.0;
		gbc_tEnderecos.weightx = 1.0;
		gbc_tEnderecos.anchor = GridBagConstraints.NORTHEAST;
		gbc_tEnderecos.insets = new Insets(3, 3, 3, 3);
		gbc_tEnderecos.fill = GridBagConstraints.BOTH;
		gbc_tEnderecos.gridx = 0;
		gbc_tEnderecos.gridy = 4;
		add(tEnderecos, gbc_tEnderecos);
		
		try {
			cCelular.setMascara("(##) #####-####");
			cCelular2.setMascara("(##) #####-####");
			cEmail.setTudoMaiusculo(false);
			cTipo.setEditavel(false);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		cCpfCnpj.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				try {
					String cpfCnpj = cCpfCnpj.getText();
					if (cpfCnpj != null && cpfCnpj.trim().length() > 0)
						cCpfCnpj.setText(SSTexto.retiraSeparadores(cpfCnpj));
				} catch (Exception el) {
					el.printStackTrace();
				}
			}

			public void focusLost(FocusEvent e) {
				formataCpfCnpj(cCpfCnpj.getText());
			}
		});
		
		cCpfCnpj.requestFocus();
		
	}
	private void formataCpfCnpj(String texto) {
		if (!SSValidacao.nuloOuVazio(texto)) {
			cCpfCnpj.setText(SSFormatador.formatarCpfCnpj(SSTexto.retiraSeparadores(texto)));
		}
	}
	
	public void setEntidade(Pessoa entidade, Endereco contato, Endereco cobranca) {
		this.entidade=entidade;
		
		cAniversario.setDataHora(entidade.getAniversario());
		cCnhIe.setText(entidade.getCnhIe());
		cNomeRazao.setText(entidade.getNomeRazao());
		cEmail.setText(entidade.getEmail());
		cCelular.setText(entidade.getCelular());
		cRgIm.setText(entidade.getRgIm());
		cCelular2.setText(entidade.getCelular2());
		cCpfCnpj.setText(entidade.getCpfCnpj());
		cNomeFantasia.setText(entidade.getNomeFantasia());
		cTipo.setText(entidade.getTipo().name());
		painelContato.setEntidade(contato);
		painelCobranca.setEntidade(cobranca);
		formataCpfCnpj(entidade.getCpfCnpj());
		
	}
	public Pessoa getEntidade() {
		entidade.setCpfCnpj(cCpfCnpj.getClipText());
		entidade.setEmail(cEmail.getText());
		entidade.setNomeRazao(cNomeRazao.getText());
		entidade.setCelular2(cCelular2.getClipText());
		entidade.setNomeFantasia(cNomeFantasia.getText());
		entidade.setCelular(cCelular.getClipText());
		entidade.setAniversario(cAniversario.getDataHora());
		entidade.setRgIm(cRgIm.getText());
		entidade.setCnhIe(cCnhIe.getText());
		return entidade;
	}
	
	public Endereco getContato() {
		return painelContato.getEntidade();
	}
	public Endereco getCobranca() {
		return painelCobranca.getEntidade();
	}
}
