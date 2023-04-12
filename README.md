# API Rest para cadastro de incidentes.

## 📖  Descrição

Esse é o projeto de uma API Rest para gestão de incidentes. Ela armazena o cadastro de usuários, que podem ser solicitantes e atendentes, e em seguidas os mesmos podem abrir incidentes e atendê-los. Essa API veio de uma ideia de um trabalho acadêmico e posteriormente resolvi evoluí-la para algo mais robusto e funcional, utilizando de boas práticas e padrões utilizados nas empresas atualmente. 
<br/>

## 🛠️ Funcionalidades

- Armazenar dados dos usuários 
- Armazenar incidentes
- Os usuários podem cadastrar incidentes
- Os usuários podem atender algum incidente
- Acompanhar os incidentes
- Finalizar incidentes caso tenham resolvido
- Sistema de login e permissões
<br/>

## 📡 Tecnologias utilizadas 
<div align="center"> 
<img align="center" alt="JAVA"  height="60" width="80"
src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original-wordmark.svg">
<img align="center" alt="Spring"  height="60" width="80"
src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg">
<img align="center" alt="PostgreSQl"  height="60" width="80"
src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original-wordmark.svg">
<img align="center" alt="Flyway"  height="60" width="80"
src="https://repository-images.githubusercontent.com/7170058/039c7180-7edc-11e9-8c29-0cbc606876c0">
<img align="center" alt="Aws"  height="60" width="80"
src="https://www.svgrepo.com/show/331300/aws.svg">
<img align="center" alt="JWT"  height="50" width="80"
src="https://thekenyandev.com/static/ba180df420dbaffd7405a0f65764feab/cover.png">
</div>
<br/><br/>

## ⏳ Inicialização

Esse projeto foi desenvolvido em ambiente Windows 10 e as tecnologias citadas anteriormente. Caso você utilize outro sistema operacional, a configuração inicial do projeto pode ser um pouco diferente, por isso sugerimos que você acesse os links indicados abaixo:

A preparação do ambiente consiste em instalar as tecnologias citadas anteriormente de acordo com seu sistema operacional.

Para instalar o JDK para utilizar o Java 17, acesse: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html

Nesse projeto usamos o Maven como gerenciador de dependências, siga as instruções na documentação oficial: https://maven.apache.org/install.html

Para utilizar o Spring (versão 3.0.2) basta adicionar as dependências no projeto, como já estão no arquivo pom.xml. 
Mais informações consulte a documentação: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/

Para instalar o banco de dados PostgreSQl 14.7: https://www.enterprisedb.com/downloads/postgres-postgresql-downloads

Se precisar de algo mais simples existem diversos vídeos rápidos que ajudam a instalar essas tecnologias.

Clone do código fonte: Faça o clone da branch JwtSecurity, que é a última versão já com a implementação do login utilizando JWT.

Após instalar todas as ferramentas acima, e feito o clone da aplicação na sua máquina, já é possível rodar a aplicação.
Com a ajuda de alguma IDE (Como IntelliJ ou Eclipse), faça o build da aplicação para resolver todas as dependências e em seguida já pode rodar.
Após surgir a mensagem com "Tomcat Started" indica que a aplicação está up.

<br/>

## 🔮 Implementações futuras
1. Implementar versionamento das tabelas, para poder ver histórico de alterações e quem as fez.

2. Implementar Exception Handle seguindo os padrões mais atuais.

3. Implementar anexo de imagens aos incidentes.

4. Implementar a containerização da aplicação, para facilitar a execução usando o docker.

<br/>

## 🤵🤵‍♀️ Colaboradores

Além do autor do projeto, Marcos Oliveira, temos também Otávio Valadão como um dos colaboradores do projeto.

<br/>

## 🔎 Status do Projeto

![Badge em Desenvolvimento](https://img.shields.io/badge/Status-Em%20Desenvolvimento-green)

<br/>

## 📑 Referências

Documentação oficial do Spring : https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/

Spring Tutorial : https://www.baeldung.com/spring-tutorial

Alura Cursos: https://www.alura.com.br/
