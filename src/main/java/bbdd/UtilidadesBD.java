package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;

public class UtilidadesBD {

    private static final String url = "jdbc:mariadb://localhost:3306/restaurantDB";
    private static final String usuario = "root";
    private static final String password = "1234";


    static Connection conectarConBD() {
        Connection conexion;
        try {
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (Exception e) {
            System.out.println("Error en la conexión:" + e.toString());
            return null;
        }
        return conexion;
    }


    static void cerrarConexion(Connection con) {
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





}
