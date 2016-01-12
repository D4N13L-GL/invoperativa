# Introduction #

En esta pagina se describe como configurar el entorno de desarrollo para
el proyecto de operativa.


# Dependencias #
> ## Para correr el proyecto es necesario: ##
    1. MySQL
    1. Apache Tomcat ( en lo posible 5.5 o 6, el 7 no)
    1. Eclipse para J2EE
    1. SubEclipse (SVN para Eclipse)


# Configuracion del Proyecto #

  * Una vez instaladas todas las herramientas, abrimos el eclipse y     vamos a **File** -> **New** -> **Other** -> **SVN** -> **Checkout proyect...**.      Esto nos bajara el proyecto a nuestra maquina

  * Bajo la carpeta **Java Resources**->**Resources** encontramos en invoperativa.sql y lo corremos para crear la base

  * Luego vamos a **Window**-> **Preferences** -> desplegamos **Server** -> **Runtime Enviroment** y ahi ubicamos donde instalamos el **tomcat**

  * Luego debemos configurar el archivo xml de hibernate ingresando la informacion de nuestra base de datos (user, pass).

  * Una vez que hicimos esto ponemos click-derecho en el proyecto y vamos a **Run as...**-> **Run on server** y seleccionamso el Tomcat

  * Si tira algun error vamos a el buildpath y verificamos en la solapa   **Libraries** que nos puede tirar un error en el JRE, ponemos click derecho y seleccionamos el nuestro

  * Una vez que tenes todo esto ya podes codear como un champion