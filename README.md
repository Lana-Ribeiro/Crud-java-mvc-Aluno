# Projeto CRUD em Java com Modelo MVC, Servlets e JSP

Este é um projeto de CRUD (Create, Read, Update, Delete) desenvolvido em Java para um trabalho acadêmico. O projeto segue o padrão arquitetural Modelo-Visão-Controlador (MVC) e utiliza Servlets para lidar com a camada de controle e JSP para a camada de visão.

## Pré-requisitos

Certifique-se de ter instalado o seguinte:

- Java Development Kit (JDK)
- Apache Tomcat (ou outro servidor web compatível com Java Servlets)
- Banco de dados (ex: MySQL, PostgreSQL) e as configurações adequadas

## Configuração do Banco de Dados

1. Crie um banco de dados para o projeto.
2. Execute os scripts SQL fornecidos na pasta `sql` para criar as tabelas necessárias.

## Configuração do Ambiente

1. Clone o repositório: `git clone https://github.com/seu-usuario/seu-projeto.git`
2. Importe o projeto no seu IDE favorito (Eclipse, IntelliJ, etc.).
3. Configure o servidor web para o projeto.

## Estrutura do Projeto

O projeto está estruturado da seguinte forma:

- `src/`
  - `com.example.controller/` - Contém os Servlets que atuam como controladores.
  - `com.example.model/` - Contém as classes de modelo.
  - `com.example.dao/` - Contém classes responsáveis pela interação com o banco de dados.
  - `com.example.util/` - Classes utilitárias.
- `WebContent/`
  - `WEB-INF/`
    - `jsp/` - Contém as páginas JSP para a camada de visão.
    - `lib/` - Bibliotecas JAR necessárias.

## Executando o Projeto

1. Inicie o servidor web.
2. Acesse a aplicação através do navegador: `http://localhost:8080/seu-projeto`

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests.

## Licença

Este projeto está licenciado sob a MIT License - veja o arquivo [LICENSE.md](LICENSE.md) para detalhes.
