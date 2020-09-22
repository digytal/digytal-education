package digytal.desktop.util;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FormularioUtil {
	public static void exibir(JPanel painel) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(painel.getSize());
		frame.add(painel,BorderLayout.CENTER);
		frame.setVisible(true);
	}
}
