# FAQ

## ¿La plataforma está pensada solo para microservicios?
No. También está diseñada para monolitos modulares.

## ¿Puedo usar solo algunos módulos?
Sí. Los módulos `common-*` y `starter-*` son reutilizables por separado.

## ¿El sample es una aplicación de negocio?
No. Es un servicio de validación técnica del wiring de la plataforma.

## ¿El archetype debe copiar exactamente al sample?
Debe mantener coherencia estructural con el baseline validado, pero no necesita replicar complejidad innecesaria.

## ¿RSA ya está soportado?
Sí. `common-crypto` incluye generación de llaves, PEM, cifrado/descifrado y firma/verificación RSA.
