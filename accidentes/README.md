## Ejecución del proyecto descargado

```
mysql -u root -p    [pedirá la contraseña de MySQL]
mysql> create database accidentes;
mysql> create user si@localhost identified by "si";
mysql> grant all privileges on accidentes.* to si@localhost;

mvn spring-boot:run
```

Arranca servidor de desarrollo en (http:localhost:8080)