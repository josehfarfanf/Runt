# Reto Banco Runt 2.0
Para solucionar el reto propuesto se utilizó una base de datos H2 (sin usuario ni contraseña)
El proyecto se dividió en los siguientes paquetes:
MODEL: con las entidades a usar
DAO: Capa de acceso a datos
SERVICE: Capa de lógica de negocio 
CONTROLLER: Contiene la definición de los distintos endpoint de los recursos
EXCEPTIONS: Clases utilizadas para contro de excepciones

Los repositorios de datos se implementaron heredandolos de JpaRepository. 
El esquema de la base de datos se genera automáticamente y se adjunta un script para insertar registros de prueba.

Servicios Solicitados:
1.- GET: localhost:8090/usuario/compania/{idCompania}
2.- POST: localhost:8090/departamento  
    PUT:  localhost:8090/departamento/{id}  Body:Departamento
3.- PATCH localhost:8090/usuario/{id}   Body:Departamento
4.- DELETE localhost:8090/usuario/{id}



Data base: jdbc:h2:file:~/testdb

Data base console: http://localhost:8090/h2-console

URLs:
localhost:8090/usuario
localhost:8090/compania
localhost:8090/departamento


