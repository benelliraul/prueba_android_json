package com.example.myapplication;

public class Tiendas {
    private String nombre;
    private String direccion;
    private String celular;
    private String correo;
    private String id;//podria ser conveniente que sea un numero
    private String categoria;
    private String ruta_imagen;
    private int latitud;//qizas hay que cambiar el tipo a floa u otro
    private int longiitu;

    public String getRuta_imagen() {
        return ruta_imagen;
    }

    public void setRuta_imagen(String ruta_imagen) {
        this.ruta_imagen = ruta_imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getLatitud() {
        return latitud;
    }

    public void setLatitud(int latitud) {
        this.latitud = latitud;
    }

    public int getLongiitu() {
        return longiitu;
    }

    public void setLongiitu(int longiitu) {
        this.longiitu = longiitu;
    }

    public Tiendas(String nombre, String direccion, String celular, String correo, String id, String categoria, String ruta_imagen, int latitud, int longiitu) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.celular = celular;
        this.correo = correo;
        this.id = id;
        this.categoria = categoria;
        this.ruta_imagen = ruta_imagen;
        this.latitud = latitud;
        this.longiitu = longiitu;
    }
}
