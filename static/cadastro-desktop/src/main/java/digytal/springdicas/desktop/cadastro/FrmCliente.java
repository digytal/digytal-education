package digytal.springdicas.desktop.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.springdicas.api.model.cadastro.Cliente;
import digytal.springdicas.api.service.cadastro.ClienteService;
import digytal.util.desktop.ss.SSMensagem;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmCliente extends FrmCadastro{
	@Autowired
	private ClienteService service;
	
	public FrmCliente() {
	}
	@Override
	public void setEntidade(Object entidade) {
		super.setEntidade(entidade);
		//campos customizados
	}
	@Override
	public void salvar() {
		Cliente entidade = getEntidade();
		entidade.setLimiteCredito(0.0d);
		if(SSMensagem.pergunta("Confirmar Salvar o Cliente?")) {
			service.salvar(tipoOperacao, entidade);
			if(cNovo.isSelected()) {
				entidade=new Cliente();
				setEntidade(entidade);
			}else
				fechar();
		}
			
		
	}

}
