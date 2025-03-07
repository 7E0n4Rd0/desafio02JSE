# **Desafio 02 - Curso Java Spring Expert: Módulo 03**
### Enunciado
Implemente as funcionalidades necessárias para que os testes do projeto passem.

### Modelo de Domínio
![diagrama](https://github.com/7E0n4Rd0/desafio01JSE/blob/main/assets/orm_model.png)

### Critérios de Correção
- Regras de controle de acesso:
    - Somente rotas de leitura (GET) de eventos e cidades são públicas (não requer login);
    - Usuários CLIENT e/ou ADMIN podem inserir novos eventos (POST);
    - Os demais acessos são permitidos apenas a usuários ADMIN.

- Regras de validação de City:
    - Nome não pode ser vazio.

- Regras de validação de Event:
    - Nome não pode ser vazio;
    - Data não pode ser passada;
    - Cidade não pode ser nula.

### Competências avaliadas:
- Desenvolvimento TDD de API Rest com Java e Spring Boot;
- Implementação de segurança com Spring Security e OAuth2;
- Controle de acesso por rotas e perfis de usuário;
- Validação de dados com Bean Validation.
