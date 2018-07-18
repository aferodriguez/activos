# activos
API activos
Es necesario tener instalado Java 1.8, NodeJs, Angular-cli , una instancia de mysql 

y utilizar una herramienta de linea de comando

Es necesario descargar los archivos del repositorio y posterior a esto hacer lo 

siguiente

1 BACK-END.

Para ejecutar activos-backend se deben seguir las siguientes instrucciones:

1. ir a la carpeta activos-backend y buscar en la siguiente ruta:
   - activos-backend\target
El artefacto de nombre:
   - activos-backend-0.0.1-SNAPSHOT.jar

estando en la ruta "activos-backend\target" de la carpeta activos-backend, se debe 

ejecutar la siguiente instrucción:

java -jar activos-backend-0.0.1-SNAPSHOT.jar

Despues de esto quedara en ejecución el microservicio por el puerto 8080, dicho 

microservicio es autocontenido y utiliza un Tomcat embedido permitiendo que spring 

se encargue de la complejidad de manejar el servidor de aplicaciones.



2 FRONT-END

Para ejecutar activos-frontend es necesario tener instalado NodeJs, el cual vamos a 

utilizar para ejecutar la aplicación y para manejar dependencias, asi que hay que 

seguir los pasos:

1. Estando ubicados en la raiz de la carpeta activos-frontendd se debe ejecutar la 

instruccion:
	-npm install

La cual descargara las dependencias que necesita el proyecto para ser ejecutado

2. Despues de actualizar las dependencias se ejecuta el comando
	-ng serve
Que despliega la aplicación front-end  en el puerto 4200 



De los puntos de la prueba se planteo una solución que alcanzo a cubrir las 

siguientes necesidades:

1. Buscar por todos los activos
2. Buscar activos por tipo, fecha de compra y serial
3. Crear nuevos activos
4. Actualizar activos.
5. Modelo de datos.
6. Modelo de paquetes.
7. Diagrama de casos de uso
8. Documentación API Utilizando Swagger
9. Documentación JAVADOC
10. Respuestas en JSON.
11. Log con log4j
12. Cliente Javascrip construido con Angular (Con consulta GET)
13. Se versiono el codigo en github.
14. Se crearon validaciones de fecha compra y fecha de baja.
15. Se manejaron excepciones y se crearon dos propias para el negocio. 

ResourceNotFound Exception y FechaDeBajaException.
16. Se utilizaron los codigos http segun la operación.
17. Cada codigó http es regresado junto con un mensaje que describe el problema.

Entregables

1.  activos-backend-0.0.1-SNAPSHOT.jar  (Es la aplicación java)
2.  activos-frontendd (es la aplicación Angular)
3.  Instrucciones estan al inicio
4.  Modelo entidad Relación
5.  Archivo de pruebas de POSTMAN
