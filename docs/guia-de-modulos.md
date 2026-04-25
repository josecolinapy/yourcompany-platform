# Guía de módulos y ejemplos de uso

Esta guía resume **qué hace cada proyecto del repositorio**, **cuándo usarlo** y **cómo consumirlo** con un ejemplo corto.

> Regla de lectura:
> - `platform-*` define build y gobierno técnico
> - `common-*` define capacidades reutilizables puras
> - `starter-*` integra librerías externas y autoconfiguración
> - `platform-archetype` genera servicios nuevos


## `common-core`

**Tipo:** Utilidades base

**Qué resuelve:** Contiene utilidades técnicas pequeñas y neutras como reloj de aplicación, generación de identificadores y normalización básica.

**Cuándo usarlo:** Usa `IdentifierGenerator` para generar UUID o `ApplicationClock` para abstraer el tiempo.

**Ejemplo de uso:**

```xml
<dependency>
    <groupId>com.example.platform</groupId>
    <artifactId>common-core</artifactId>
</dependency>
```


## `common-crypto`

**Tipo:** Criptografía reusable

**Qué resuelve:** Incluye hashing, HMAC, AES/GCM, PEM y soporte RSA para generación de llaves, cifrado y firma.

**Cuándo usarlo:** Genera un `KeyPair` con `RsaKeyPairSupport`, firma con `RsaSignatureSupport` y cifra con `AesGcmSupport`.

**Ejemplo de uso:**

```java
KeyPair keyPair = RsaKeyPairSupport.generate2048();
String signature = RsaSignatureSupport.signToBase64(
        keyPair.getPrivate(),
        "payload".getBytes(StandardCharsets.UTF_8)
);
boolean valid = RsaSignatureSupport.verifyFromBase64(
        keyPair.getPublic(),
        "payload".getBytes(StandardCharsets.UTF_8),
        signature
);
```


## `common-errors`

**Tipo:** Modelo de errores

**Qué resuelve:** Define la taxonomía estándar de excepciones y códigos de error.

**Cuándo usarlo:** Lanza `ResourceNotFoundException` o `BusinessException` desde servicios de aplicación.

**Ejemplo de uso:**

```xml
<dependency>
    <groupId>com.example.platform</groupId>
    <artifactId>common-errors</artifactId>
</dependency>
```


## `common-jpa`

**Tipo:** Base JPA

**Qué resuelve:** Provee entidades base con UUID, auditoría y soft delete opcional.

**Cuándo usarlo:** Extiende `AuditableEntity` en tus entidades persistentes.

**Ejemplo de uso:**

```xml
<dependency>
    <groupId>com.example.platform</groupId>
    <artifactId>common-jpa</artifactId>
</dependency>
```


## `common-logging`

**Tipo:** Soporte de logging

**Qué resuelve:** Centraliza constantes de MDC, correlation id y helpers de logging.

**Cuándo usarlo:** Usa `MdcSupport` para colocar correlation id durante una operación técnica.

**Ejemplo de uso:**

```xml
<dependency>
    <groupId>com.example.platform</groupId>
    <artifactId>common-logging</artifactId>
</dependency>
```


## `common-observability`

**Tipo:** Contratos de observabilidad

**Qué resuelve:** Define nombres y tags reutilizables para métricas y observaciones.

**Cuándo usarlo:** Usa `MetricNames` y `MetricTagSupport` al publicar métricas custom.

**Ejemplo de uso:**

```xml
<dependency>
    <groupId>com.example.platform</groupId>
    <artifactId>common-observability</artifactId>
</dependency>
```


## `common-pagination`

**Tipo:** Paginación

**Qué resuelve:** Provee DTOs y metadata para paginación homogénea.

**Cuándo usarlo:** Acepta `PageRequestDto` desde el controlador y devuelve `PageResponseDto<T>`.

**Ejemplo de uso:**

```xml
<dependency>
    <groupId>com.example.platform</groupId>
    <artifactId>common-pagination</artifactId>
</dependency>
```


## `common-security`

**Tipo:** Modelo de seguridad

**Qué resuelve:** Define contratos genéricos para usuario autenticado y permisos.

**Cuándo usarlo:** Usa `SecurityContextFacade` para obtener el usuario actual sin acoplarte al framework.

**Ejemplo de uso:**

```xml
<dependency>
    <groupId>com.example.platform</groupId>
    <artifactId>common-security</artifactId>
</dependency>
```


## `common-test`

**Tipo:** Soporte de pruebas

