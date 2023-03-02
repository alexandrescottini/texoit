# api
API RESTful que possibilita a leitura da lista de indicados e vencedores
da categoria Pior Filme do Golden Raspberry Awards.

## Requisitos
- Git (para clonar o projeto)
- IDE (testado com IntelliJ IDEA 2022.3.2 CE)
- Maven (incluido na IDE)
- JDK 8 (testado com jdk-8u202)

## Configurações
- Por padrão a api está configurada com o servlet.contextPath=/api
```sh
    # Context
    server.servlet.contextPath=/api
```
- Para alterar as configurações do banco de dados com URL, usuário, senha e url do console, e ativar/desativar o console, abra o arquivo application.properties. A opções do banco H2 e as propriedades do datasource são exibidas como abaixo:
```sh
    # H2
    spring.h2.console.enabled=true
    spring.h2.console.path=/h2
    
    # Datasource
    spring.datasource.url=jdbc:h2:mem:testdb
    spring.datasource.username=sa
    spring.datasource.password=
    spring.datasource.driver-class-name=org.h2.Driver
    spring.jpa.hibernate.ddl-auto=update
```

## Para executar o projeto
Para executar o projeto, nenhuma instalação externa é necessária. Ao ser iniciada, a aplicação cria o banco de dados e o popula com os dados do arquivo movielist.csv, que se encontra em *src/main/resources/*.
1. Clone o repositório;
2. Importe o projeto como *Open as Project*;
3. Execute o comando Maven abaixo:
```sh
        $ mvn clean install -Dmaven.test.skip=true
```
4. Para iniciar a aplicação clique na classe Application com o botão direito e Run 'Application.main()'.

## EndPoints
Para ver a lista de chamadas REST disponíveis, seus parâmetros, códigos de resposta HTTP, e tipo de retorno, inicie a aplicação e acesse: http://localhost:8080/api/swagger-ui.html#/

## Testes
Para executar os testes clique com o botão direito na classe AppTest.java, clique em Run 'AppTest'.
