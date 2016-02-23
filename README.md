# Culqi Android SDK

El Culqi Android SDK hace sencillo aceptar tarjetas dentro de tu aplicación, usando un formulario web de pago.

## Requirimientos
Nuestro SDK es compatible con aplicaciones para Android 4.0 y superior.

## Integración

Tenemos una [guía](https://www.culqi.com/docs) que explica desde la creación de una venta, la instalación, la configuración , el procesamiento de la venta y más.

## Aplicación de ejemplo

Hay un aplicación de ejemplo en este repositorio:
- Culqi Ejemplo Android (Formulario Web) muestra una integración rápida usando el formulario web de pago de Culqi.

Hemos puesto a disposisición un servicio web en el entorno de integración para que puedan realizar pruebas en los dispositivos móviles, si todavía no tienes terminado el backend de la aplicación.

### Creación de una venta
Método | Entorno | URL |
---------- | ----------- | -----------
POST | Integración | https://integ-com.culqi.com/venta/movil |

Para crear una venta debes de enviar un JSON con la información de la venta:

### Parámetros de envío

EL JSON que vas a enviar debe de contener los siguientes parámetros.

Nombre | Parámetro | Descripción | Tipo | Tamaño Mínimo| Tamaño Máximo
--------- | --------- | ------- | ----------- | ----------- | -----------
Moneda | moneda | Código [ISO-4217](https://es.wikipedia.org/wiki/ISO_4217) de la Moneda de la venta . Ej: Nuevos Soles: PEN , Dólares: USD | N | 3 caracteres | 3 caracteres
Monto | monto | Monto de la venta, sin punto decimal Ej: 100.25 sería 10025 | N | 3 caracteres | 9 caracteres
Descripción | descripcion | Breve descripción del producto o servicio brindado. | AN | 5 caracteres | 80 caracteres
Correo Electrónico | correo_electronico | Dirección del correo electrónico del cliente. | AN | 5 caracteres | 50 caracteres
País | pais | Código [ISO-3166-1 Alfa 2](https://es.wikipedia.org/wiki/ISO_3166-1) del País del cliente. Ej. Perú : PE | A | 2 caracteres | 2 caracteres
Ciudad | ciudad | Ciudad del cliente. | A | 2 caracteres | 30 caracteres
Dirección | direccion | Dirección del cliente. | AN | 5 caracteres | 100 caracteres
Teléfono | telefono | Número de teléfono del cliente. | N | 5 caracteres  | 15 caracteres
ID Usuario | id_usuario_comercio | Identificador del usuario. | N | 2 caracteres | 15 caracteres
Nombres | nombres | Nombres del cliente. | A | 2 caracteres | 50 caracteres
Apellidos | apellidos | Apellidos del cliente. | A | 2 caracteres | 50 caracteres

### Parámetros de respuesta

Una vez enviada la información recibirás un JSON como respuesta:

Nombre | Parámetro | Descripción 
--------- | --------- | ------- 
Código Respuesta | respuesta | Código de respuesta de la creación de la venta, solo si es **"venta_registrada"** debes de mostrar el formulario de pago, de lo contrario ocurrirán errores visibles para el usuario.
Mensaje Respuesta | mensaje_respuesta | Respuesta detallada de la creación de la venta.
Información Venta | informacion_venta | Cadena cifrada que debes usar para cargar el formulario de Culqi.

Luego de haber creado la venta debes de cargar el formulario de pago y procesar la venta, siguiendo las indicaciones de la [documentación.](https://www.culqi.com/docs) Puedes usar el mismo servicio web para poder procesar la respuesta de Culqi.

### Procesamiento de la respuesta
Método | Entorno | URL |
---------- | ----------- | -----------
POST | Integración | https://integ-com.culqi.com/venta/respuesta |

Para descifrar la respuesta cifrada de Culqi debes de enviar un JSON con la respuesta.

### Parámetros de envío

EL JSON que vas a enviar debe de contener los siguientes parámetros.

Parámetro | Descripción |
---------- | ----------- | -----------
respuesta | Es la respuesta de Culqi cifrada.

Y obtendrás como respuesta:

### Parámetros de respuesta

EL JSON que vas a recibir contiene los siguientes parámetros.

Parámetro | Descripción |
---------- | ----------- | -----------
codigo_respuesta | Es el código de respuesta de la venta, puedes ver los códigos de respuesta en la [documentación.](https://www.culqi.com/docs)

## ¿Necesitas ayuda?

Estamos felices de resolver cualquier duda o pregunta que tengas.

Escribenos a soporte@culqi.com.

Respondemos todas tus dudas en máximo 24 horas.
