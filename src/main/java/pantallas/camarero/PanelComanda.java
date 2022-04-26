package pantallas.camarero;


import bbdd.*;
import modelos.*;
import utilidades.UtilidadesComponentes;
import utilidades.UtilidadesFormulario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PanelComanda extends JFrame {

    private static final ImageIcon fondoFormularios = new ImageIcon(getRutaImagenFondo());
    private JTable tablaComanda;
    private Comanda comanda;


    public PanelComanda() {
        super("Comandas");

        JPanel panelPrincipal = crearPanelImagenFondo();

        //Cargamos los datos de los combos
        List<Mesa> mesas = MesaBD.obtenerMesas();
        List<Empleado> empleados = EmpleadoBD.obtenerEmpleados();
        List<Producto> productos = ProductoBD.obtenerProductos();

        //Paneles
        JPanel panelExterior = new JPanel(new BorderLayout());
        JPanel panelInterior = new JPanel();
        JPanel panel1 = new JPanel(new GridLayout(2, 6));
        JPanel panelTabla = new JPanel(new BorderLayout());

        //Contenido Panel 1
        UtilidadesFormulario.crearCampoFormulario(panel1,"Selecciona Mesa:" , TipoCampoFormulario.COMBO,mesas);
        UtilidadesFormulario.crearCampoFormulario(panel1,"Selecciona Camarero:" , TipoCampoFormulario.COMBO,empleados);
        UtilidadesComponentes.anyadirEspacioBlanco(panel1);
        JButton botonBuscarComanda = new JButton("\uD83D\uDD0D︎");
        botonBuscarComanda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mesa mesaSeleccionada = (Mesa) UtilidadesFormulario.obtenerTextoComponente(panel1,"Selecciona Mesa:") ;
                Empleado empleadoSeleccionado =  (Empleado) UtilidadesFormulario.obtenerTextoComponente(panel1,"Selecciona Camarero:") ;
                comanda = ComandaBD.obtenerPorId(mesaSeleccionada,empleadoSeleccionado);
                if(comanda==null) comanda = new Comanda(0,"C_"+ mesaSeleccionada.getNum_mesa(), empleadoSeleccionado,mesaSeleccionada, new ArrayList<>());
                actualizarContenidoTabla(comanda);
            }
        });
        panel1.add(botonBuscarComanda);
        UtilidadesFormulario.crearCampoFormulario(panel1,"Selecciona Producto:" , TipoCampoFormulario.COMBO,productos);
        UtilidadesFormulario.crearCampoFormulario(panel1,"Cantidad" , TipoCampoFormulario.NUMERO,new ArrayList<>());
        JButton botonAnyadirProducto = new JButton("+");
        botonAnyadirProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Producto productoSeleccionado = (Producto) UtilidadesFormulario.obtenerTextoComponente(panel1,"Selecciona Producto:");
                int cantidadSeleccionada =  Integer.parseInt( (String) UtilidadesFormulario.obtenerTextoComponente(panel1,"Cantidad"));
                anyadirProductos(productoSeleccionado, cantidadSeleccionada,comanda);
                actualizarContenidoTabla(comanda);

            }
        });
        UtilidadesComponentes.anyadirEspacioBlanco(panel1);
        panel1.add(botonAnyadirProducto);


        //Tabla Productos
        String[] columnNames = {"Producto","Cantidad"};
        Object[][] data = {
        };

        tablaComanda = new JTable(new DefaultTableModel(data,columnNames));
        tablaComanda.setRowHeight(40);
        tablaComanda.getColumnModel().getColumn(0).setMinWidth(500);
        tablaComanda.getColumnModel().getColumn(1).setMinWidth(500);
        panelTabla.add(tablaComanda, BorderLayout.CENTER);
        panelTabla.add(tablaComanda.getTableHeader(), BorderLayout.NORTH);
        JButton botonGuardarComanda = new JButton("Guardar Comanda︎");
        botonGuardarComanda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LineaComandaBD.guardarComanda(comanda);
            }
        });
        panelPrincipal.add(botonGuardarComanda,BorderLayout.SOUTH);




        //Estilos y Posicionamiento
        panelInterior.setBackground(Color.DARK_GRAY);
        panel1.setOpaque(false);
        panelTabla.setOpaque(false);
        panelTabla.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
        panelInterior.add(panel1);
        panelInterior.add(panelTabla);
        panelExterior.setBorder(new EmptyBorder(100,100,100,100));
        panelExterior.setOpaque(false);
        panelExterior.add(panelInterior,BorderLayout.CENTER);
        panelPrincipal.add(panelExterior, BorderLayout.CENTER);




        //ASPECTO
        setContentPane(panelPrincipal);//Panel de Fondo
        setBackground(Color.DARK_GRAY); //COLOR
        setLocationRelativeTo(null); //POSICION CENTRADA
        pack();
        setSize(1250, 600); //TAMAÑO DE VENTANA
        setResizable(false);
        setLocationRelativeTo(null); //POSICION CENTRADA
        setVisible(true); //VISIBILIDAD

    }



    private JPanel crearPanelImagenFondo(){
        JPanel panel = new JPanel(new BorderLayout()){
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondoFormularios.getImage(), 0, 0, null);
            }
        };

        return panel;

    }

    private static String getRutaImagenFondo(){
        String ruta = new File("").getAbsolutePath();
        return ruta + "\\imagenes\\aforo_imagen.jpg";
    }

    private void actualizarContenidoTabla(Comanda comanda){

        DefaultTableModel model = (DefaultTableModel) tablaComanda.getModel();
        model.setRowCount(0);

        if(comanda != null){
            for (LineaComanda lineaComanda : comanda.getLineasComanda()) {
                model = (DefaultTableModel) tablaComanda.getModel();
                model.addRow(new Object[]{lineaComanda.getProducto().getDescripcion(), lineaComanda.getCantidad_pedida()});

            }
            tablaComanda.repaint();
        }

    }

    private void anyadirProductos(Producto producto, int cantidad, Comanda comanda){

        //Comprobamos que el producto esté en la comanda
        if(!comanda.getLineasComanda().isEmpty() && comanda.getLineasComanda().stream().anyMatch(l -> l.getProducto().equals(producto))){

            //Cambiar cantidad de la linea de la comanda
            LineaComanda lineaComanda =  comanda.getLineasComanda().stream().filter(l-> l.getProducto().equals(producto)).findFirst().get();
            lineaComanda.setCantidad_pedida(lineaComanda.getCantidad_pedida() + cantidad);

        }else {
            LineaComanda nueva = new LineaComanda(producto, cantidad, 0);
            comanda.getLineasComanda().add(nueva);
        }


    }


}
