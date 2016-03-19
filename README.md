# logistics-walmart

O projeto foi criado com as seguintes ferramentas e o motivo do uso:

- Maven: Gerenciador de projeto mais utilizado

- Spring Boot: Utilizado pela facilidade de iniciar o sistema sem a necessidade de um servidor de aplicação (o tomcat é embutido) e pelo uso do Spring

- PowerMock e EasyMock: Ferramentas de testes simples para criar diversões cenários.

- Oracle Database: Utilizado pois é o banco de dados que tenho mais experiência, porém para o tamanho do projeto recomendaria o uso do MongoDB.


#Para executar o programa, é só seguir os passos abaixo:

- Clone o projeto do git: 

	git clone https://github.com/tucs-nash/logistics-walmart.git

- Importar banco de dados Oracle usando o arquivo ROUTE_DUMP.DMP dentro da pasta dump do projeto

	impdp user/pass@db10g schemas=walmart_logistics dumpfile=ROUTE_DUMP.DMP logfile=impdp.log

- Adicionar o driver do oracle no projeto

	mvn install:install-file -Dfile={pastadoprojeto}/lib/ojdbc.jar} -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0 -Dpackaging=jar

- Instalar o projeto usando maven:

	mvn clean install

- Rodar o projeto

	java -jar target/com.walmart.logistics.api-0.0.1-SNAPSHOT.jar

# Links Web Service
 Adicionar Mapa
 	
 	POST /addMap
 
 	JSON: {"name": "map1", "routes" : [{"routeFrom":"A","routeTo":"B","distance": 10},{"routeFrom":"B","routeTo":"D","distance": 15},{"routeFrom":"A","routeTo":"C","distance": 20},{   "routeFrom":"C","routeTo":"D","distance": 30},{"routeFrom":"B","routeTo":"E","distance": 50},{    "routeFrom":"D", "routeTo":"E","distance": 30}]}
 
 	Respostas: Sucesso= "OK: Map created successfully", Erro= "ERROR: Map already registered"
 
 Melhor Rota
 
 	GET bestRoute/test?routeFrom=A&routeTo=D&engine=10&litresPrice=2.5
 	
 	Resposta: {"route": ["A","B","D"],"distance": 25,"amount": 6.25}	