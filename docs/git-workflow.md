# Flujo de Git

## Branches oficiales
- `main`
- `develop`
- `feature/*`
- `fix/*`
- `hotfix/*`
- `release/*`

## Ejemplos
```text
feature/add-mail-template-support
fix/platform-exception-handler-wiring
release/1.0.0-rc1
```

## Commits
Usar **Conventional Commits**.

Formato:

```text
type(scope): summary
```

Ejemplos:
```text
feat(mail): add thymeleaf templated mail support
fix(exception-handler): import controller advice through auto-configuration
docs(readme): update sample service usage
```

## Flujo recomendado
1. crear issue
2. crear branch
3. implementar
4. agregar tests
5. correr `mvn clean verify`
6. abrir PR
7. revisión
8. merge
