# ${applicationName}

Servicio generado a partir de `platform-archetype` con estructura hexagonal organizada por capas y adaptadores.

## Estructura base generada

```text
${basePackage}
├─ application
│  ├─ ports
│  │  ├─ in
│  │  └─ out
│  └─ service
├─ domain
│  ├─ enum
│  ├─ exception
│  └─ event
└─ infraestructure
   ├─ adapter
   │  ├─ web
   │  │  ├─ controller
   │  │  ├─ dto
   │  │  │  ├─ request
   │  │  │  └─ response
   │  │  └─ mapper
   │  ├─ persistence
   │  │  ├─ entity
   │  │  ├─ repository
   │  │  └─ mapper
   │  └─ messages
   │     ├─ producer
   │     │  ├─ dto
   │     │  ├─ mapper
   │     │  └─ publisher
   │     └─ consumer
   │        ├─ dto
   │        ├─ mapper
   │        └─ listener
   └─ configuration
```

## Requisitos

- Java 21
- Maven 3.9+
- Docker
- PostgreSQL

## Levantar base local

```bash
docker compose up -d
```

## Ejecutar aplicación

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```