**Qué resuelve:** Agrupa factories, datos aleatorios y soporte Testcontainers para PostgreSQL.

**Cuándo usarlo:** Extiende `PostgreSqlContainerSupport` en tests de integración.

**Ejemplo de uso:**

```xml
<dependency>
    <groupId>com.example.platform</groupId>
    <artifactId>common-test</artifactId>
</dependency>
```

## `common-validation`

**Tipo:** Validaciones reutilizables

**Qué resuelve:** Agrupa anotaciones y validadores custom neutrales al negocio.

**Cuándo usarlo:** Anota DTOs con `@EnumValue` para validar valores de enums.

**Ejemplo de uso:**

```xml
<dependency>
    <groupId>com.example.platform</groupId>
    <artifactId>common-validation</artifactId>
</dependency>
```


## `common-web`

**Tipo:** Contratos web

**Qué resuelve:** Define respuestas API reutilizables para éxito y error.

**Cuándo usarlo:** Usa `ApiResponse<T>` o `ErrorResponse` en controladores y handlers.

**Ejemplo de uso:**

```xml
<dependency>
    <groupId>com.example.platform</groupId>
    <artifactId>common-web</artifactId>
</dependency>
```


## `platform-archetype`

**Tipo:** Archetype Maven

**Qué resuelve:** Genera nuevos servicios alineados al estándar de la plataforma.

**Cuándo usarlo:** Instálalo localmente y ejecuta `mvn archetype:generate` para crear un servicio nuevo.

**Ejemplo de uso:**

```bash
mvn -pl platform-archetype clean install
mvn archetype:generate -DarchetypeGroupId=com.example.platform -DarchetypeArtifactId=platform-archetype ...
```


## `platform-bom`

**Tipo:** BOM corporativo

**Qué resuelve:** Centraliza versiones de dependencias externas e internas para evitar deriva entre módulos.

**Cuándo usarlo:** Importa el BOM desde el parent o desde un consumidor que necesite alinear versiones.

**Ejemplo de uso:**

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.example.platform</groupId>
            <artifactId>platform-bom</artifactId>
            <version>1.0.0.19</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```


## `platform-parent`

**Tipo:** Parent Maven corporativo

**Qué resuelve:** Centraliza plugins, reglas de build, Java 21, cobertura y calidad.

**Cuándo usarlo:** Hereda este parent desde cualquier módulo o servicio para reutilizar la política de build.

**Ejemplo de uso:**

```xml
<parent>
    <groupId>com.example.platform</groupId>
    <artifactId>platform-parent</artifactId>
    <version>1.0.0.19</version>
</parent>
```

## `starter-actuator`

**Tipo:** Starter Actuator

**Qué resuelve:** Expone endpoints técnicos de salud, info y métricas.

**Cuándo usarlo:** Úsalo en cualquier servicio productivo para health checks y métricas.

**Ejemplo de uso:**

```xml
<dependency>
    <groupId>com.example.platform</groupId>
    <artifactId>starter-actuator</artifactId>
</dependency>
```


## `starter-exception-handler`

**Tipo:** Starter de manejo de excepciones

**Qué resuelve:** Registra el handler global de excepciones HTTP de la plataforma.

**Cuándo usarlo:** Inclúyelo para traducir `PlatformException` a respuestas HTTP consistentes.

**Ejemplo de uso:**

```xml
<dependency>
    <groupId>com.example.platform</groupId>
    <artifactId>starter-exception-handler</artifactId>
</dependency>
```


## `starter-flyway`

**Tipo:** Starter Flyway

**Qué resuelve:** Estandariza el uso de migraciones versionadas.

**Cuándo usarlo:** Coloca scripts en `src/main/resources/db/migration` y activa el starter.

**Ejemplo de uso:**

```xml
<dependency>
    <groupId>com.example.platform</groupId>
    <artifactId>starter-flyway</artifactId>
</dependency>
```

## `starter-jpa-postgresql`

**Tipo:** Starter JPA PostgreSQL

**Qué resuelve:** Configura el baseline de persistencia con JPA e integración PostgreSQL.

**Cuándo usarlo:** Inclúyelo en servicios que persistan entidades con Spring Data JPA.

**Ejemplo de uso:**

```xml
<dependency>
    <groupId>com.example.platform</groupId>
    <artifactId>starter-jpa-postgresql</artifactId>
