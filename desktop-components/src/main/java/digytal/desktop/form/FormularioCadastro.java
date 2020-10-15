package digytal.desktop.form;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import digytal.desktop.util.Sexo;
import digytal.util.desktop.ss.SSBotao;
import digytal.util.desktop.ss.SSCabecalho;
import digytal.util.desktop.ss.SSCaixaCombinacao;
import digytal.util.desktop.ss.SSCampoDataHora;
import digytal.util.desktop.ss.SSCampoMascara;
import digytal.util.desktop.ss.SSCampoNumero;
import digytal.util.desktop.ss.SSCampoTexto;
import digytal.util.desktop.ss.SSRodape;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.ParseException;
import java.util.Date;

public class FormularioCadastro extends JPanel {
	private SSCabecalho cabecalho = new SSCabecalho();
	private SSRodape rodape = new SSRodape();
	private SSBotao bSair = new SSBotao();
	private SSBotao bSalvar = new SSBotao();
	private SSCaixaCombinacao bCombo = new SSCaixaCombinacao();
	private SSCampoTexto bTexto = new SSCampoTexto();
	private SSCampoNumero bNumero = new SSCampoNumero();
	private SSCampoDataHora bData = new SSCampoDataHora();
	private SSCampoDataHora bDataHora = new SSCampoDataHora();
	private SSCampoMascara bMascara = new SSCampoMascara();
	private final JPanel painelCampos = new JPanel();
	
	
	public FormularioCadastro() {
		setSize(300,300);
		cabecalho.setTitulo("Formulario Cadastro");
		cabecalho.setDescricao("Exemplo de Formulario de Cadastro");
		bSair.setText("Fechar");
		bSalvar.setText("Salvar");
		
		bTexto.setRotulo("Campo Texto");
		bNumero.setRotulo("Campo Número");
		bCombo.setRotulo("Campo Opção");
		bData.setRotulo("Campo Data");
		bDataHora.setRotulo("Campo Data Hora");
		bMascara.setRotulo("Campo Máscara");
		
		bTexto.setText("DIGITE UM TEXTO AQUI");
		bNumero.setValue(1234.5);
		try {
			bDataHora.setFormato("dd/MM/yy HH:mm");
			bMascara.setMascara("(##) #####-####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		bData.setDataHora(new Date());
		bDataHora.setDataHora(new Date());
		
		bMascara.setText("9836834510");
		bCombo.setItens(Sexo.values());
		
		
		setLayout(new BorderLayout());
		add(cabecalho,BorderLayout.NORTH);
		add(rodape,BorderLayout.SOUTH);
		
		rodape.add(bSalvar);
		rodape.add(bSair);
		
		add(painelCampos, BorderLayout.CENTER);
		GridBagLayout gbl_painelCampos = new GridBagLayout();
		painelCampos.setLayout(gbl_painelCampos);
		
		GridBagConstraints gTexto = new GridBagConstraints();
		gTexto.weightx = 1.0;
		gTexto.insets = new Insets(3, 3, 3, 3);
		gTexto.anchor = GridBagConstraints.NORTHWEST;
		gTexto.fill = GridBagConstraints.HORIZONTAL;
		gTexto.gridx = 0;
		gTexto.gridy = 0;
		painelCampos.add(bTexto, gTexto);
		
		GridBagConstraints gCombo = new GridBagConstraints();
		gCombo.insets = new Insets(3, 3, 3, 3);
		gCombo.anchor = GridBagConstraints.NORTHWEST;
		gCombo.fill = GridBagConstraints.HORIZONTAL;
		gCombo.gridx = 0;
		gCombo.gridy = 1;
		painelCampos.add(bCombo, gCombo);
		
		GridBagConstraints gNumero = new GridBagConstraints();
		gNumero.insets = new Insets(3, 3, 3, 3);
		gNumero.anchor = GridBagConstraints.NORTHWEST;
		gNumero.fill = GridBagConstraints.HORIZONTAL;
		gNumero.gridx = 0;
		gNumero.gridy = 2;
		painelCampos.add(bNumero, gNumero);
		
		GridBagConstraints gData = new GridBagConstraints();
		gData.insets = new Insets(3, 3, 3, 3);
		gData.anchor = GridBagConstraints.NORTHWEST;
		gData.fill = GridBagConstraints.HORIZONTAL;
		gData.gridx = 0;
		gData.gridy = 3;
		painelCampos.add(bData, gData);
		
		GridBagConstraints gDataHora = new GridBagConstraints();
		gDataHora.insets = new Insets(3, 3, 3, 3);
		gDataHora.anchor = GridBagConstraints.NORTHWEST;
		gDataHora.fill = GridBagConstraints.HORIZONTAL;
		gDataHora.gridx = 0;
		gDataHora.gridy = 4;
		painelCampos.add(bDataHora, gDataHora);
		
		GridBagConstraints gMascara = new GridBagConstraints();
		gMascara.weighty = 1.0;
		gMascara.insets = new Insets(3, 3, 3, 3);
		gMascara.anchor = GridBagConstraints.NORTHWEST;
		gMascara.fill = GridBagConstraints.HORIZONTAL;
		gMascara.gridx = 0;
		gMascara.gridy = 5;
		painelCampos.add(bMascara, gMascara);
		
		
		
	}
}
