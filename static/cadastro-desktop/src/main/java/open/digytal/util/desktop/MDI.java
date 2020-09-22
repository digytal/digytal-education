package open.digytal.util.desktop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import digytal.springdicas.utils.Imagens;

public abstract class MDI extends JFrame {
    private JDesktopPane areaTrabalho = new JDesktopPane();
    protected JMenuBar barraMenu = new JMenuBar();
    private JLabel lblNome = new JLabel("USUARIO");
    private JLabel lblLogin = new JLabel("LOGIN");
    private JLabel lblAmbiente = new JLabel("PRODUÇÃO");
    private JLabel lblProfile = new JLabel("CONEXÃO");

    private JLabel imagemFundo = new JLabel();
    private ImageIcon imgFundo;
    public MDI() {
        areaTrabalho.setBackground(Color.LIGHT_GRAY);
        areaTrabalho.setVisible(true);
        getContentPane().setLayout(new BorderLayout());


        JLabel lblName = new JLabel("NOME:");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 11));

        JLabel lblUser = new JLabel("LOGIN:");
        lblUser.setFont(new Font("Tahoma", Font.BOLD, 11));

        JLabel lblEnv = new JLabel("AMBIENTE:");
        lblEnv.setFont(new Font("Tahoma", Font.BOLD, 11));

        JLabel lblVersao = new JLabel("Versão: 1.0");
        lblVersao.setFont(new Font("Tahoma", Font.BOLD, 11));

        JLabel lblCnn = new JLabel("PROFILE");
        lblCnn.setFont(new Font("Tahoma", Font.BOLD, 11));

        lblVersao.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblAmbiente.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblProfile.setFont(new Font("Tahoma", Font.BOLD, 11));

        lblLogin.setForeground(Color.BLUE);
        lblNome.setForeground(Color.BLUE);
        lblAmbiente.setForeground(Color.BLUE);
        lblProfile.setForeground(Color.BLUE);

        JPanel barraSessao = new JPanel();
        barraSessao.setLayout(new FlowLayout(FlowLayout.LEFT));
        getContentPane().add(barraSessao,BorderLayout.NORTH);

        barraSessao.add(lblUser);
        barraSessao.add(lblLogin);

        barraSessao.add(lblName);
        barraSessao.add(lblNome);

        barraSessao.add(lblEnv);
        barraSessao.add(lblAmbiente);

        barraSessao.add(lblCnn);
        barraSessao.add(lblProfile);

        barraSessao.add(lblVersao);

        getContentPane().add(areaTrabalho, BorderLayout.CENTER);
        setJMenuBar(barraMenu);
        setTitle("Sistema Controle de Cadastros");

        this.setIconImage(Imagens.png("app").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(new Rectangle(650, 650));
        setLocationRelativeTo(null);

        imgFundo = Imagens.jpg("fundo");
        imagemFundo.setIcon(imgFundo);
        areaTrabalho.add(imagemFundo);
        areaTrabalho.setBackground(Color.LIGHT_GRAY);
        areaTrabalho.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                desktopPane_componentResized(e);
            }
        });
    }
    private void desktopPane_componentResized(ComponentEvent e) {
        if (imgFundo == null)
            return;

        int top, left;

        top = (areaTrabalho.getWidth() / 2) - (imgFundo.getIconWidth() / 2);
        left = (areaTrabalho.getHeight() / 2) - (imgFundo.getIconHeight() / 2);

        imagemFundo.setBounds(top, left - 20, imgFundo.getIconWidth(), imgFundo.getIconHeight());
    }
    
    /*
    public void exibirSessao(Ambiente ambiente){
        lblLogin.setText(ambiente.getSessao().getUsuario().getLogin());
        lblNome.setText(ambiente.getSessao().getUsuario().getNome());
        lblProfile.setText(ambiente.getProfile());
    }
    */
    public JDesktopPane getAreaTrabalho() {
        return areaTrabalho;
    }
   
    public JMenuBar getBarraMenu() {
        return barraMenu;
    }
    protected void exibir(Formulario formulario) {
		formulario.setMdi(this);
		formulario.carregar();
		formulario.exibir();
	}
    
}
