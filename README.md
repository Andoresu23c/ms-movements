# ms-movements
## Descripción
Microservicio para la gestión de cuentas y movimientos utilizando Spring Boot, PostgreSQL y Docker.

## Características
- Registro de cuentas bancarias.
- Registro y actualización de movimientos (suma/reste al saldo).
- Validación de saldos disponibles.

## Requisitos
- Java 17
- Maven 3.8+
- Docker 20.10+
## Maneja la Arquitectura Limpia basada en n-capas
## Arquitectura
```plaintext
ms_movements/
│
├── controllers/       # Controladores que exponen los endpoints REST
│   ├── CuentaController.java
│   └── MovimientoController.java
│
├── dtos/              # Objetos de Transferencia de Datos 
│   ├── CuentaDTO.java
│   └── MovimientoDTO.java
│
├── mappers/           # Mappeo entre entidades y DTOs
│   ├── CuentaMapper.java
│   └── MovimientoMapper.java
│
├── models/            # Modelos/Entidades para la base de datos
│   ├── Cuenta.java
│   └── Movimiento.java
│
├── repositories/      # Interfaces para la capa de persistencia
│   ├── CuentaRepository.java
│   └── MovimientoRepository.java
│
└── services/          # Lógica de negocio
    ├── CuentaService.java
    ├── MovimientoService.java
    └── impl/
        ├── CuentaServiceImpl.java
        └── MovimientoServiceImpl.java
```
## Endpoints
1. /clientes
2. /cuentas
3. /movimientos
## Dockerización
A continuación se detallan unos pasos sencillos para poder inicializar el proyecto en docker y poder desplegarlo y consumir sus apis.
En el archivo docker-compose.yml se encuentra especificado como está configurada la inicialización de la imagen virtual tanto de la base de datos 
como de la aplicación y ambas se comunicaran. 

## Instalar apache maven en caso de no tener el archivo .jar con la snapshot
 1. Descargar la ultima versión de maven https://maven.apache.org/download.cgi
 2. Extrae el contenido del archivo .zip en una carpeta de tu elección, por ejemplo:
    ``` 
    C:\maven
    ```
 3. Configuración de Variables de Entorno:
 Abre Configuración avanzada del sistema > Variables de entorno.
    Agrega una nueva variable:
     Nombre: MAVEN_HOME
    ``` 
    Valor: C:\maven\apache-maven-3.x.x
    ``` 
    Edita la variable PATH y agrega:  %MAVEN_HOME%\bin
4. Verificar si se instaló con: 
    ``` 
    mvn -version
    ``` 
5. Ejecutar el comando ``` mvn clean package ``` en dirección base del proyecto para poder crear el snapshot en la carpeta ```/target``` del mismo.

## Ejecutar el archivo docker-compose.yml
Luego de cumplir con el paso anterior el proyecto estará listo para poder ser dockerizado de la manera correcta.
1. Abrir una terminal o bash con la carpeta del proyecto.
2. Escribir el comando: ``` docker compose up --build ``` para generar tanto las imagenes como el contenedor de la base de datos en postgres 17.2 en el 5432 y el microservicio en el 8080.
3. El archivo ejecutará una imagen virtual de postgres y de JavaSDK17, que está configurado en el archivo ```Dockerfile```.

## Prueba de las apis en postman
1. Se tiene el archivo de postman en formato JSON para poder hacer prueba de todas las apis del proyecto

##Base de datos
El archivo .sql con la base de datos y su diagramación, datos de prueba e inserciones se detalla como ```BasedeDatos.sql```
