package bbdd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilidadesBD {

    private static final String url = "jdbc:mariadb://localhost:3306/restaurantDB";
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

    public List<Object> obtenerProductos(){

        Connection con = conectarConBD();
        List<Object> objetos = new ArrayList<>();

        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM producto");
            ResultSet rs = stmt.executeQuery();

            //Recorremos los datos
            while (rs.next()){
               int id = rs.getInt("id");
               String descripcion = rs.getString("descripcion");
            }

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());

        } finally {
            try {
                // Cerramos conexiones
                if (con!=null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println("Error cerrando conexiones: "
                        + e.toString());
            }
        }

        return objetos;
    }

}
