package bbdd;


import modelos.Producto;
import modelos.TipoProducto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoBD extends UtilidadesBD {

    public static Producto obtenerPorId(Integer id) {

        Connection con = conectarConBD();
        Producto producto = null;

        try {
            PreparedStatement query = con.prepareStatement("SELECT * FROM producto where id = ?  ");
            query.setInt(1, id);
            ResultSet rs = query.executeQuery();

            //Recorremos los datos
            while (rs.next()) {
                producto = new Producto(rs.getInt("id"), rs.getString("descripcion"),
                        rs.getDouble("precio"), TipoProducto.values()[rs.getInt("tipo_producto")]);
            }

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());

        } finally {
            cerrarConexion(con);
        }

        return producto;
    }



    public static void crearActualizarProducto(Producto producto){

        Producto productoBD = obtenerPorId(producto.getId());

        if(productoBD != null){
            actualizarProducto(producto);
        }else{
            crearProducto(producto);
        }
    }


    public static void crearProducto(Producto producto){
        Connection con = conectarConBD();

        try {
            PreparedStatement insert = con.prepareStatement("insert into producto (id, descripcion,precio,tipo_producto)" +
                    "values(?,?,?,?)");

            insert.setInt(1, producto.getId());
            insert.setString(2,producto.getDescripcion());
            insert.setDouble(3,producto.getPrecio());
            insert.setInt(4, producto.getTipoProducto().ordinal());

            //Ejecución del insert
            insert.executeUpdate();


        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());

        } finally {
            cerrarConexion(con);
        }
    }


    public static void actualizarProducto(Producto producto){
        Connection con = conectarConBD();

        try {
            PreparedStatement update = con.prepareStatement("update producto " +
                    "set descripcion = ? , precio = ? , tipo_producto = ?" +
                    "where id = ? ");

            update.setString(1,producto.getDescripcion());
            update.setDouble(2,producto.getPrecio());
            update.setInt(3, producto.getTipoProducto().ordinal());
            update.setInt(4, producto.getId());


            //Ejecución del update
            update.executeUpdate();


        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());

        } finally {
            cerrarConexion(con);
        }
    }


    public static void eliminarProducto(Producto producto){
        Connection con = conectarConBD();

        try {
            PreparedStatement delete = con.prepareStatement("delete from producto where id = ? ");

            delete.setInt(1, producto.getId());

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
