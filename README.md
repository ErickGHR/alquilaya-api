# AlquilaYa

Este proyecto es una API REST para llevar el control de vehículos, clientes, oficinas, alquileres y mantenimientos. El código está dividido en modelos, repositorios, servicios y controladores para que cada parte tenga una función clara.

También incluye los ejercicios de POO sobre la bicicleta, la validación del DNI y el manejo de errores con `try-catch` dentro del paquete `poo`.

## Requisitos

- Java 21.
- MySQL 8 o una versión compatible.
- Git, solamente si se va a clonar el repositorio.

No es necesario instalar Maven porque el proyecto incluye Maven Wrapper.

## Descargar y ejecutar en Windows

```powershell
git clone https://github.com/ErickGHR/alquilaya-api.git
cd alquilaya-api
.\run-local.ps1
```

El script pide la contraseña local de MySQL sin guardarla en GitHub. La base `alquilaya` se crea automáticamente si el usuario de MySQL tiene permiso.

Otra forma de iniciarlo es:

```powershell
$env:DB_USER="root"
$env:DB_PASSWORD="TU_CONTRASEÑA"
.\mvnw.cmd spring-boot:run
```

La API queda disponible en `http://localhost:8080`.

## Endpoints principales

```text
/api/rental-offices
/api/vehicles
/api/customers
/api/rentals
/api/maintenances
```

En todos se puede consultar, guardar, modificar y eliminar información.

## Orden recomendado para probar

1. Crear una oficina.
2. Crear un cliente.
3. Crear un vehículo asociado a la oficina.
4. Crear un alquiler con el vehículo y el cliente.
5. Consultar la lista de alquileres.

El precio total del alquiler lo calcula el servicio de acuerdo con la tarifa diaria y las fechas. También se revisa que no existan alquileres cruzados y que el vehículo no esté en mantenimiento.

En `postman/AlquilaYa.postman_collection.json` está la colección lista para importar y tomar las capturas de las pruebas.

## Pruebas automáticas

```powershell
.\mvnw.cmd test
```

Para detener la aplicación se presiona `Ctrl + C` en la terminal.
