package modelos;

import java.util.Objects;

public class Empleado {
    private int id;
    private String codigoEmpleado;
    private String nombre;
    private String apellidos;
    private TipoEmpleado tipoEmpleado;

    public Empleado() {
    }

    public Empleado(int id, String codigoEmpleado, String nombre, String apellidos) {
        this.id = id;
        this.codigoEmpleado = codigoEmpleado;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Empleado(int id, String codigoEmpleado, String nombre, String apellidos, TipoEmpleado tipoEmpleado) {
        this.id = id;
        this.codigoEmpleado = codigoEmpleado;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.tipoEmpleado = tipoEmpleado;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return id == empleado.id && Objects.equals(codigoEmpleado, empleado.codigoEmpleado) && Objects.equals(nombre, empleado.nombre) && Objects.equals(apellidos, empleado.apellidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigoEmpleado, nombre, apellidos);
    }

    @Override
    public String toString() {
        return nombre + "," + apellidos + "-" + codigoEmpleado;
    }
}
