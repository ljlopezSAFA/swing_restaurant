package modelos;

import java.util.List;
import java.util.Objects;

public class Cuenta {
    private String num_mesa;
    private String nombre_camarero;
    private List<TotalesComanda> totalesComandas;
    private Double totalCuenta;


    public Cuenta(String num_mesa, String nombre_camarero, List<TotalesComanda> totalesComandas, Double totalCuenta) {
        this.num_mesa = num_mesa;
        this.nombre_camarero = nombre_camarero;
        this.totalesComandas = totalesComandas;
        this.totalCuenta = totalCuenta;
    }


    public Cuenta() {
    }

    public String getNum_mesa() {
        return num_mesa;
    }

    public void setNum_mesa(String num_mesa) {
        this.num_mesa = num_mesa;
    }

    public String getNombre_camarero() {
        return nombre_camarero;
    }

    public void setNombre_camarero(String nombre_camarero) {
        this.nombre_camarero = nombre_camarero;
    }

    public List<TotalesComanda> getTotalesComandas() {
        return totalesComandas;
    }

    public void setTotalesComandas(List<TotalesComanda> totalesComandas) {
        this.totalesComandas = totalesComandas;
    }

    public Double getTotalCuenta() {
        return totalCuenta;
    }

    public void setTotalCuenta(Double totalCuenta) {
        this.totalCuenta = totalCuenta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuenta cuenta = (Cuenta) o;
        return Objects.equals(num_mesa, cuenta.num_mesa) && Objects.equals(nombre_camarero, cuenta.nombre_camarero) && Objects.equals(totalesComandas, cuenta.totalesComandas) && Objects.equals(totalCuenta, cuenta.totalCuenta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num_mesa, nombre_camarero, totalesComandas, totalCuenta);
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "num_mesa='" + num_mesa + '\'' +
                ", nombre_camarero='" + nombre_camarero + '\'' +
                ", totalesComandas=" + totalesComandas +
                ", totalCuenta=" + totalCuenta +
                '}';
    }
}
