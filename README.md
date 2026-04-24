# yourcompany-platform
Plataforma reusable para crear servicios backend con **Java 21**, **Spring Boot 4**, **Maven**, **PostgreSQL**, **Flyway**, **observabilidad**, **testing** y **Maven Archetype**, pensada para acelerar nuevos proyectos y estandarizar calidad, estructura y capacidades técnicas transversales.

---

## Tabla de contenidos

- [Visión general](#visión-general)
- [Objetivos](#objetivos)
- [Arquitectura general](#arquitectura-general)
- [Componentes principales](#componentes-principales)
- [Stack tecnológico](#stack-tecnológico)
- [Documentación de la plataforma](#documentación-de-la-plataforma)
- [Guías rápidas](#guías-rápidas)
- [Cómo construir el repositorio](#cómo-construir-el-repositorio)
- [Cómo ejecutar el servicio de ejemplo](#cómo-ejecutar-el-servicio-de-ejemplo)
- [Cómo generar un nuevo servicio con el archetype](#cómo-generar-un-nuevo-servicio-con-el-archetype)
- [Estándares y gobierno técnico](#estándares-y-gobierno-técnico)
- [Versionado y changelogs](#versionado-y-changelogs)
- [Contribuciones](#contribuciones)
- [Licencia](#licencia)

---

## Visión general

Esta plataforma existe para evitar que cada nuevo servicio backend tenga que reconstruir desde cero la misma base técnica.

La idea central es transformar decisiones repetitivas en activos reutilizables:

- Compilación y manejo de dependencias con Maven
- Dependencias y versiones
- Persistencia base
- Migraciones
- Manejo de errores
- Observabilidad
- Seguridad base
- AOP y anotaciones enterprise
- Testing
- Generación de nuevos proyectos

---

## Objetivos

- acelerar la creación de nuevos servicios backend
- estandarizar estructura y convenciones
- reducir boilerplate técnico
- mejorar la consistencia entre proyectos
- centralizar capacidades transversales
- permitir evolución controlada del stack
- ofrecer una base corporativa reusable y gobernable

---

## Arquitectura general

La plataforma está organizada en torno a estas capas:

- **`platform-parent`**: build, plugins, calidad y testing
- **`platform-bom`**: versiones de dependencias
- **`common-*`**: piezas técnicas reutilizables
- **`starter-*`**: capacidades completas listas para consumir
- **`platform-archetype`**: generación de nuevos servicios

---

## Componentes principales

### Base de build
- `platform-parent`
- `platform-bom`

### Commons
- `common-core`
- `common-errors`
- `common-web`
- `common-pagination`
- `common-validation`
- `common-logging`
- `common-observability`
- `common-security-model`
- `common-jpa`
- `common-test`
- `common-crypto`
- `common-text`
- `common-decimal`
- `common-datetime`
- `common-aop`

### Starters
- `starter-web`
- `starter-exception-handler`
- `starter-validation`
- `starter-jpa-postgresql`
- `starter-flyway`
- `starter-actuator`
- `starter-observability`
- `starter-openapi`
- `starter-security`
- `starter-rest-client`
- `starter-test`
- `starter-aop-observability`

---

## Stack tecnológico

- **Java 21**
- **Spring Boot 4**
- **Spring Cloud** compatible
- **Maven**
- **PostgreSQL**
- **Flyway**
- **Spring Data JPA**
- **Actuator**
- **Micrometer**
- **Prometheus**
- **Spring Security**
- **OpenAPI**
- **Testcontainers**
- **Spring AOP**

---

## Documentación de la plataforma

- [Getting Started](docs/getting-started.md)
- [FAQ](docs/faq.md)
- [Template de Release Notes](docs/release-notes-template.md)

### Estándares y estrategia

- [Estándar de Código](docs/coding-standard.md)
- [Workflow de Git](docs/git-workflow.md)
- [Versionado](docs/versioning.md)

### Guía funcional de la plataforma
- [Guía de módulos con explicación y ejemplos de uso](docs/guia-de-modulos.md)

### Templates
- [Template de changelog por componente](docs/templates/component-changelog-template.md)

---

---

## Guías rápidas

### Quiero entender la plataforma primero
Empieza por:
1. [Getting Started](docs/getting-started.md)
2. [Guía de módulos](docs/guia-de-modulos.md)
3. [Versionado](docs/versioning.md)


### Quiero entender estándares y gobierno
Ve a:
1. [Estándar de Código](docs/coding-standard.md)
2. [Workflow de Git](docs/git-workflow.md)
3. [Gestión de Issues](docs/issue-management.md)
4. [CONTRIBUTING.md](CONTRIBUTING.md)

### Quiero revisar capacidades transversales
Ve a:
- [common-crypto](common-crypto/README.md)
- [starter-aop-observability](starter-aop-observability/README.md)

---

## Cómo construir el repositorio

### Build completo
```bash
mvn clean verify
```

### Build por bloques

#### Parent y BOM
```bash
mvn -pl platform-parent,platform-bom clean install
```

#### Commons
```bash
mvn -pl common-core,common-errors,common-web,common-pagination,common-validation,common-logging,common-observability,common-security,common-jpa,common-test,common-crypto,common-aop -am clean install
```

#### Starters
```bash
mvn -pl starter-web,starter-exception-handler,starter-validation,starter-jpa-postgresql,starter-flyway,starter-actuator,starter-observability,starter-openapi,starter-security,starter-rest-client,starter-test,starter-aop-observability -am clean install
```

---

## Cómo ejecutar el servicio de ejemplo

### Con Docker Compose raíz
```bash
docker compose up -d
```

### O usando el compose del sample
```bash
docker compose -f sample-crud-service/docker-compose.yml up -d
```

### Levantar la aplicación
```bash
mvn -pl sample-crud-service spring-boot:run -Dspring-boot.run.profiles=local
```

### Validar endpoints
- `http://localhost:8080/actuator/health`
- `http://localhost:8080/actuator/prometheus`
- `http://localhost:8080/swagger-ui.html`
- `http://localhost:8080/api/v1/items`

Más detalle en:

- [Getting Started](docs/getting-started.md)

---

## Cómo generar un nuevo servicio con el archetype

### Instalar el archetype localmente
```bash
mvn -pl platform-archetype clean install
```

### Generar un proyecto nuevo
```bash
mvn archetype:generate \
  -DarchetypeGroupId=com.example.platform \
  -DarchetypeArtifactId=platform-archetype \
  -DarchetypeVersion=1.0.0-SNAPSHOT \
  -DgroupId=com.acme.orders \
  -DartifactId=orders-service \
  -Dversion=1.0.0-SNAPSHOT \
  -Dpackage=com.acme.orders \
  -DbasePackage=com.acme.orders \
  -DapplicationName="Orders Service" \
  -DapplicationClassName=OrdersServiceApplication \
  -DserviceName=orders-service \
  -DplatformVersion=1.0.0-SNAPSHOT
```

Más detalle en:

- [platform-archetype](platform-archetype/README.md)


---

## Estándares y gobierno técnico

Consulta estos documentos como referencia oficial:

- [Estándar de Código](docs/coding-standard.md)
- [Workflow de Git](docs/git-workflow.md)
- [Gestión de Issues](docs/issue-management.md)
- [Versionado](docs/versioning.md)
- [CONTRIBUTING.md](CONTRIBUTING.md)
- [SECURITY.md](SECURITY.md)


---

## Versionado y changelogs

### Política de versionado
- [docs/versioning.md](docs/versioning.md)




## Contribuciones

Las contribuciones son bienvenidas, pero deben seguir el estándar del repositorio.

Antes de contribuir, revisa:
- [CONTRIBUTING.md](CONTRIBUTING.md)
- [Estándar de Código](docs/coding-standard.md)
- [Workflow de Git](docs/git-workflow.md)
- [Gestión de Issues](docs/issue-management.md)

---

## Licencia

Consulta:
- [LICENSE](LICENSE)

---

## Cierre

Esta plataforma no busca reemplazar el diseño de cada sistema. Busca dejar resuelta una base técnica común para que los equipos dejen de reconstruir constantemente la misma infraestructura de aplicación.

Si quieres entender la plataforma de punta a punta, la mejor ruta es:

1. [Getting Started](docs/getting-started.md)
2. [Guía de módulos](docs/guia-de-modulos.md)
3. [Versionado](docs/versioning.md)





