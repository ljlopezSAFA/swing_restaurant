package bbdd;

import modelos.Comanda;
import modelos.LineaComanda;
import modelos.Mesa;
import modelos.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LineaComandaBD extends UtilidadesBD {

    public static List<LineaComanda> obtenerLineaComanda(int idComanda) {

        Connection con = conectarConBD();
        List<LineaComanda> lineas = new ArrayList<>();

        try {
            PreparedStatement query = con.prepareStatement("select * from linea_comanda lc where id_comanda = ? ");
            query.setInt(1,idComanda);
            ResultSet rs = query.executeQuery();

            //Recorremos los datos
            while (rs.next()) {
                LineaComanda lineaComanda = new LineaComanda();
                lineaComanda.setId_comanda(rs.getInt("id_comanda"));
                lineaComanda.setCantidad_pedida(rs.getInt("cantidad_pedida"));
                lineaComanda.setCantidad_servida(rs.getInt("candidad_servida"));

                Producto p = ProductoBD.obtenerPorId(rs.getInt("id_producto"));
                lineaComanda.setProducto(p);


                lineas.add(lineaComanda);
            }

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());

        } finally {
            cerrarConexion(con);
        }

        return lineas;
    }

    public static void guardarComanda(Comanda comanda){

        Connection con = conectarConBD();


        try {
            PreparedStatement guardarComanda = con.prepareStatement("insert into comanda (codigo_comanda, id_empleado, id_mesa) values (?,?,?)"
                    , Statement.RETURN_GENERATED_KEYS );
            PreparedStatement guardarLineaComanda = con.prepareStatement("insert into linea_comanda" +
                    " (id_comanda, id_producto, cantidad_pedida, candidad_servida) values (?,?,?,?)");
            PreparedStatement actualizarLineaComanda = con.prepareStatement("update linea_comanda  set cantidad_pedida = ? " +
                    ",  candidad_servida = ?  where id_comanda =  ? and id_producto = ? ");


            if(comanda.getId()==0){
                guardarComanda.setString(1,comanda.getCodigo_comanda());
                guardarComanda.setInt(2,comanda.getEmpleado().getId());
                guardarComanda.setInt(3,comanda.getMesa().getId());
                guardarComanda.executeUpdate();
                ResultSet resultSet = guardarComanda.getGeneratedKeys();
                while (resultSet.next()){
                    comanda.setId(resultSet.getInt(1));
                }
            }

            if(comanda.getId() != 0){
                for(LineaComanda lineaComanda: comanda.getLineasComanda()){
                    if(lineaComanda.getId_comanda() == 0){
                        guardarLineaComanda.setInt(1,comanda.getId());
                        guardarLineaComanda.setInt(2,lineaComanda.getProducto().getId());
                        guardarLineaComanda.setInt(3, lineaComanda.getCantidad_pedida());
                        guardarLineaComanda.setInt(4,lineaComanda.getCantidad_servida());
                        guardarLineaComanda.executeUpdate();
                    }else{
                        actualizarLineaComanda.setInt(1, lineaComanda.getCantidad_pedida());
                        actualizarLineaComanda.setInt(2,lineaComanda.getCantidad_servida());
                        actualizarLineaComanda.setInt(3,lineaComanda.getId_comanda());
                        actualizarLineaComanda.setInt(4,lineaComanda.getProducto().getId());
                        actualizarLineaComanda.executeUpdate();
                    }
                }
            }


        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());

        } finally {
            cerrarConexion(con);
        }

    }


}
