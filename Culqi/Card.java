package com.culqi.checkout;

/**
 * Created by William on 9/19/16.
 */
public class Card {

    String correo_electronico;
    String nombre;
    String apellido;
    Long numero;
    Integer cvv;
    Integer m_exp;
    Integer a_exp;

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public Card setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Card setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getApellido() {
        return apellido;
    }

    public Card setApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public Long getNumero() {
        return numero;
    }

    public Card setNumero(Long numero) {
        this.numero = numero;
        return this;
    }

    public Integer getCvv() {
        return cvv;
    }

    public Card setCvv(Integer cvv) {
        this.cvv = cvv;
        return this;
    }

    public Integer getM_exp() {
        return m_exp;
    }

    public Card setM_exp(Integer m_exp) {
        this.m_exp = m_exp;
        return this;
    }

    public Integer getA_exp() {
        return a_exp;
    }

    public Card setA_exp(Integer a_exp) {
        this.a_exp = a_exp;
        return this;
    }
}
