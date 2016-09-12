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

Clonar el repositorio o descargar el código fuente

```bash
$ git clone git@github.com:culqi/culqi-android.git
```

Ahora, incluir la carpeta `Culqi"` en tu proyecto. Debes hacer el llamado correctamente a la carpeta y/o archivo dependiendo de tu estructura.

```java
...
import com.culqi.*
...
```

## Modo de uso

Inicialmente hay que configurar la credencial `merchantCode`:

```objective-c
// Configurar tu Código de Comercio
    culqi.setMerchantCode = "<Ingresa aquí tu código de comercio>";


```

### Obtén un token
Antes de crear un Cargo, Plan o un Suscripción es necesario crear un `token` de tarjeta. Dentro de esta librería se encuentra una funcionalidad para generar 'tokens', debes de generar los 'tokens' directamente desde tu aplicación en Android, **debido a que es muy importante que los datos de tarjeta sean enviados desde el dispositivo de tus clientes directamente a los servidores de Culqi**, para no poner en riesgo información sensible.


```java
//Obtén los datos de la tarjeta de tu cliente
Card *card = [[Card alloc] init];
card.number = @"4111111111111111";
card.cvc = @"123";
card.expMonth = @"09";
card.expYear = @"2020";
card.email = @"wmuro@me.com";
card.lastName = @"Muro";
card.firstName = @"William";

//Crea el token de tarjeta
[culqi createToken:card completion:^(Token *token, NSError *error) {

        if (error) {
             //Si recibes error, muestra a tu cliente el mensaje dirigido al usuario.
            NSLog(@"¡Ocurrió un error!");
            NSLog(@"Domain: %@ Code: %li", [error domain], [error code]);
            NSLog(@"Descripción: %@", [error localizedDescription]);
            
        } else {
            
            NSLog(@"¡Registro exitoso!");

            //Tienes que enviar el token.id a tu servidor para realizar un cargo o una suscripción.
            NSLog(@"Token de tarjeta: %@", token.id);
            
            //También tienes información adicional que te puede ser útil.
            NSLog(@"Número de tarjeta: %@", token.tokenCard.number);
            NSLog(@"Marca de tarjeta: %@", token.tokenCard.brand);
            NSLog(@"Correo electrónico: %@", token.email);
            NSLog(@"Nombre: %@", token.tokenCard.firstName);
            NSLog(@"Apellido: %@", token.tokenCard.lastName);
            NSLog(@"Bin de tarjeta: %@", token.tokenCard.bin);

        }
        
    }];


```
## Documentación
¿Necesitas más información para integrar `culqi-android`? La documentación completa se encuentra en [https://developers.culqi.com](https://developers.culqi.com)


## Licencia

MIT.
