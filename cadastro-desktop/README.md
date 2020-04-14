# Digytal - Treinamento, Consultoria e Fábrica de Software
www.digytal.com.br
(11) 95894 0362

# Cadastro Desktop
Projeto open source para demonstrar a construção de uma aplicação desktop utilizando os principais métodos de CRUD (insert, update, delete e select) com Java, Spring Boot, Hibernate e Swing. 

> Você que pretende aprender a programar mas não sabe por onde começar, segue sistema de cadastros em desktop para que você possa dar os primeiros passos. Se curtiu a implementação ou tem alguma dúvida, deixe um comentário para assim melhorar esta iniciativa.

## Temas abordados:
1. Integração com Spring, Spring Boot e JPA
1. Componentes desktop customizados como: Mensagem, CampoMascara, CampoNumero, CampoData, CampoCombo, Grid, Cabeçalho, Rodapé, Herança de Formulários
1. Paineis de Campos reutilizáveis como: Painel Endereço, Painel Pessoa 
1. Exemplos de Layouts: Flow Layout, Border Layout e o complexo GridBagLayout
1. Enum dos estados e cidades para não precisar buscar na base
1. Banco de dados em memoria ou arquivo texto - HSQLDB
1. Exemplo de CRUD - Tela de Consulta, Inclusão e Alteração
1. Mapeamento avançado de entidades:
* @ManyToOne (fetch / cascade)
* @OneToMany
* @Inheritance
* @Embedded
* @AttributeOverrides
* etc

## Futuro:
* Exibição de relatórios com JasperReports
* Senha com criptografia (SpringSecurity e Jasypt)
* Exemplo de consulta da API de Ceps com FeingClient

**NOTA: Fica à seu critério revisar o modelo de dados**

|Pacote         |Descrição                      
|----------------|-------------------------------
|api|Entidades e interfaces de serviços
|beans|Beans criados para o projeto
|core|Implementação JPA das interfaces
|desktop|Formularios e consulta e cadastro
|utils|Classes utilitárias
|open.digytal.util.desktop| Pacote com os componentes customizados para um projeto desktop (Pode ser um projeto maven separado)


  
### Inicialização

- Execute a classe `digytal.springdicas.Application` .
- Banco de dados HSQLDB `file:/springdicas/cadastro/database/cadastrodesktop`  .


