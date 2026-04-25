# Estándar de código

## 1. Principios
- Claridad antes que ingenio
- Consistencia antes que preferencia personal
- Simplicidad antes que magia
- Código reusable sin acoplamiento al negocio

## 2. Reglas generales
- Código en inglés
- Documentación en español
- Java 21
- Maven
- Spring Boot 4

## 3. Organización
Regla oficial: **package by feature**.

Ejemplo:

```text
com.company.orders.order

├─ application
├─ domain
├─ infrastructure

```

## 4. Nombres
- Clases: `PascalCase`
- Métodos y variables: `camelCase`
- Constantes: `UPPER_SNAKE_CASE`
- Paquetes: minúsculas

## 5. Inyección
Usar inyección por constructor. No usar field injection.

## 6. DTOs y entidades
- DTOs para entrada y salida
- Entidades para persistencia
- No devolver entidades desde controladores

## 7. Excepciones
Usar excepciones con significado:
- `BusinessException`
- `TechnicalException`
- `ResourceNotFoundException`

## 8. Logging
- Usar `slf4j`
- No loggear secretos
- Usar `INFO`, `WARN`, `ERROR`, `DEBUG` con criterio

## 9. BigDecimal
- No usar `double` para dinero
- Usar `BigDecimal`
- Comparar con `compareTo`

## 10. Fechas
Usar `java.time`:
- `Instant`
- `LocalDate`
- `LocalDateTime`
- `ZoneId`

## 11. Testing
- Unit tests para lógica aislada
- Integration tests para persistencia e infraestructura
- Testcontainers para PostgreSQL

## 12. Checklist previo a merge
- Compila
- Tests pasan
- Sin secretos
- Naming consistente
- Documentación actualizada si aplica
