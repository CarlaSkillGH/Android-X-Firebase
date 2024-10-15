package com.example.androidxfirebase.Data.model;

public class Empleado {

    private String tipo_id;
    private String nombre;
    private String apellido;
    private String numero_id;
    private String fnacimiento;
    private String fcontratacion;
    private String estado;
    private String edad;
    private String direccion;
    private String departamento;
    private String telefono;
    private String cargo;
    private String image;
    private String email;
    private String id;

    public Empleado(String nombre, String apellido, String edad, String email, String direccion, String telefono, String cargo, String fnacimiento, String fcontratacion, String estado, String tipo_documento, String documento, String departamento, String s) {
    }

    public Empleado(String tipo_id, String nombre, String apellido, String numero_id, String fnacimiento, String fcontratacion, String estado, String edad, String direccion, String departamento, String telefono, String cargo, String image, String email,String id) {
        this.tipo_id = tipo_id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numero_id = numero_id;
        this.fnacimiento = fnacimiento;
        this.fcontratacion = fcontratacion;
        this.estado = estado;
        this.edad = edad;
        this.direccion = direccion;
        this.departamento = departamento;
        this.telefono = telefono;
        this.cargo = cargo;
        this.image = image;
        this.email = email;
        this.id = id;
    }

    public String getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(String tipo_id) {
        this.tipo_id = tipo_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumero_id() {
        return numero_id;
    }

    public void setNumero_id(String numero_id) {
        this.numero_id = numero_id;
    }

    public String getFnacimiento() {
        return fnacimiento;
    }

    public void setFnacimiento(String fnacimiento) {
        this.fnacimiento = fnacimiento;
    }

    public String getFcontratacion() {
        return fcontratacion;
    }

    public void setFcontratacion(String fcontratacion) {
        this.fcontratacion = fcontratacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
