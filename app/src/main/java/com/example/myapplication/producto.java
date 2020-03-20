package com.example.myapplication;

class producto {
    private String nombre_prod;
    private String precio_rod;
    private String descripcion_prod;
    private String ruta_imagen;

    public producto(String nombre_prod, String precio_rod, String descripcion_prod, String ruta_imagen) {
        this.nombre_prod = nombre_prod;
        this.precio_rod = precio_rod;
        this.descripcion_prod = descripcion_prod;
        this.ruta_imagen = ruta_imagen;
    }

    public String getNombre_prod() {
        return nombre_prod;
    }

    public void setNombre_prod(String nombre_prod) {
        this.nombre_prod = nombre_prod;
    }

    public String getPrecio_rod() {
        return precio_rod;
    }

    public void setPrecio_rod(String precio_rod) {
        this.precio_rod = precio_rod;
    }

    public String getDescripcion_prod() {
        return descripcion_prod;
    }

    public void setDescripcion_prod(String descripcion_prod) {
        this.descripcion_prod = descripcion_prod;
    }

    public String getRuta_imagen() {
        return ruta_imagen;
    }

    public void setRuta_imagen(String ruta_imagen) {
        this.ruta_imagen = ruta_imagen;
    }
}
