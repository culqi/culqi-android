# Culqi Android

[![Latest Stable Version](https://poser.pugx.org/culqi/culqi-php/v/stable)](https://packagist.org/packages/culqi/culqi-php)
[![Total Downloads](https://poser.pugx.org/culqi/culqi-php/downloads)](https://packagist.org/packages/culqi/culqi-php)
[![License](https://poser.pugx.org/culqi/culqi-php/license)](https://packagist.org/packages/culqi/culqi-php)

Biblioteca Android oficial de Culqi, pagos simples en tu sitio web.

> **Importante**: Hemos descontinuado el soporte a la versión 1.0 de Culqi API para centrarnos en la nueva versión. Si estabas trabajando con la anterior versión de esta biblioteca puedes entrar al branch [1.1.1](https://github.com/culqi/culqi-php/tree/1.1.1).

**Nota**: Esta biblioteca trabaja con la [v1.2](https://culqi.api-docs.io/v1.2) de Culqi API.


## Requisitos

* Android 4.0 o superior.
* Credenciales de comercio Culqi (1).

(1) Debes registrarte [aquí](https://integ-panel.culqi.com/#/registro). Luego, crear un comercio y estando en el panel, acceder a Desarrollo > [***API Keys***](https://integ-panel.culqi.com/#/panel/comercio/desarrollo/llaves).

![alt tag](http://i.imgur.com/NhE6mS9.png)

## Instalación

### Manualmente

Incluir la carpeta `Culqi"` en tu proyecto. Debes hacer el llamado correctamente a la carpeta y/o archivo dependiendo de tu estructura.


## Modo de uso

### Obtén un token
Antes de crear un Cargo, Plan o un Suscripción es necesario crear un `token` de tarjeta. Dentro de esta librería se encuentra una funcionalidad para generar 'tokens', debes de generar los 'tokens' directamente desde tu aplicación en Android, **debido a que es muy importante que los datos de tarjeta sean enviados desde el dispositivo de tus clientes directamente a los servidores de Culqi**, para no poner en riesgo información sensible.


```java
//Obtén los datos de la tarjeta de tu cliente
Card card = new Card();
card.setApellido("Doe");
card.setNombre("Jon");
card.setCorreo_electronico("jon@culqi.com");
card.setCvv(123);
card.setNumero(4111111111111111);
card.setA_exp(9);
card.setM_exp(2020);

String token = getToken(card, "<Ingresa aquí tu código de comercio");

if (token.equals("error")) {

      System.out.print("Ocurrió un error al crear el token");

} else {

       //Envía el token a tu servidor web y realiza el cargo o la suscripción.
       System.out.print("Token: " + token);

}

```
## Documentación
¿Necesitas más información para integrar `culqi-android`? La documentación completa se encuentra en [https://developers.culqi.com](https://developers.culqi.com)


## Licencia

MIT.