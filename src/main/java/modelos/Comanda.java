package modelos;

import java.util.List;
import java.util.Objects;

public class Comanda {

    private int id;
    private String codigo_comanda;
    private Empleado empleado;
    private Mesa mesa;
    private List<LineaComanda> lineasComanda;

    public Comanda(int id, String codigo_comanda, Empleado empleado, Mesa mesa, List<LineaComanda> lineasComanda) {
        this.id = id;
        this.codigo_comanda = codigo_comanda;
        this.empleado = empleado;
        this.mesa = mesa;
        this.lineasComanda = lineasComanda;
    }

    public Comanda(int id, String codigo_comanda, Empleado empleado, Mesa mesa) {
        this.id = id;
        this.codigo_comanda = codigo_comanda;
        this.empleado = empleado;
        this.mesa = mesa;
    }

    public Comanda() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo_comanda() {
        return codigo_comanda;
    }

    public void setCodigo_comanda(String codigo_comanda) {
        this.codigo_comanda = codigo_comanda;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public List<LineaComanda> getLineasComanda() {
        return lineasComanda;
    }

    public void setLineasComanda(List<LineaComanda> lineasComanda) {
        this.lineasComanda = lineasComanda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comanda comanda = (Comanda) o;
        return id == comanda.id && Objects.equals(codigo_comanda, comanda.codigo_comanda) && Objects.equals(empleado, comanda.empleado) && Objects.equals(mesa, comanda.mesa) && Objects.equals(lineasComanda, comanda.lineasComanda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo_comanda, empleado, mesa, lineasComanda);
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "codigo_comanda='" + codigo_comanda + '\'' +
                '}';
    }
}
