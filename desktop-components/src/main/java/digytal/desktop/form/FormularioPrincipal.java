package digytal.desktop.form;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import digytal.desktop.util.FormularioUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;

public class FormularioPrincipal extends JFrame {
	public FormularioPrincipal() {
		setTitle("Java - Exemplo de Sistema Desktop");
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCadastros = new JMenu("Cadastros");
		menuBar.add(mnCadastros);
		
		JMenuItem mnExemplo = new JMenuItem("Exemplo");
		mnCadastros.add(mnExemplo);
		mnExemplo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exibirCadastro();
			}
		});
	}
	private void exibirCadastro() {
		FormularioUtil.exibir(new FormularioConsulta());
	}
}
