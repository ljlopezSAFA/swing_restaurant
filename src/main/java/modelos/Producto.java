package modelos;

import java.util.Objects;

public class Producto {

    private int id;
    private String descripcion;
    private Double  precio;
    private TipoProducto tipoProducto;

    public Producto(int id, String descripcion, Double precio, TipoProducto tipoProducto) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tipoProducto = tipoProducto;
    }

    public Producto() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id && Objects.equals(descripcion, producto.descripcion) && Objects.equals(precio, producto.precio) && tipoProducto == producto.tipoProducto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion, precio, tipoProducto);
    }

    @Override
    public String toString() {
        return descripcion + "\t" + "\t"  + precio;
    }
}
