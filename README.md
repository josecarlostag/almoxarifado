# almoxarifado
Sintema para controlar o cadastro de equipamentos, possibilita um registro centralizado da disponibilidade de 
equipamento e informações de cada equipamento entregues aos respectivos usuários.

Ao exceutar pela primeira vez faça a seguinte configuração no persistence.xml.

1.	Crie o banco de dados almoxarifado no mysql.
2.	Configura o acesso ao seu SGDB informando o seu login e senha.
3.	No arquivo libera a execução do javax para criar as tabela e gerar a carga inicial dos dados.
4.	Acesse http://localhost:8080/almoxarifado/index.xhtml
5.	No momento não precisa informa login e senha, as demais telas estão com acesso liberado.  

<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.1"
xmlns="http://xmlns.jcp.org/xml/ns/persistence" 
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence 
http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd">

	<persistence-unit name="AlmoxarifadoPU">

	<provider>org.hibernate.ejb.HibernatePersistence</provider>

	<properties>
			
	<property name="javax.persistence.jdbc.url"value="jdbc:mysql://localhost/almoxarifado"/>
	<property name="javax.persistence.jdbc.user" value="root" />
	<property name="javax.persistence.jdbc.password" value="root" />
	<property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver" />
<!--                    	
	<property name="javax.persistence.schema-generation.database.action" value="drop-and-create" />
      	<property name="javax.persistence.schema-generation.create-source" value="metadata" />
 	<property name="javax.persistence.sql-load-script-source" value="META-INF/sql/dados-iniciais.sql"/> 
-->
	<!--  propriedades do hibernate -->
   	<property name="hibernate.dialect" value="org.hibernate.dialect.MySQL5InnoDBDialect" />		  		
	<property name="hibernate.show_sql" value="true" />
	<property name="hibernate.format_sql" value="true" />
			
	<property name="hibernate.hbm2ddl.auto" value="update" /> 
	</properties>

	</persistence-unit>
</persistence>

Se inicializou  e deu a carga inicial com sucesso, desabilita a criação das tabela e a carga inicial.

