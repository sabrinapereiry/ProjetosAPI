# ProjetosAPI

<h1 align="center">
   E-COMMERCE
</h1>
    
Projeto desenvolvido para aula de API - Application Programming Interface (Interface de Programação de Aplicação)
<br> <br>

## ÍNDICE ##
 - SOBRE O PROJETO
 - REQUISITOS
 - FUNCIONALIDADES
 - RESTRIÇÕES 
 - ACESSO AO PROJETO
 - TECNOLOGIAS UTILIZADAS
 - AUTORES DO PROJETO
    <br> <br> <br>

## ✔ SOBRE O PROJETO ##
- Nesse projeto foi desenvolvida a API RESTFul de um E-commerce
- O banco de dados e as tabelas foram criados de acordo com o DER abaixo, a partir das Entidades;
<p align="center">
  <img src="https://github.com/sabrinapereiry/ProjetosAPI/blob/caa634e92ce8bfdd693134265722e32209c55cec/ecommerce/imagens/DER_API.png">
 </p>
<br> 

## 📝 REQUISITOS ##
- Para todos os recursos da API são disponibilizados os métodos CRUD; 
- O Relatório de Pedido, criado com a utilização do DTO,  contém: id do pedido, data do pedido, valor total; Relação de itens do pedido: código e nome do produto, preço de venda, quantidade, valor bruto, percentual de desconto e valor líquido;
- Para transição dos dados referentes aos métodos CRUD foram utilizadas Entidades e DTOs; 
- Nos métodos CRUD foram identificadas e tratadas as exceções de item não encontrado, com a exibição de mensagem personalizada;
- As imagens dos Produtos estão armazenadas no banco de dados;
- A cada novo pedido cadastrado é enviado um e-mail contendo o Relatório de Pedido;
- Foi imlementada a autenticação e o controle de acesso à API (com o módulo de segurança do Spring + JWT).
<br> <br> <br>

## 🔄 FUNCIONALIDADES ##  
- No ato de cadastro de um novo pedido é calculado os valores bruto e líquido dos produtos: valor bruto (preço venda * qtde) e valor líquido (valor bruto – valor desconto; sendo esse último calculado através da aplicação do percentual de desconto sobre o valor bruto);
- Ao final do cadastro de um novo pedido é calculado e armazenado o seu valor total: soma dos valores líquidos dos itens do pedido;
- Os dados do endereço do Cliente são obtidos a partir de sua coleta numa API externa de consulta de CEP.
<br> <br> <br>

## 🚨 RESTRIÇÕES ##
Não é possível cadastrar:
- um pedido com data retroativa à atual;
- um produto com descrição idêntica a uma já existente;
- diferentes clientes com um mesmo número de CPF;
- diferentes clientes com um mesmo endereço de e-mail.
  <br> <br> <br>

 ## 💻 ACESSO AO PROJETO ##

 https://github.com/sabrinapereiry/ProjetosAPI/tree/main/ecommerce
<br> <br> <br>

## 🛠 TECNOLOGIAS UTILIZADAS ##
- [Dbeaver](https://dbeaver.io/download/)
- [Git v2.41.0.3](https://git-scm.com/downloads)
- [GitHub](https://www.github.com/)
- [Insomnia](https://insomnia.rest/download)
- [Java 17](https://www.oracle.com/br/java/technologies/downloads/#java17)
- [Spring Tool Suite 4](https://spring.io/tools)
- [Trello](https://trello.com/b/qRhzIPh5/projeto-final-database)
<br> <br> <br>

## 👥 AUTORES DO PROJETO ## 
- [Aurelio Lamela](https://github.com/netolamela)
- [José Lourenço](https://github.com/joselourencocm)
- [Lucas Caiafa](https://github.com/lucascaiafa00)
- [Luciana Brand](https://github.com/lucianabrand)
- [Sabrina Pereira](https://github.com/sabrinapereiry)
