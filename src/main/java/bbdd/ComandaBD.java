package bbdd;

import modelos.Comanda;
import modelos.Empleado;
import modelos.LineaComanda;
import modelos.Mesa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComandaBD extends UtilidadesBD {


    public static Comanda obtenerPorId(Mesa mesa, Empleado camarero){

        Connection conexion = conectarConBD();
        Comanda comanda = null;


        try {
            PreparedStatement consulta = conexion.prepareStatement("select * from comanda where id_mesa = ? and  id_empleado = ? ");
            consulta.setInt(1, mesa.getId());
            consulta.setInt(2,camarero.getId());
            ResultSet rs = consulta.executeQuery();

            while(rs.next()){

                comanda = new Comanda();
                comanda.setId(rs.getInt("id"));
                comanda.setCodigo_comanda(rs.getString("codigo_comanda"));

                //Obtener Empleado
                Empleado e = EmpleadoBD.obtenerPorId(rs.getInt("id_empleado")) ;
                comanda.setEmpleado(e);

                //Obtener Mesa
                Mesa m = MesaBD.obtenerPorId(rs.getInt("id_mesa")) ;
                comanda.setMesa(m);

                //Obtener Lineas de la comanda
                List<LineaComanda> lineaComandas = LineaComandaBD.obtenerLineaComanda(comanda.getId());
                comanda.setLineasComanda(lineaComandas);



            }

        }catch (SQLException e){
            System.out.println("Error en la ejecución:"
                    + e.getErrorCode() + " " + e.getMessage());



        }finally {
            cerrarConexion(conexion);
        }


        return comanda;

    }


    public static List<Comanda> obtenerComandas(){

        Connection conexion = conectarConBD();
        List<Comanda> comandas = new ArrayList<>();


        try {
            PreparedStatement consulta = conexion.prepareStatement("select * from comanda");
            ResultSet rs = consulta.executeQuery();

            while(rs.next()){

                Comanda comanda = new Comanda();
                comanda.setId(rs.getInt("id"));
                comanda.setCodigo_comanda(rs.getString("codigo_comanda"));

                //Obtener Empleado
                Empleado e = EmpleadoBD.obtenerPorId(rs.getInt("id_empleado")) ;
                comanda.setEmpleado(e);

                //Obtener Mesa
                Mesa m = MesaBD.obtenerPorId(rs.getInt("id_mesa")) ;
                comanda.setMesa(m);

                //Obtener Lineas de la comanda
                List<LineaComanda> lineaComandas = LineaComandaBD.obtenerLineaComanda(comanda.getId());
                comanda.setLineasComanda(lineaComandas);


                comandas.add(comanda);

            }

        }catch (SQLException e){
            System.out.println("Error en la ejecución:"
                    + e.getErrorCode() + " " + e.getMessage());



        }finally {
            cerrarConexion(conexion);
        }


        return comandas;

    }


}