</dependency>
```

## `starter-observability`

**Tipo:** Starter de observabilidad

**Qué resuelve:** Agrega filtro de correlation id y baseline para métricas.

**Cuándo usarlo:** Inclúyelo para tener `X-Correlation-Id` y logs correlacionados.

**Ejemplo de uso:**

```xml
<dependency>
    <groupId>com.example.platform</groupId>
    <artifactId>starter-observability</artifactId>
</dependency>
```


## `starter-openapi`

**Tipo:** Starter OpenAPI

**Qué resuelve:** Configura documentación Swagger/OpenAPI básica y reutilizable.

**Cuándo usarlo:** Abre `/swagger-ui.html` o `/swagger-ui/index.html` en el servicio consumidor.

**Ejemplo de uso:**

```xml
<dependency>
    <groupId>com.example.platform</groupId>
    <artifactId>starter-openapi</artifactId>
</dependency>
```


## `starter-rest-client`

**Tipo:** Starter RestClient

**Qué resuelve:** Provee un `RestClient` preconfigurado con timeouts base.

**Cuándo usarlo:** Inyecta `RestClient` y define tus llamadas salientes HTTP.

**Ejemplo de uso:**

```xml
<dependency>
    <groupId>com.example.platform</groupId>
    <artifactId>starter-rest-client</artifactId>
</dependency>
```


## `starter-security`

**Tipo:** Starter de seguridad

**Qué resuelve:** Provee seguridad base con Spring Security y OAuth2 Resource Server.

**Cuándo usarlo:** Úsalo en APIs protegidas que consumen JWT u opaque tokens.

**Ejemplo de uso:**

```xml
<dependency>
    <groupId>com.example.platform</groupId>
    <artifactId>starter-security</artifactId>
</dependency>
```

## `starter-test`

**Tipo:** Starter de testing

**Qué resuelve:** Agrupa dependencias de prueba y soporte Testcontainers reusable.

**Cuándo usarlo:** Decláralo con `scope test` en servicios consumidores.

**Ejemplo de uso:**

```xml
<dependency>
    <groupId>com.example.platform</groupId>
    <artifactId>starter-test</artifactId>
</dependency>
```


## `starter-validation`

**Tipo:** Starter de validación

**Qué resuelve:** Activa Bean Validation y reutiliza validadores comunes.

**Cuándo usarlo:** Úsalo junto a `@Valid` en DTOs de entrada.

**Ejemplo de uso:**

```xml
<dependency>
    <groupId>com.example.platform</groupId>
    <artifactId>starter-validation</artifactId>
</dependency>
```


## `starter-web`

**Tipo:** Starter web

**Qué resuelve:** Provee el baseline HTTP MVC del proyecto.

**Cuándo usarlo:** Agrega la dependencia al servicio para exponer controladores REST.

**Ejemplo de uso:**

```xml
<dependency>
    <groupId>com.example.platform</groupId>
    <artifactId>starter-web</artifactId>
</dependency>
```


## `common-aop`

**Tipo:** Anotaciones reutilizables para AOP

**Qué resuelve:** Define anotaciones comunes para logging, medición de tiempo, auditoría y trazabilidad sin acoplarlas a un `@Aspect` concreto.

**Cuándo usarlo:** Cuando quieras marcar métodos con semántica transversal reutilizable.

**Ejemplo de uso:**

```java
@Logged(logArguments = true)
@Timed(value = "item.create", warnThresholdMs = 500)
@Auditable(action = "CREATE_ITEM", resource = "ITEM")
public ItemResponse create(CreateItemRequest request) {
    ...
}
```

## `starter-aop-observability`

**Tipo:** Starter reusable de AOP

**Qué resuelve:** Activa `@Aspect` para interpretar `@Logged`, `@Timed`, `@Auditable` y `@Traced`, registrar logs, medir tiempos y publicar eventos de auditoría desacoplados.

**Cuándo usarlo:** Cuando quieras evitar logging manual repetitivo y estandarizar instrumentación de métodos.

**Ejemplo de dependencia:**

```xml
<dependency>
    <groupId>com.example.platform</groupId>
    <artifactId>starter-aop-observability</artifactId>
</dependency>
```

**Ejemplo de propiedades:**

```yaml
platform:
  aop:
    enabled: true
    metrics-enabled: true
    audit-enabled: true
    trace-enabled: true
```

**Ejemplo de uso:**

```java
@Logged(logArguments = true, logResult = false)
@Timed(value = "mail.send", warnThresholdMs = 1000)
public MailSendResult send(MailMessageRequest request) {
    ...
}
```
