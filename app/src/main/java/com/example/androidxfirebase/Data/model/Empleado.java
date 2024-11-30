package com.example.androidxfirebase.Data.model;

public class Empleado {

    private String primer_nombre;
    private String primer_apellido;
    private String numero_documento_identidad;
    private String fecha_nacimiento;
    private String fecha_contratacion;
    private String estado_empleado;
    private String edad;
    private String ciudad;
    private String departamento;
    private String cargo;
    private String correo;
    private String image;
    private String telefono;
    private String tipo_documento_identidad;

    public Empleado() {
    }

    public Empleado(String primer_nombre, String primer_apellido, String numero_documento_identidad, String fecha_nacimiento, String fecha_contratacion, String estado_empleado, String edad, String ciudad, String departamento, String cargo, String correo, String image, String telefono, String tipo_documento_identidad) {
        this.primer_nombre = primer_nombre;
        this.primer_apellido = primer_apellido;
        this.numero_documento_identidad = numero_documento_identidad;
        this.fecha_nacimiento = fecha_nacimiento;
        this.fecha_contratacion = fecha_contratacion;
        this.estado_empleado = estado_empleado;
        this.edad = edad;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.cargo = cargo;
        this.correo = correo;
        this.image = image;
        this.telefono = telefono;
        this.tipo_documento_identidad = tipo_documento_identidad;
    }

    public String getTipo_documento_identidad() {
        return tipo_documento_identidad;
    }

    public void setTipo_documento_identidad(String tipo_documento_identidad) {
        this.tipo_documento_identidad = tipo_documento_identidad;
    }

    public String getPrimer_nombre() {
        return primer_nombre;
    }

    public void setPrimer_nombre(String primer_nombre) {
        this.primer_nombre = primer_nombre;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getNumero_documento_identidad() {
        return numero_documento_identidad;
    }

    public void setNumero_documento_identidad(String numero_documento_identidad) {
        this.numero_documento_identidad = numero_documento_identidad;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getFecha_contratacion() {
        return fecha_contratacion;
    }

    public void setFecha_contratacion(String fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }

    public String getEstado_empleado() {
        return estado_empleado;
    }

    public void setEstado_empleado(String estado_empleado) {
        this.estado_empleado = estado_empleado;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}