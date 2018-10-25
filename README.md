# iFood Backend Basic Test

Este é um projeto base para demonstração de um micro-serviço RESTful fictício.

Recomenda-se gastar entre 4 a 6 horas para realizar essas tarefas. As duas primeiras são as mais importantes, 
enquanto que as duas últimas são opcionais, para quem conseguir terminar antes do previsto.

Esse projeto foi iniciado a partir do [Spring Initializr][SpringInitializr]. É o boilerplate padrão do Spring  
com [Spring Boot][SpringBoot] 1.5.2 (torna o micro-serviço executável, sem necessidade de deploy para um servidor).

Crie um *fork* deste repositório e siga as tarefas abaixo.

## Tarefas

### 1. Crie um endpoint para retornar informações sobre o serviço

Para testar rapidamente se nosso serviço está funcionando, crie na classe `About` um endpoint `GET /about` que retorna
uma mensagem confirmando que nosso serviço está funcionando e recebendo requisições.


### 2. Integração com OpenWeatherMap

Vamos usar o OpenWeatherMap para obter informações do clima de uma cidade.

Primeiro, faça um registro rápido no [OpenWeatherMaps][OpenWeather] para obter uma API Key.

Agora, crie um endpoint `GET /weather` no nosso micro-serviço que aceite o parâmetro `city` e retorne os detalhes do
tempo na cidade (ensolarado, nublado, etc) e detalhes meteorológicos (temperatura, pressão, etc), utilizando 
requisições HTTP para o OpenWeatherMaps.

Estruture a resposta da maneira que achar mais organizada. 

Sugestão: Pesquise alguma biblioteca de requisições HTTP robusta, que trate erros e serialização/deserialização 
automaticamente. 


### 3. Cache

Pesquise alguma biblioteca de cache para tornar nosso micro-serviço mais rápido.

Adicione cache na chamada ao servidor do OpenWeatherMaps.


### 4. Tolerância a falhas

Se por algum motivo o serviço do OpenWeatherMaps estiver indisponível, não deveríamos deixar que nosso micro-serviço 
seja afetado.

Para isso, podemos adicionar uma biblioteca de controle de falhas chamada Hystrix.

Nessa etapa, adiciona e configure o [Hystrix][HystrixRepo] para tornar nosso micro-serviço mais robusto.

Em caso de falha... o que poderia acontecer para que o nosso endpoint não retorne apenas uma resposta de erro genérica?


[OpenWeather]: https://openweathermap.org/appid
[FeignRepo]: https://github.com/OpenFeign/feign
[HystrixRepo]: https://github.com/Netflix/Hystrix
[SpringInitializr]: https://start.spring.io/
[SpringBoot]: https://docs.spring.io/spring-boot/docs/1.5.2.RELEASE/reference/htmlsingle/#getting-started

## Notas do Desenvolvedor

Foram feitos dois commits, onde o primeiro satisfaz as três primeiras tarefas e o segundo adiciona o suporte ao Hystrix.

### 1º Commit

O "error handling" foi feito de maneira manual onde todo erro que ocorresse era passado para o usuário da API, seja erro de serviço ou propagado da chamada ao OpenWeatherAPI.

O cache utilizado foi a anotação do spring, onde o retorno é salvo para um mesmo request, sendo assim eliminando a necessidade de realizar uma nova requisição igual.

Token para acesso ao OpenWeather foi externalizada para o applications.properties como uma maneira de não ficar preso a um único ambiente.

### 2º Commit

A quarta tarefa foi implementada, que era a tolerância a falhas utilizando o Hystrix.

Como o cache foi feito utilizando a anotação do Spring, preferi implementar o Hystrix de maneira manual, utilizando a dependência Hystrix-Core ao invés do seu encapsulamento do Spring Cloud.

Modificar para o Hystrix do Spring Cloud traria a mesma funcionalidade mas seria muito menos verboso.

A utilização do Hystrix fez com que os erros do serviço parassem de ser propagados para o usuário, assim com a implementação de um Fallback será feita uma requisição à uma outra API com funcionalidades parecidas para não deixar o cliente com uma mensagem de erro.

A implementação dos Tokens para essas duas APIs(OpenWeather e Weatherbit no Fallback) foi modificada para variáveis de ambiente, assim para rodar é necessário as variáveis "OpenWeatherAppid" e "KeyWeatherbit" configuradas com seus respectivos Tokens ao iniciar a aplicação.

Testes implementados verificam erros e possíveis retornos. Para passar em todos os testes as variáveis de ambiente com os Tokens devem ser configuradas novamente, o teste "verifiesTheReturnOfAbout"em "DemoApplicationTests" faz uma chamada as APIs como teste de integração.

Caso seja necessário, meus tokens são das duas APIs estão no Dockerfile no container de build e no container de Run, ambos possuem categoria gratuita de utilização.

WeatherBit é uma API que será o Fallback da OpenWeather, ou seja, só será chamada caso a primeira falhe, assim concluímos que ela não é ótima, não é tão rápida e não possui todos os dados necessários, mas retorna o suficiente para não deixar o usuário com erros.