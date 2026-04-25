# Guía de inicio rápido

## 1. Objetivo

Esta guía permite instalar, validar y entender rápidamente la plataforma reusable.

## 2. Requisitos previos

Necesitas:

- Java 21
- Maven 3.9+
- Docker
- Docker Compose

## 3. Validación rápida

### 3.1 Verificar herramientas

```bash
java -version
mvn -version
docker --version
docker compose version
```
# Uso del Maven Archetype

## 1. Objetivo

`platform-archetype` permite generar nuevos servicios alineados al estándar técnico de la plataforma.

## 2. Instalar el archetype localmente

```bash
mvn -pl platform-archetype clean install
```

## 3. Generar un nuevo servicio

```bash
mvn archetype:generate   -DarchetypeGroupId=com.example.platform   -DarchetypeArtifactId=platform-archetype   -DarchetypeVersion=1.0.0.19   -DgroupId=com.acme.orders   -DartifactId=orders-service   -Dversion=1.0.0.19   -Dpackage=com.acme.orders   -DbasePackage=com.acme.orders   -DapplicationName="Orders Service"   -DapplicationClassName=OrdersServiceApplication   -DserviceName=orders-service   -DplatformVersion=1.0.0.19
```

## 4. Validar el proyecto generado

```bash
cd orders-service
mvn clean verify
```

## 5. Qué incluye el proyecto generado

- `pom.xml` alineado al parent de la plataforma
- dependencias base
- estructura package by feature
- CRUD mínimo
- configuración `application.yml`
- migración Flyway inicial
- `Dockerfile`
- `docker-compose.yml`
- pruebas base
