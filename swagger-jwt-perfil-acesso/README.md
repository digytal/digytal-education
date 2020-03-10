# Digytal - Treinamento, Consultoria e Fábrica de Software
www.digytal.com.br
(11) 95894 0362

# Swagger  - JWT - Perfil Acesso
Projeto open source para demonstrar a construção de uma API Rest com configurações de perfis de acesso de forma dinâmica e segurança nos recursos disponibilizados.

> Aqui na Digytal acreditamos que é imprescindivel uma API segura com perfis de acesso e uma boa documentação.
Segue um exemplo de nossa API com controle de perfis de acesso deixando seus regras (roles) mais dinâmicas e seus recursos (rest controller) mais seguros.
Vocês já sabem né, dúvidas e sugestões são sempre bem-vindas.

|Pacote         |Descrição                      
|----------------|-------------------------------
|domain|Classes de Entidade relacionadas ao banco de dados
|repository|Classes de repositorio e acesso a dados
|controller|Classes que representam os recursos fornecidos pela API
|configuration|Classes de configuração como Swagger entre outros
|secutiry|Classes de configuração de segurança dos recursos da API
|service|Classes que contém regras de negócio da aplicação 

  
### Inicialização

- Execute a classe `digytal.springdicas.SwaggerJwtPerfilAcessoApplication`.
	- Alguns processos irão acontecer:
	1: A classe `digytal.springdicas.service.AcessoService` dará uma carga inicial de **Usuários, Perfis e Permissões** onde você poderá consultar os registros na base de dados de sua preferência. (ver `application.properties` ) 

> Se você preferiu utilizar o banco de dados H2 configurado no
> `application.properties`, pode realizar consultas pelo link
> `http://localhost:8080/h2-console/` com a seguinte configuração de
> acesso:

- Acesse a URL `localhost:8080/swagger-ui.html`  .
- Conforme carga inicial você terá 2 perfis de acesso (USER, MASTER) e seus respectivos usuários:
	Login: user      / Senha: user
	Login: master / Senha: master

> NOTA: Teste chamar todos os recursos de listagem com o token gerado para os usuários: user e master veja a validação do perfil de acesso de cada usuário, exemplo:

     POST: http://localhost:8080/login
    {
      "password": "master",
      "username": "master"
    }
    
    Resposta Token:
    {  "login":  "master",  "menus":  [  "ROLE_PERMISSOES_MENU",  "ROLE_FUNCIONALIDADES_MENU",  "ROLE_PERFIS_MENU"  ],  "token":  "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXN0ZXIiLCJzY29wZXMiOiJST0xFX0ZVTkNJT05BTElEQURFU19ERUxFVEUsUk9MRV9GVU5DSU9OQUxJREFERVNfSU5TRVJULFJPTEVfRlVOQ0lPTkFMSURBREVTX01FTlUsUk9MRV9GVU5DSU9OQUxJREFERVNfU0VBUkNILFJPTEVfRlVOQ0lPTkFMSURBREVTX1VQREFURSxST0xFX0ZVTkNJT05BTElEQURFU19WSUVXLFJPTEVfUEVSRklTX0RFTEVURSxST0xFX1BFUkZJU19JTlNFUlQsUk9MRV9QRVJGSVNfTUVOVSxST0xFX1BFUkZJU19TRUFSQ0gsUk9MRV9QRVJGSVNfVVBEQVRFLFJPTEVfUEVSRklTX1ZJRVcsUk9MRV9QRVJNSVNTT0VTX0RFTEVURSxST0xFX1BFUk1JU1NPRVNfSU5TRVJULFJPTEVfUEVSTUlTU09FU19NRU5VLFJPTEVfUEVSTUlTU09FU19TRUFSQ0gsUk9MRV9QRVJNSVNTT0VTX1VQREFURSxST0xFX1BFUk1JU1NPRVNfVklFVyIsImlhdCI6MTU4MzgxMDUwOCwiZXhwIjoxNTgzODE0MTA4fQ.tVK5fYYfGu7sYwdkTTNUZLJn7Z8wgJe4ZDisM5cM1ro"  }

 


