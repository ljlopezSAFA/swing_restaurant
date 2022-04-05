package pantallas.administrador;


import bbdd.ProductoBD;
import modelos.*;
import utilidades.UtilidadesFormulario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FormularioProducto extends JFrame {

    private static final ImageIcon fondoFormularios = new ImageIcon(getRutaImagenFondo());


    public FormularioProducto() {
        super("Productos");

        JPanel panelPrincipal = crearPanelImagenFondo();


        //Creaciónd de formulario
        JPanel panelExteriorFormulario = new JPanel(new BorderLayout());
        JPanel formulario = new JPanel(new GridLayout(0,1));
        formulario.setOpaque(false);
        formulario.setSize(new Dimension(300, 300));

        //Campos Formulario
        UtilidadesFormulario.crearCampoFormulario(formulario,"id", TipoCampoFormulario.NUMERO, null);
        UtilidadesFormulario.crearCampoFormulario(formulario,"descripción", TipoCampoFormulario.TEXTO,null);
        UtilidadesFormulario.crearCampoFormulario(formulario,"tipo producto", TipoCampoFormulario.COMBO, List.of(TipoProducto.values()));
        UtilidadesFormulario.crearCampoFormulario(formulario,"precio", TipoCampoFormulario.TEXTO,null);

        //Espaciado
        formulario.add(Box.createRigidArea(new Dimension(5, 0)));

        //Botones Formulario
        UtilidadesFormulario.crearGridBotones(formulario,crearBotonesFormulario(formulario));


        //Estilos y Posicionamiento de paneles de formulario
        panelExteriorFormulario.setBorder(new EmptyBorder(100,100,100,100));
        panelExteriorFormulario.setOpaque(false);
        panelExteriorFormulario.add(formulario,BorderLayout.CENTER);
        panelPrincipal.add(panelExteriorFormulario, BorderLayout.CENTER);




        //ASPECTO
        setContentPane(panelPrincipal);//Panel de Fondo
        setBackground(Color.DARK_GRAY); //COLOR
        setLocationRelativeTo(null); //POSICION CENTRADA
        pack();
        setSize(500, 750); //TAMAÑO DE VENTANA
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
        return ruta + "\\imagenes\\formularios.jpg";
    }


    private List<JButton> crearBotonesFormulario(JPanel formulario){
        List<JButton> listadoBotones = new ArrayList<>();

        //BUSCAR
        JButton buscar = new JButton("Buscar");
        buscar.addActionListener(new ActionListener( ) {
            public void actionPerformed(ActionEvent e) {
                Integer idProducto = Integer.valueOf(UtilidadesFormulario.obtenerTextoComponente(formulario,"id").toString());
                Producto producto = ProductoBD.obtenerPorId(idProducto);
                if(producto!= null) {
                    cambiarDatosFormulario(formulario, String.valueOf(producto.getId()), producto.getDescripcion(),producto.getPrecio().toString(),producto.getTipoProducto());
                }
                else{
                    cambiarDatosFormulario(formulario, "", "", "", "");
                }
            }
        });


        //Guardar
        JButton guardar = new JButton("Guardar");
        guardar.addActionListener(new ActionListener( ) {
            public void actionPerformed(ActionEvent e) {
                Producto producto = new Producto();
                producto.setId(Integer.parseInt(UtilidadesFormulario.obtenerTextoComponente(formulario,"id").toString()));
                producto.setDescripcion(UtilidadesFormulario.obtenerTextoComponente(formulario,"descripción").toString());
                producto.setPrecio(Double.parseDouble(UtilidadesFormulario.obtenerTextoComponente(formulario,"precio").toString()));
                producto.setTipoProducto((TipoProducto) UtilidadesFormulario.obtenerTextoComponente(formulario,"tipo producto"));
                ProductoBD.crearActualizarProducto(producto);
                cambiarDatosFormulario(formulario,"","","",null);
            }
        });

        //Eliminar
        JButton eliminar  = new JButton("Eliminar");
        eliminar.addActionListener(new ActionListener( ) {
            public void actionPerformed(ActionEvent e) {
                Producto producto = new Producto();
                producto.setId(Integer.parseInt(UtilidadesFormulario.obtenerTextoComponente(formulario,"id").toString()));
                ProductoBD.eliminarProducto(producto);
                cambiarDatosFormulario(formulario,"","","", null);
            }
        });

        listadoBotones.add(buscar);
        listadoBotones.add(guardar);
        listadoBotones.add(eliminar);
        return listadoBotones;
    }

    private void cambiarDatosFormulario(JPanel formulario, String id, String descripcion, String precio, Object tipoProducto) {
        UtilidadesFormulario.ponerValorComponente(formulario, "id", id);
        UtilidadesFormulario.ponerValorComponente(formulario, "descripción", descripcion);
        UtilidadesFormulario.ponerValorComponente(formulario, "precio", precio);
        if(tipoProducto!= null) UtilidadesFormulario.ponerValorComponente(formulario, "tipo producto", tipoProducto);
    }





}
