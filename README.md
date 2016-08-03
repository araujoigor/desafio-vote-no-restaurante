# Desafio
Você deve criar um projeto no seu git-hub chamado vote-no-restaurante e subir o código fonte.

## Requisitos Técnicos:
- Java (opcionalmente pode escrever os testes de unidade em JUnit, Spring Test) 
- JPA 
- Spring MVC, vRaptor, Struts 2, ou Play Framework. 
- Bancos de Dados (Relacional) em Memória (HyperSQL (HSQLDB), Derby ou Similar) 
- Na View utilizar JSP ou Velocity, ou Fremarker. JavaScript e CSS (opcionalmente pode utilizar frameworks de CSS e Javascript: Bootstrap, AngularJS, Backbone, 960, etc.).

A ideia é basicamente a seguinte:

Ao entrar na página inicial em http://localhost:8080/vote-no-restaurante, você deverá exibir dois restaurantes para o usuário e perguntar qual deles ele gosta mais. 

Digamos que o usuário clique no restaurante “Mc Donalds”. Você então deverá computar o voto dele e perguntar sobre outros restaurantes (Subway, Outback, etc...).

Você deverá ter apenas 5 restaurantes diferentes em seu banco de dados. Depois de computar todos os votos necessários, você deverá exibir dois campos para que o usuário preencha: um para o e-mail e outro para o nome dele.

Ao confirmar, você deverá exibir o ranking geral dos restaurantes considerando todos os votos inclusive dos outros usuário, e o ranking dele.

Observação: a descrição é breve propositalmente para que possamos avaliar sua criatividade.
