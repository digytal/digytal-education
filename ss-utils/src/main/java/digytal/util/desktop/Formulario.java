package digytal.util.desktop;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.beans.PropertyVetoException;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

import digytal.util.desktop.ss.SSCabecalho;
import digytal.util.desktop.ss.SSMensagem;
import digytal.util.desktop.ss.SSRodape;

public abstract class Formulario extends JPanel {
	private SSCabecalho cabecalho = new SSCabecalho();
	private SSRodape rodape = new SSRodape();
	private JPanel conteudo = new JPanel();
	protected JCheckBox cNovo = new JCheckBox("Novo?");
	protected TipoOperacao tipoOperacao;
	private Formulario dono;
	private Object resposta;
	protected Object entidade;
	private static Object respostaDialogo;
	
	private MDI mdi;
	
	public Formulario() {
		this(new GridBagLayout());
	}
	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
	public Formulario(LayoutManager layout) {
		this.conteudo.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.setLayout(new BorderLayout());

		this.setTitulo("Informe um título");
		this.setDescricao("Informe uma descrição");

		this.add(cabecalho, BorderLayout.NORTH);
		this.add(conteudo, BorderLayout.CENTER);
		this.add(rodape, BorderLayout.SOUTH);
		
		conteudo.setLayout(layout);
		rodape.setLayout(new FlowLayout(FlowLayout.RIGHT));
		rodape.add(cNovo);
	}
	
	public String getTitulo() {
		return cabecalho.getTitulo();
	}
	
	public void carregar() {

	}
	
	public MDI getMdi() {
		return mdi;
	}
	public void setMdi(MDI mdi) {
		this.mdi = mdi;
	}
	
	public void exibir() {
		this.exibir(this);
	}
	
	public void exibir(Formulario frm) {
		if (frm != this) {
			frm.setMdi(this.getMdi());
		}
		//frm.setLogin(this.getLogin());
		JInternalFrame internal = new JInternalFrame(getTitulo());
		internal.setFrameIcon(Imagens.png("app"));
		internal.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		internal.setResizable(true);
		internal.setVisible(true);
		internal.setContentPane(frm);
		internal.setSize(frm.getWidth(), frm.getHeight());
		try {
			internal.setSelected(true);
			internal.pack();
			centralizar(internal);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		frm.carregar();
		mdi.getAreaTrabalho().add(internal);
		mdi.getAreaTrabalho().getDesktopManager().activateFrame(internal);
		   
		
	}
	
	
	public void setTitulo(String titulo) {
		this.cabecalho.setTitulo(titulo);
	}
	public void setDescricao(String descricao) {
		this.cabecalho.setDescricao(descricao);
	}
	public JPanel getConteudo() {
		return conteudo;
	}
	public SSCabecalho getCabecalho() {
		return cabecalho;
	}
	public SSRodape getRodape() {
		return rodape;
	}
	protected void setAlinhamentoBotoes(int alinhamento) {
		this.rodape.setLayout(new FlowLayout(alinhamento));
	}
	public void ocultarNovo() {
		this.cNovo.setVisible(false);
	}
	public void setExibirNovo(boolean exibir) {
		this.cNovo.setVisible(exibir);
	}
	public JPanel getNovoPainel() {
		JPanel novo = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		novo.setLayout(gridBagLayout);		
		return novo;
	}
	
	public static Object dialogo(Formulario form) {
		respostaDialogo=null;
		form.carregar();
		criarDialogo(form);
		return respostaDialogo;
	}
	private static void criarDialogo(JPanel form) {
		JDialog dialog = new JDialog();
		dialog.setResizable(false);
		
		dialog.setUndecorated(true);
		//dialog.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		dialog.setModal(true);
		dialog.setContentPane(form);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		dialog.dispose();
	}
	
	
	public void fechar(Object resposta) {
		if (resposta != null) {
			if(dono!=null)
				dono.resposta = resposta;
			respostaDialogo=resposta;
			fechar();
		} else {
			SSMensagem.avisa("Selecione um item da item");
		}
		resposta=null;
	}

	public void fechar() {
		if(isInternal(this))
			removerFormulario();
		else if(isJanela(this))
			fecharJanela();
		else if (isDialogo(this))
			fecharDialogo();
		else
			removerFormulario();
	}
	
	private void fecharDialogo() {
		JDialog dialog = (JDialog) SwingUtilities.getAncestorOfClass(JDialog.class, this);
		dialog.dispose();
		dialog.setVisible(false);
	}
	
	private void removerFormulario() {
		JInternalFrame iframe = (JInternalFrame) SwingUtilities.getAncestorOfClass(JInternalFrame.class, this);
		if (mdi != null) {
			mdi.getAreaTrabalho().remove(iframe);
			mdi.getAreaTrabalho().repaint();
		}
	}
	
	private void fecharJanela() {
		JFrame frame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this);
		frame.setVisible(false);
		frame.dispose();
	}
	
	private void centralizar(JInternalFrame componente) {
		Dimension dim = mdi.getSize();
		int x = dim.width / 2 - componente.getSize().width / 2;
		int y = dim.height / 2 - componente.getSize().height / 2;
		y = y - 50; // opcional
		componente.setLocation(x, y);
		componente.setVisible(true);
	}
	public boolean isInternal(Formulario form) {
		return SwingUtilities.getAncestorOfClass(JInternalFrame.class, form) != null;
	}
	public boolean isJanela(Formulario form) {
		return SwingUtilities.getAncestorOfClass(JFrame.class, form) != null;
	}
	public boolean isDialogo(Formulario form) {
		return SwingUtilities.getAncestorOfClass(JDialog.class, form) != null;
	}
	
	public abstract void setEntidade(Object entidade);
	
	public <T> T getEntidade() {
		return (T) entidade;
	}
	
}
