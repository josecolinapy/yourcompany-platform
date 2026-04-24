# Versionado

## Modelo oficial
Se usa **Semantic Versioning**.

Formato:
```text
MAJOR.MINOR.PATCH
```

Ejemplos:
- `1.0.0`
- `1.0.1`
- `1.1.0`

Pre-release:
- `1.0.0-rc1`
- `1.0.0-beta1`

## Cuándo subir versión

### PATCH
Fixes backward-compatible.

### MINOR
Nueva funcionalidad backward-compatible.

### MAJOR
Cambio incompatible o breaking change.

## Reglas de release
Antes de publicar una release estable se debe validar:

- `mvn clean verify`
- sample service funcionando
- archetype instalando
- proyecto generado compilando
- changelog actualizado
- release notes actualizadas

## Por qué se requiere changelog además de documentación estándar
La documentación estándar describe el sistema.  
El changelog describe su evolución y el impacto de cada release.
