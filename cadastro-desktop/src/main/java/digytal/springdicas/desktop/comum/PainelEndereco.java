package digytal.springdicas.desktop.comum;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;

import javax.swing.JPanel;

import digytal.springdicas.api.enums.LocalEndereco;
import digytal.springdicas.api.enums.municipio.Estados;
import digytal.springdicas.api.enums.municipio.Municipio;
import digytal.springdicas.api.model.cadastro.Endereco;
import digytal.springdicas.beans.Contexto;
import open.digytal.util.desktop.Formulario;
import open.digytal.util.desktop.ss.SSCaixaCombinacao;
import open.digytal.util.desktop.ss.SSCampoMascara;
import open.digytal.util.desktop.ss.SSCampoTexto;
import open.digytal.util.desktop.ss.SSMensagem;
import open.digytal.util.desktop.ss.evento.SSPesquisaEvento;
import open.digytal.util.desktop.ss.evento.SSPesquisaListener;


public class PainelEndereco extends JPanel {
	private SSCampoMascara cCep = new SSCampoMascara();
	private SSCampoTexto cPais = new SSCampoTexto();
	private SSCampoTexto cUf = new SSCampoTexto();
	private SSCampoTexto cComplemento = new SSCampoTexto();
	private SSCampoTexto cNumero = new SSCampoTexto();
	private SSCampoTexto cNome = new SSCampoTexto();	
	private SSCampoTexto cCIdade = new SSCampoTexto();
	private SSCampoMascara cTelefone = new SSCampoMascara();
	private SSCaixaCombinacao cLocal = new SSCaixaCombinacao();
	private SSCampoTexto cBairro = new SSCampoTexto();	
	private Endereco entidade;
	private Municipio municipio;
	public PainelEndereco() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);		
		
		GridBagConstraints gbc_cCep = new GridBagConstraints();
		gbc_cCep.insets = new Insets(3, 3, 0, 0);
		gbc_cCep.anchor = GridBagConstraints.NORTHWEST;
		gbc_cCep.gridx = 0;
		gbc_cCep.gridy = 0;
		add(cCep, gbc_cCep);

		GridBagConstraints gbc_cLocal = new GridBagConstraints();
		gbc_cLocal.insets = new Insets(3, 3, 0, 0);
		gbc_cLocal.anchor = GridBagConstraints.NORTHWEST;
		gbc_cLocal.gridx = 1;
		gbc_cLocal.gridy = 0;
		add(cLocal, gbc_cLocal);

		GridBagConstraints gbc_pnlUsarComo = new GridBagConstraints();
		gbc_pnlUsarComo.insets = new Insets(20, 0, 0, 0);
		gbc_pnlUsarComo.gridwidth = 3;
		gbc_pnlUsarComo.anchor = GridBagConstraints.NORTHWEST;
		gbc_pnlUsarComo.fill = GridBagConstraints.HORIZONTAL;
		gbc_pnlUsarComo.gridx = 2;
		gbc_pnlUsarComo.gridy = 0;
		cBairro.setObrigatorio(true);

		GridBagConstraints gbc_cBairro = new GridBagConstraints();
		gbc_cBairro.gridwidth = 3;
		gbc_cBairro.anchor = GridBagConstraints.NORTHWEST;
		gbc_cBairro.insets = new Insets(3, 3, 0, 0);
		gbc_cBairro.fill = GridBagConstraints.HORIZONTAL;
		gbc_cBairro.gridx = 0;
		gbc_cBairro.gridy = 2;
		add(cBairro, gbc_cBairro);
		
		GridBagConstraints gbc_cTelefone = new GridBagConstraints();
		gbc_cTelefone.anchor = GridBagConstraints.NORTHWEST;
		gbc_cTelefone.insets = new Insets(3, 3, 0, 3);
		gbc_cTelefone.fill = GridBagConstraints.HORIZONTAL;
		gbc_cTelefone.gridx = 3;
		gbc_cTelefone.gridy = 2;
		add(cTelefone, gbc_cTelefone);
				GridBagConstraints gbc_cCIdade = new GridBagConstraints();
		gbc_cCIdade.gridwidth = 2;
		gbc_cCIdade.anchor = GridBagConstraints.NORTHWEST;
		gbc_cCIdade.weighty = 1.0;
		gbc_cCIdade.weightx = 1.0;
		gbc_cCIdade.insets = new Insets(3, 3, 3, 0);
		gbc_cCIdade.fill = GridBagConstraints.HORIZONTAL;
		gbc_cCIdade.gridx = 0;
		gbc_cCIdade.gridy = 3;
		add(cCIdade, gbc_cCIdade);
		
		GridBagConstraints gbc_cNome = new GridBagConstraints();
		gbc_cNome.gridwidth = 2;
		gbc_cNome.anchor = GridBagConstraints.NORTHWEST;
		gbc_cNome.weightx = 1.0;
		gbc_cNome.insets = new Insets(3, 3, 0, 0);
		gbc_cNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_cNome.gridx = 0;
		gbc_cNome.gridy = 1;
		add(cNome, gbc_cNome);
				GridBagConstraints gbc_cNumero = new GridBagConstraints();
		gbc_cNumero.anchor = GridBagConstraints.NORTHWEST;
		gbc_cNumero.insets = new Insets(3, 3, 0, 0);
		gbc_cNumero.fill = GridBagConstraints.HORIZONTAL;
		gbc_cNumero.gridx = 2;
		gbc_cNumero.gridy = 1;
		add(cNumero, gbc_cNumero);

		GridBagConstraints gbc_cComplemento = new GridBagConstraints();
		gbc_cComplemento.anchor = GridBagConstraints.NORTHWEST;
		gbc_cComplemento.insets = new Insets(3, 3, 0, 3);
		gbc_cComplemento.fill = GridBagConstraints.HORIZONTAL;
		gbc_cComplemento.gridx = 3;
		gbc_cComplemento.gridy = 1;
		add(cComplemento, gbc_cComplemento);

		GridBagConstraints gbc_cUf = new GridBagConstraints();
		gbc_cUf.weighty = 1.0;
		gbc_cUf.fill = GridBagConstraints.HORIZONTAL;
		gbc_cUf.anchor = GridBagConstraints.NORTHWEST;
		gbc_cUf.insets = new Insets(5, 5, 5, 0);
		gbc_cUf.gridx = 2;
		gbc_cUf.gridy = 3;
		add(cUf, gbc_cUf);
				
		GridBagConstraints gbc_cPais = new GridBagConstraints();
		gbc_cPais.insets = new Insets(5, 5, 5, 5);
		gbc_cPais.weighty = 1.0;
		gbc_cPais.anchor = GridBagConstraints.NORTHEAST;
		gbc_cPais.fill = GridBagConstraints.HORIZONTAL;
		gbc_cPais.gridx = 3;
		gbc_cPais.gridy = 3;
		add(cPais, gbc_cPais);
		
		try {
			cCep.setMascara("##.###-###");
			cTelefone.setMascara("(##) ####-####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cLocal.setItens(LocalEndereco.values(),"nome");
		set();
		eventos();
	}
	private void set() {
		cComplemento.setColunas(20);
		cComplemento.setRotulo("Complemento");
		cNumero.setObrigatorio(true);
		cNumero.setRotulo("Número");

		cUf.setRotulo("UF");
		
		cPais.setColunas(12);
		cPais.setRotulo("Pais");

		cNome.setObrigatorio(true);
		cNome.setRotulo("Endereço");
		cCIdade.setPesquisa(true);
		cCIdade.setObrigatorio(true);
		cCIdade.setRotulo("Cidade");

		cBairro.setRotulo("Bairro");
		cTelefone.setObrigatorio(true);
		cTelefone.setColunas(12);
		cTelefone.setRotulo("Telefone");

		cLocal.setRotulo("Local");
		cCep.setObrigatorio(true);
		cCep.setColunas(8);
		cCep.setRotulo("CEP");
		cCep.setPesquisa(true);
		cUf.setEditavel(false);
		cPais.setEditavel(false);
		//cCIdade.setEditavel(false);
	}
	private void eventos() {
		cCIdade.addPesquisaListener(new SSPesquisaListener() {
			@Override
			public void pesquisaListener(SSPesquisaEvento evento) {
				pesquisarMunicipio();
			}
		});
		cCIdade.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

			}
			public void focusLost(FocusEvent e) {
				String valor = cCIdade.getText();
				if (valor != null && valor.trim().length() > 0) {
					municipio = Estados.municipio(Integer.valueOf(valor));
					exibirMunicipio();
					if (municipio == null) {
						SSMensagem.avisa("Cidade não localizada com o valor digitado\nConsulte o código IBGE");
					}	
				}

			}
		});
	}
	private void pesquisarMunicipio() {
		FrmListaMunicipios frm = Contexto.getBean(FrmListaMunicipios.class);
		municipio = (Municipio) Formulario.dialogo(frm);	
		exibirMunicipio();
	}
	private void exibirMunicipio() {
		if(municipio!=null) {
			cCIdade.setText(municipio.getNome());
			cUf.setText(municipio.getSigla());
			cPais.setText("BRASIL");
		}else {
			cCIdade.setText(null);
			cUf.setText(null);
			cPais.setText(null);
		}
		
	}
	
	public void setEntidade(Endereco entidade) {
		this.entidade=entidade;
		cPais.setText(entidade.getPais().getNome());
		cNome.setText(entidade.getLogradouro());
		cCep.setText(entidade.getCep());
		cNumero.setText(entidade.getNumero());
		cComplemento.setText(entidade.getComplemento());
		cTelefone.setText(entidade.getTelefone());
		municipio = Estados.municipio(entidade.getIbge());
		cLocal.setValue(entidade.getLocal());
		cBairro.setText(entidade.getBairro());
		exibirMunicipio();
		
	}
	public Endereco getEntidade() {
		if(municipio!=null){
			entidade.setIbge(municipio.getIbge());
			entidade.setLocalidade(cCIdade.getText());
			entidade.setUf(cUf.getText());
				
		}
		if(cLocal.getValue()!=null)
			entidade.setLocal((LocalEndereco) cLocal.getValue());
		
		entidade.setCep(cCep.getClipText());
		entidade.setLogradouro(cNome.getText());
		entidade.setBairro(cBairro.getText());
		entidade.setNumero(cNumero.getText());
		entidade.setTelefone(cTelefone.getText());
		entidade.setComplemento(cComplemento.getText());
		return entidade;
	}
	
}