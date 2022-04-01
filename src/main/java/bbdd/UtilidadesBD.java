package bbdd;

import modelos.Empleado;

import java.sql.*;

public class UtilidadesBD {

    private static final String url = "jdbc:mariadb://localhost:3306/restaunrantDB";
    private static final String usuario = "root";
    private static final String password = "patata123";


    private static Connection conectarConBD() {
        Connection conexion;
        try {
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (Exception e) {
            System.out.println("Error en la conexión:" + e.toString());
            return null;
        }
        return conexion;
    }

    public static Empleado obtenerPorId(Integer id){

        Connection con = conectarConBD();
        Empleado empleado = null;

        try {
            PreparedStatement query = con.prepareStatement("SELECT * FROM empleado where id = ?  ");
            query.setInt(1,id);
            ResultSet rs = query.executeQuery();

            //Recorremos los datos
            while (rs.next()){
               empleado = new Empleado(rs.getInt("id"), rs.getString("codigo_empleado"),
                                        rs.getString("nombre"), rs.getString("apellidos"));
            }

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());

        } finally {
            cerrarConexion(con);
        }

        return empleado;
    }


    private static void cerrarConexion(Connection con) {
        try {
            // Cerramos conexiones
            if (con !=null) {
                con.close();
            }
        } catch (Exception e) {
            System.out.println("Error cerrando conexiones: "
                    + e.toString());
        }
    }


    public static void crearActualizarEmpleado(Empleado empleado){

        Empleado empBaseDatos = obtenerPorId(empleado.getId());

        if(empBaseDatos != null){
            actualizarEmpleado(empleado);
        }else{
            crearEmpleado(empleado);
        }
    }


    public static void crearEmpleado(Empleado empleado){
        Connection con = conectarConBD();

        try {
            PreparedStatement insert = con.prepareStatement("insert into empleado (id, codigo_empleado, nombre, apellidos)" +
                    "values(?,?,?,?)");

            insert.setInt(1, empleado.getId());
            insert.setString(2,empleado.getCodigoEmpleado());
            insert.setString(3,empleado.getNombre());
            insert.setString(4, empleado.getApellidos());

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
                    "set codigo_empleado = ? , nombre = ? , apellidos = ? " +
                    "where id = ? ");

            update.setString(1,empleado.getCodigoEmpleado());
            update.setString(2,empleado.getNombre());
            update.setString(3, empleado.getApellidos());
            update.setInt(4, empleado.getId());


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
