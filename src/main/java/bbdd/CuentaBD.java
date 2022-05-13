package bbdd;

import modelos.Cuenta;
import modelos.TotalesComanda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CuentaBD extends UtilidadesBD {

    public static Cuenta generarObjetoCuenta(int idComanda) {

        Connection con = conectarConBD();
        Cuenta cuenta = new Cuenta();
        List<TotalesComanda> totalesComanda = new ArrayList<>();

        try {
            PreparedStatement query = con.prepareStatement("select * from totales_comanda where  id_comanda = ? ");
            query.setInt(1,idComanda);
            ResultSet rs = query.executeQuery();

            //Recorremos los datos
            while (rs.next()) {
                if(cuenta.getNum_mesa() == null){
                    cuenta.setNum_mesa(rs.getString("num_mesa"));
                }
                if(cuenta.getNombre_camarero() == null){
                    cuenta.setNombre_camarero(rs.getString("camarero"));
                }

                TotalesComanda t = new TotalesComanda();
                t.setNombre_producto(rs.getString("nombre_producto"));
                t.setCantidad_pedida(rs.getInt("cantidad_pedida"));
                t.setPrecio_total(rs.getDouble("total"));
                totalesComanda.add(t);
            }

            cuenta.setTotalesComandas(totalesComanda);
            cuenta.setTotalCuenta(cuenta.getTotalesComandas().stream().mapToDouble(TotalesComanda::getPrecio_total).sum());


        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());

        } finally {
            cerrarConexion(con);
        }

        return cuenta;
    }


}
