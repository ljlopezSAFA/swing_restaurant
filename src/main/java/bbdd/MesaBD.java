package bbdd;

import modelos.Mesa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MesaBD extends UtilidadesBD{


    public static Mesa obtenerPorId(int idMesa) {

        Connection con = conectarConBD();
        Mesa mesa = null;

        try {
            PreparedStatement query = con.prepareStatement("SELECT id,num_mesa, esta_ocupada FROM mesa where id = ? ");
            query.setInt(1,idMesa);
            ResultSet rs = query.executeQuery();

            //Recorremos los datos
            while (rs.next()) {
                mesa= new Mesa(
                        rs.getInt("id"),
                        rs.getInt("num_mesa"),
                        rs.getInt("esta_ocupada") == 1);

            }

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());

        } finally {
            cerrarConexion(con);
        }

        return mesa;
    }

    public static List<Mesa> obtenerMesas() {

        Connection con = conectarConBD();
        List<Mesa> mesas = new ArrayList<>();

        try {
            PreparedStatement query = con.prepareStatement("SELECT id,num_mesa, esta_ocupada FROM mesa ");
            ResultSet rs = query.executeQuery();

            //Recorremos los datos
            while (rs.next()) {
                Mesa mesa = new Mesa(
                        rs.getInt("id"),
                        rs.getInt("num_mesa"),
                        rs.getInt("esta_ocupada") == 1);

                mesas.add(mesa);
            }

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());

        } finally {
            cerrarConexion(con);
        }

        return mesas;
    }

    public static void ocuparDesocuparMesa(Mesa mesa) {

        Connection con = conectarConBD();
        try {
            PreparedStatement update = con.prepareStatement("update mesa set esta_ocupada = ? where id = ?");
            update.setInt(1, mesa.isOcupada()? 0 : 1);
            update.setInt(2,mesa.getId());
            update.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());

        } finally {
            cerrarConexion(con);
        }

    }

    public static  int  sePuedeSolicitarCuenta(Mesa mesa){

        Connection con = conectarConBD();

         try{
             PreparedStatement consultarIdComanda = con.prepareStatement("select id from comanda c  where id_mesa = ? and historico = 0");
             consultarIdComanda.setInt(1, mesa.getId());
             ResultSet rs = consultarIdComanda.executeQuery();

             if(rs.next()){
                 int id_comanda = rs.getInt("id");

                 //Que esa comanda tiene todos los productos pedidos servidos.
                 PreparedStatement consultarPedidoServido = con
                         .prepareStatement("select 1 as res where (select sum(cantidad_pedida- candidad_servida) from linea_comanda lc where id_comanda = ?) = 0; ");
                 consultarPedidoServido.setInt(1, id_comanda);
                 ResultSet resultado = consultarPedidoServido.executeQuery();

                 if(rs.next()){
                     return id_comanda;
                 }else{
                     return  0;
                 }

             }else{
                 return 0;
             }


         }catch (SQLException sqle){
             System.out.println("Error en la ejecución:"
                     + sqle.getErrorCode() + " " + sqle.getMessage());

         }finally {
             cerrarConexion(con);

         }

         return 0;

    }

}
