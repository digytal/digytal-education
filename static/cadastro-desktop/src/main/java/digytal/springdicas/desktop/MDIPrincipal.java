package digytal.springdicas.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.springframework.stereotype.Component;

import digytal.springdicas.api.enums.LocalEndereco;
import digytal.springdicas.api.enums.municipio.Pais;
import digytal.springdicas.api.model.cadastro.CadastroEndereco;
import digytal.springdicas.api.model.cadastro.Cliente;
import digytal.springdicas.api.model.cadastro.Endereco;
import digytal.springdicas.api.model.cadastro.Pessoa;
import digytal.springdicas.beans.Contexto;
import digytal.springdicas.desktop.cadastro.FrmCliente;
import digytal.springdicas.desktop.cadastro.FrmClientes;
import open.digytal.util.desktop.MDI;

@Component
public class MDIPrincipal extends MDI {
	public MDIPrincipal() {
		
		JMenu mnCadastros = new JMenu("Cadastros");
		JMenu mnLancamentos = new JMenu("Lançamentos");
		JMenu mnConsultas = new JMenu("Consultas");
		JMenu mnRelatorios = new JMenu("Relatórios");
		JMenu mnAjuda = new JMenu("Ajuda");
		JMenu mnFerramentas = new JMenu("Ajustes");
		
		JMenuItem mniCliente = new JMenuItem("Clientes");
		mniCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirCliente();
			}
		});
		mnCadastros.add(mniCliente);

		getBarraMenu().add(mnCadastros);
		getBarraMenu().add(mnLancamentos);
		getBarraMenu().add(mnConsultas);
		getBarraMenu().add(mnRelatorios);
		getBarraMenu().add(mnFerramentas);
		getBarraMenu().add(mnAjuda);
	}
	
	private void exibirCliente() {
		FrmClientes frm = Contexto.getBean(FrmClientes.class);
		exibir(frm);
	}
	
}
