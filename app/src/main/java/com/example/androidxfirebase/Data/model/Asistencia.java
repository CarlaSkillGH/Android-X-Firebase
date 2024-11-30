package com.example.androidxfirebase.Data.model;

public class Asistencia {

    private String fecha_entrada;
    private String fecha_salida;
    private String novedades_asistencia;
    private String numero_documento_identidad;

    public Asistencia() {
    }

    public Asistencia(String fecha_entrada, String fecha_salida, String novedades_asistencia, String numero_documento_identidad) {
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
        this.novedades_asistencia = novedades_asistencia;
        this.numero_documento_identidad = numero_documento_identidad;
    }

    public String getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(String fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public String getNovedades_asistencia() {
        return novedades_asistencia;
    }

    public void setNovedades_asistencia(String novedades_asistencia) {
        this.novedades_asistencia = novedades_asistencia;
    }

    public String getNumero_documento_identidad() {
        return numero_documento_identidad;
    }

    public void setNumero_documento_identidad(String numero_documento_identidad) {
        this.numero_documento_identidad = numero_documento_identidad;
    }
}
