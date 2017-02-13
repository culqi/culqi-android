# Culqi Android

**Nota**: Esta biblioteca trabaja con la [v2](https://beta.culqi.com/#/) de Culqi API.

## Requisitos

* Android 4.0 o superior.
* Credenciales de comercio Culqi (1).

## Construye tu propio formulario

Si crea su propio formulario de pago, deberá recopilar al menos los números de tarjeta y las fechas de vencimiento de sus clientes. Es probable que también recoger el CVV para evitar el fraude. 
Una vez que haya recopilado la información de un cliente, tendrá que intercambiar la información por un token Culqi.

## Creación de Token desde un formulario personalizado

Puede crear tokens utilizando el método utilizando el método de instancia Culqi createToken
Pasando el número de la tarjeta, cvv, la fecha de vencimiento y un correo

```java
Card card = new Card(“411111111111111”, “123”, 9, 2020, “wm@wm.com”);

Token token = new Token("{CODIGO COMERCIO}");

token.createToken(getApplicationContext(), card, new TokenCallback() {
      @Override
      public void onSuccess(JSONObject token) {
            // token
            token.get("id").toString()
      }

      @Override
      public void onError(Exception error) {
      }
});
```

## Usando Tokens

El uso de token requiere una llamada de API desde su servidor utilizando su clave de API secreta. (Por razones de seguridad, nunca debe incrustar su clave secreta de API en su aplicación.) El método **createToken** devuelve onSuccess una respuesta en json y onError el error de la petición HTTP


## Licencia

MIT.
