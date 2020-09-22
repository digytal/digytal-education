package digytal.desktop;

import javax.swing.UIManager;

import digytal.desktop.form.FormularioPrincipal;

public class DesktopDemo {
	public static void main(String[] args) {
		try {
			String lf = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(lf);
			FormularioPrincipal frm = new FormularioPrincipal();
			frm.setSize(600,600);
			frm.setVisible(true);
			
		} catch (Exception e) {
			//logger.error(e);
			System.exit(0);
		}
		
	}
}
