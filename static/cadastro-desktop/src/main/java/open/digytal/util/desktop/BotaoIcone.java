package open.digytal.util.desktop;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import digytal.springdicas.utils.Imagens;

public class BotaoIcone extends JLabel {
	private String icone;
	public BotaoIcone (){
		setIcone("pasta");
	}
	
	public String getIcone() {
		return icone;
	}
	public void setIcone(String icone) {
		this.icone = icone;        
        ImageIcon imagem = null;
        if (icone != null) {
              imagem = Imagens.png(icone);
        }
        setIcon(imagem);
		
	}
}
