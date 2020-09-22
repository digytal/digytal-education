package digytal.springdicas.desktop.comum;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.springdicas.api.enums.municipio.Estados;
import digytal.springdicas.api.enums.municipio.Municipio;
import digytal.util.desktop.FormularioConsulta;
import digytal.util.desktop.ss.SSBotao;
import digytal.util.desktop.ss.SSCampoTexto;
import digytal.util.desktop.ss.SSMensagem;
import digytal.util.desktop.ss.SSPosicaoRotulo;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmListaMunicipios extends FormularioConsulta {
	private SSBotao bOk = new SSBotao();
	private SSBotao bFechar = new SSBotao();
	private SSCampoTexto cNome = new SSCampoTexto();
	public FrmListaMunicipios() {
		init();
	}

	public void init() {
		super.setTitulo("Cidades");
		super.setDescricao("Consulta de Cidades");
		bFechar.setText("Sair");
		bFechar.setIcone("fechar");
		bOk.setText("OK");
		
		cNome.setRotulo("Nome");
		cNome.setColunas(15);
		cNome.setRotuloPosicao(SSPosicaoRotulo.ESQUERDA);
	
		
		JPanel pFiltros = getNovoPainel();
		GridBagConstraints gbc_cNome = new GridBagConstraints();
		gbc_cNome.insets = new Insets(3, 3, 0, 0);
		gbc_cNome.anchor = GridBagConstraints.NORTHWEST;
		gbc_cNome.gridx = 0;
		gbc_cNome.gridy = 0;
		pFiltros.add(cNome, gbc_cNome);
		
		getTabela().adicionarColuna(0, "Código", "codigo");
		getTabela().adicionarColuna(1, "Nome", "nome");
		getTabela().adicionarColuna(2, "UF", "sigla");
		getTabela().definirLarguraColunas(60, 170,50);
				
		
		getRodape().add(bOk);
		getRodape().add(bFechar);
		
		super.setAlinhamentoBotoes(FlowLayout.RIGHT);
		super.setFiltros(pFiltros, 1, 0);
	
		bOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ok();
			}
		});
		
		
		bFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		
	}
	
	private void ok() {
		Municipio entidade = (Municipio) getTabela().getLinhaSelecionada();
		fechar(entidade);
	}

	public void filtrar() {
		try {
			String nome = cNome.getText();
			if (nome.isEmpty()) {
				SSMensagem.avisa("Informe um nome para a pesquisa");
				return;
			}
			List<Municipio> lista = Estados.municipios(nome);
			if (lista.size() == 0) {
				SSMensagem.pergunta("Cidade não encontrada");
			}
			getTabela().setValue(lista);
		} catch (Exception e) {
			e.printStackTrace();
			SSMensagem.erro(e.getMessage());
		}

	}
}
