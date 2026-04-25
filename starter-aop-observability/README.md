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