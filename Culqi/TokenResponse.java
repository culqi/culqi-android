package com.culqi.checkout;

/**
 * Created by William on 9/19/16.
 */
public class TokenResponse {

    String correo_electronico;

    String id;

    String objeto;

    String tipo;

    String codigo;

    String mensaje;

    String mensaje_usuario;

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public TokenResponse setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
        return this;
    }

    public String getId() {
        return id;
    }

    public TokenResponse setId(String id) {
        this.id = id;
        return this;
    }

    public String getObjeto() {
        return objeto;
    }

    public TokenResponse setObjeto(String objeto) {
        this.objeto = objeto;
        return this;
    }

    public String getTipo() {
        return tipo;
    }

    public TokenResponse setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public String getCodigo() {
        return codigo;
    }

    public TokenResponse setCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public String getMensaje() {
        return mensaje;
    }

    public TokenResponse setMensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }

    public String getMensaje_usuario() {
        return mensaje_usuario;
    }

    public TokenResponse setMensaje_usuario(String mensaje_usuario) {
        this.mensaje_usuario = mensaje_usuario;
        return this;
    }
}
