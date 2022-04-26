package modelos;

import java.util.Objects;

public class LineaComanda {

    private int id_comanda;
    private Producto producto;
    private int cantidad_pedida;
    private int cantidad_servida;

    public LineaComanda(int id_comanda, Producto producto, int cantidad_pedida, int cantidad_servida) {
        this.id_comanda = id_comanda;
        this.producto = producto;
        this.cantidad_pedida = cantidad_pedida;
        this.cantidad_servida = cantidad_servida;
    }

    public LineaComanda(Producto producto, int cantidad_pedida, int cantidad_servida) {
        this.producto = producto;
        this.cantidad_pedida = cantidad_pedida;
        this.cantidad_servida = cantidad_servida;
    }

    public LineaComanda() {
    }

    public int getId_comanda() {
        return id_comanda;
    }

    public void setId_comanda(int id_comanda) {
        this.id_comanda = id_comanda;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad_pedida() {
        return cantidad_pedida;
    }

    public void setCantidad_pedida(int cantidad_pedida) {
        this.cantidad_pedida = cantidad_pedida;
    }

    public int getCantidad_servida() {
        return cantidad_servida;
    }

    public void setCantidad_servida(int cantidad_servida) {
        this.cantidad_servida = cantidad_servida;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineaComanda that = (LineaComanda) o;
        return cantidad_pedida == that.cantidad_pedida && cantidad_servida == that.cantidad_servida && Objects.equals(producto, that.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producto, cantidad_pedida, cantidad_servida);
    }

    @Override
    public String toString() {
        return producto.getDescripcion() + ": " + cantidad_pedida;
    }
}
