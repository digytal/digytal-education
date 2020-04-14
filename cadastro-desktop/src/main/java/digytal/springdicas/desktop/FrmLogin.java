package digytal.springdicas.desktop;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.springdicas.beans.Contexto;
import digytal.springdicas.utils.Imagens;
import open.digytal.util.desktop.ss.SSBotao;
import open.digytal.util.desktop.ss.SSCabecalho;
import open.digytal.util.desktop.ss.SSCampoSenha;
import open.digytal.util.desktop.ss.SSCampoTexto;
import open.digytal.util.desktop.ss.SSMensagem;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmLogin extends JFrame {
    private JPanel pnlLogin = new JPanel();
    private SSBotao btOk = new SSBotao();
    private SSBotao btSair = new SSBotao();
    private SSCampoTexto txtLogin = new SSCampoTexto();
    private SSCampoSenha txtSenha = new SSCampoSenha();
    public FrmLogin() {
    	this.setIconImage(Imagens.pngImage("app"));
    	setTitle("LOGIN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(259, 240));
        setLocationRelativeTo(null);

        pnlLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnlLogin.setLayout(new BorderLayout(0, 0));
        setContentPane(pnlLogin);

        SSCabecalho cabecalho = new SSCabecalho();
        cabecalho.setTitulo("LOGIN");
        cabecalho.setDescricao("Acesse o sistema");

        pnlLogin.add(cabecalho, BorderLayout.NORTH);

        JPanel conteudo = new JPanel();
        conteudo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        pnlLogin.add(conteudo, BorderLayout.CENTER);
        GridBagLayout gbl_conteudo = new GridBagLayout();
        conteudo.setLayout(gbl_conteudo);
        txtLogin.setColunas(10);

        txtLogin.setRotulo("Usuário");
        GridBagConstraints gbc_txtUsuario = new GridBagConstraints();
        gbc_txtUsuario.weightx = 1.0;
        gbc_txtUsuario.anchor = GridBagConstraints.NORTHWEST;
        gbc_txtUsuario.insets = new Insets(5, 5, 0, 5);
        gbc_txtUsuario.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtUsuario.gridx = 0;
        gbc_txtUsuario.gridy = 0;
        conteudo.add(txtLogin, gbc_txtUsuario);

        txtSenha.setRotulo("Senha");
        GridBagConstraints gbc_txtSenha = new GridBagConstraints();
        gbc_txtSenha.weighty = 1.0;
        gbc_txtSenha.anchor = GridBagConstraints.NORTHEAST;
        gbc_txtSenha.insets = new Insets(5, 5, 0, 5);
        gbc_txtSenha.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtSenha.gridx = 0;
        gbc_txtSenha.gridy = 1;
        conteudo.add(txtSenha, gbc_txtSenha);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        pnlLogin.add(panel_1, BorderLayout.SOUTH);

        btOk.setText("LOGIN");
        btOk.setIcone("login");

        panel_1.add(btOk);

        btSair.setText("SAIR");
        btSair.setIcone("fechar");
        panel_1.add(btSair);
        txtLogin.setTudoMaiusculo(false);
        txtSenha.setTudoMaiusculo(false);
        txtLogin.setText("");
        txtSenha.setText("");

        btSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               fechar();
            }
        });
        
        btOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               login();
            }
        });
    }
    private void login() {
		try {
			//logger.info(service.getClass().getName());
			
			/*
			Sessao sessao = service.login(getLogin(),getSenha());
			if (sessao==null|| sessao.getUsuario()==null) {
				SSMensagem.avisa("Credencial Inválida");
				FrmUsuario frm = CfipDesktopApp.getBean(FrmUsuario.class);
				frm.setVisible(true);
			} else {
				ambiente.setSessao(sessao);
				ambiente.setProfile(profile);
				MDICfip mdi = CfipDesktopApp.getBean(MDICfip.class);
				mdi.exibirSessao(ambiente);
				mdi.setVisible(true);
				this.dispose();
			}
			*/
			MDIPrincipal mdi = Contexto.getBean(MDIPrincipal.class);
			mdi.setVisible(true);
			this.dispose();
			//SSMensagem.avisa(txtLogin.getText());
		} catch (Exception e) {
			//logger.error(e);
			SSMensagem.erro("Erro ao tentar fazer login");
		}
	}
    /*
    public void logar(ActionListener listener) {
        btOk.addActionListener(listener);
    }
	*/
    public void exibir() {
        this.setVisible(true);
        Contexto.fecharSplash();
    }

    public String getLogin() {
        return txtLogin.getText();
    }

    public String getSenha() {
        return txtSenha.getText();
    }

    
    private void fechar() {
        System.exit(0);
    }
}
