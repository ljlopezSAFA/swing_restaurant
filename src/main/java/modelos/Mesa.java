package modelos;

import java.util.Objects;

public class Mesa {

    private int id;
    private int num_mesa;
    private boolean ocupada = false;

    public Mesa() {
    }

    public Mesa(int id, int num_mesa, boolean ocupada) {
        this.id = id;
        this.num_mesa = num_mesa;
        this.ocupada = ocupada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_mesa() {
        return num_mesa;
    }

    public void setNum_mesa(int num_mesa) {
        this.num_mesa = num_mesa;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mesa mesa = (Mesa) o;
        return id == mesa.id && ocupada == mesa.ocupada && Objects.equals(num_mesa, mesa.num_mesa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, num_mesa, ocupada);
    }

    @Override
    public String toString() {
        return "Mesa" + num_mesa;
    }
}
