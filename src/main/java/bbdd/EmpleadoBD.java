package bbdd;

import modelos.Empleado;
import modelos.Producto;
import modelos.TipoEmpleado;
import modelos.TipoProducto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoBD extends UtilidadesBD {

    public static Empleado obtenerPorId(Integer id) {

        Connection con = conectarConBD();
        Empleado empleado = null;

        try {
            PreparedStatement query = con.prepareStatement("SELECT * FROM empleado where id = ?  ");
            query.setInt(1, id);
            ResultSet rs = query.executeQuery();

            //Recorremos los datos
            while (rs.next()) {
                empleado = new Empleado(rs.getInt("id"), rs.getString("codigo_empleado"),
                        rs.getString("nombre"), rs.getString("apellidos"), TipoEmpleado.values()[rs.getInt("tipo_empleado")]);
            }

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());

        } finally {
            cerrarConexion(con);
        }

        return empleado;
    }



    public static void crearActualizarEmpleado(Empleado empleado){

        Empleado empBaseDatos = obtenerPorId(empleado.getId());

        if(empBaseDatos != null){
            actualizarEmpleado(empleado);
        }else{
            crearEmpleado(empleado);
        }
    }

    public static List<Empleado> obtenerEmpleados() {

        Connection con = conectarConBD();
        List<Empleado> empleados = new ArrayList<>();

        try {
            PreparedStatement query = con.prepareStatement("SELECT * FROM empleado ");
            ResultSet rs = query.executeQuery();

            //Recorremos los datos
            while (rs.next()) {
                Empleado empleado = new Empleado(
                        rs.getInt("id"),
                        rs.getString("codigo_empleado"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        TipoEmpleado.values()[rs.getInt("tipo_empleado")]);
                empleados.add(empleado);
            }

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());

        } finally {
            cerrarConexion(con);
        }

        return empleados;
    }

    public static void crearEmpleado(Empleado empleado){
        Connection con = conectarConBD();

        try {
            PreparedStatement insert = con.prepareStatement("insert into empleado (id, codigo_empleado, nombre, apellidos, tipo_empleado)" +
                    "values(?,?,?,?,?)");

            insert.setInt(1, empleado.getId());
            insert.setString(2,empleado.getCodigoEmpleado());
            insert.setString(3,empleado.getNombre());
            insert.setString(4, empleado.getApellidos());
            insert.setInt(5, empleado.getTipoEmpleado().ordinal());

            //Ejecución del insert
            insert.executeUpdate();


        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());

        } finally {
            cerrarConexion(con);
        }
    }


    public static void actualizarEmpleado(Empleado empleado){
        Connection con = conectarConBD();

        try {
            PreparedStatement update = con.prepareStatement("update empleado " +
                    "set codigo_empleado = ? , nombre = ? , apellidos = ? , tipo_empledado = ?" +
                    "where id = ? ");

            update.setString(1,empleado.getCodigoEmpleado());
            update.setString(2,empleado.getNombre());
            update.setString(3, empleado.getApellidos());
            update.setInt(4, empleado.getTipoEmpleado().ordinal());
            update.setInt(5, empleado.getId());


            //Ejecución del update
            update.executeUpdate();


        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());

        } finally {
            cerrarConexion(con);
        }
    }


    public static void eliminarEmpleado(Empleado empleado){
        Connection con = conectarConBD();

        try {
            PreparedStatement delete = con.prepareStatement("delete from empleado where id = ? ");

            delete.setInt(1, empleado.getId());

            //Ejecución del delete
            delete.executeUpdate();


        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());

        } finally {
            cerrarConexion(con);
        }
    }


}
