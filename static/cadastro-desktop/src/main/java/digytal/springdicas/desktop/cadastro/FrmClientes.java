package digytal.springdicas.desktop.cadastro;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.springdicas.api.enums.LocalEndereco;
import digytal.springdicas.api.enums.municipio.Pais;
import digytal.springdicas.api.model.cadastro.CadastroEndereco;
import digytal.springdicas.api.model.cadastro.Cliente;
import digytal.springdicas.api.model.cadastro.Endereco;
import digytal.springdicas.api.model.cadastro.Pessoa;
import digytal.springdicas.api.service.cadastro.ClienteService;
import digytal.springdicas.beans.Contexto;
import digytal.util.desktop.FormularioConsulta;
import digytal.util.desktop.ss.SSBotao;
import digytal.util.desktop.ss.SSCaixaCombinacao;
import digytal.util.desktop.ss.SSCampoTexto;
import digytal.util.desktop.ss.SSPosicaoRotulo;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmClientes extends FormularioConsulta {
	@Autowired
	private ClienteService service;
	private SSCaixaCombinacao tCampos = new SSCaixaCombinacao();
	private SSCampoTexto cNome = new SSCampoTexto();
	private SSBotao bSair = new SSBotao();
	private SSBotao bIncluir = new SSBotao();
	private SSBotao bAlterar = new SSBotao();
	public FrmClientes() {
		setTitulo("Clientes");
		setDescricao("Listagem de Clientes");
		
		tCampos.setRotulo("Campo");
		cNome.setRotulo("Nome");
		tCampos.setRotuloPosicao(SSPosicaoRotulo.ESQUERDA);
		cNome.setRotuloPosicao(SSPosicaoRotulo.ESQUERDA);
		cNome.setColunas(25);
		bSair.setText("Fechar");
		
		bAlterar.setText("Alterar");
		bIncluir.setText("Incluir");
		
		JPanel pFiltros = getNovoPainel();
		GridBagConstraints gbc_cNome = new GridBagConstraints();
		gbc_cNome.insets = new Insets(3, 3, 0, 0);
		gbc_cNome.anchor = GridBagConstraints.NORTHWEST;
		gbc_cNome.gridx = 0;
		gbc_cNome.gridy = 0;
		pFiltros.add(cNome, gbc_cNome);
		
		getTabela().getModeloTabela().addColumn("CPF/CNPJ");
		getTabela().getModeloTabela().addColumn("Nome");
		getTabela().getModeloColuna().getColumn(0).setPreferredWidth(120);
		getTabela().getModeloColuna().getColumn(1).setPreferredWidth(250);
		getTabela().getModeloColuna().setCampo(0, "pessoa.cpfCnpj");
		getTabela().getModeloColuna().setCampo(1, "pessoa.nomeRazao");
		
		super.setFiltros(pFiltros, 1, 0);
		
		bSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fechar();
			}
		});
		
		bIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluir(Contexto.getBean(FrmCliente.class),criarClienteDemo());
			}
		});
		
		bAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alterar(Contexto.getBean(FrmCliente.class));
			}
		});
		
		getRodape().add(bIncluir);
		getRodape().add(bAlterar);
		getRodape().add(bSair);
	}
	public void filtrar() {
		List lista = service.listar("pessoa.nomeRazao", cNome.getText());
		exibirResultado(lista);
	}
	@Override
	public void carregar() {
		
	}
	private Cliente criarClienteDemo() {
		Endereco e = new Endereco();
		e.setPais(Pais.BRASIL);
		e.setBairro("CENTRO");
		e.setCep("65300000");
		e.setComplemento("BUFFET MIRIM NOVO");
		e.setIbge(2109908);
		e.setLocalidade("SANTA INES");
		e.setLogradouro("RUA DO COMERCIO");
		e.setNumero("853");
		e.setTelefone("9836539090");
		e.setUf("MA");
		e.setLocal(LocalEndereco.CASA);

		CadastroEndereco ce = new CadastroEndereco();
		ce.setCobranca(e);
		ce.setContato(e);
		
		Pessoa p=new Pessoa();

		p.setAniversario(new Date());
		p.setCelular("11912345678");
		p.setCelular2("11912345678");
		p.setCpfCnpj("00732822129");
		p.setCelular("11912345678");
		p.setNomeFantasia("GLEYSON SAMPAIO");
		p.setCelular2("11912345678");
		p.setNomeRazao("GLEYSON SAMPAIO");
		p.setRgIm("1120923-SSPMA");
		p.setCnhIe("12687126");
		p.setEmail("gleyson.s@hotmail.com");
		
		Cliente entidade = new Cliente();
		entidade.setPessoa(p);
		entidade.setLimiteCredito(1000.0);
		entidade.setEndereco(ce);
		return entidade;
	}
}

