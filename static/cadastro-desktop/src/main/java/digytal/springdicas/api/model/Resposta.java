package digytal.springdicas.api.model;

import java.time.LocalDate;

public class Resposta {
	private LocalDate data = LocalDate.now();
	private boolean sucesso=true;
	private String mensagem="";
	private Object codigo=0;
	private Object entidade;
	private Resposta() {
		
	}
	public static Resposta ok() {
		return ok(0, "REQUISICAO REALIZADA COM SUCESSO", null);
	}
	public static Resposta ok(Object codigo, String mensagem) {
		return ok(codigo, mensagem, null);
	}
	public static Resposta ok(Object codigo, String mensagem, Object entidade) {
		Resposta resposta = new Resposta();
		resposta.codigo=codigo;
		resposta.mensagem=mensagem;
		resposta.entidade=entidade;
		return resposta;
	} 
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public boolean isSucesso() {
		return sucesso;
	}
	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Object getCodigo() {
		return codigo;
	}
	public void setCodigo(Object codigo) {
		this.codigo = codigo;
	}
	public Object getEntidade() {
		return entidade;
	}
	public void setEntidade(Object entidade) {
		this.entidade = entidade;
	}
	
}
