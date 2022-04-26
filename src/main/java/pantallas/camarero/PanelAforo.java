package pantallas.camarero;


import bbdd.MesaBD;
import bbdd.ProductoBD;
import modelos.Mesa;
import modelos.Producto;
import modelos.TipoProducto;
import utilidades.UtilidadesComponentes;
import utilidades.UtilidadesFormulario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PanelAforo extends JFrame {

    private static final ImageIcon fondoFormularios = new ImageIcon(getRutaImagenFondo());


    public PanelAforo() {
        super("Aforo");

        JPanel panelPrincipal = crearPanelImagenFondo();

        //Rellenamos las mesas
        List<Mesa> mesas = MesaBD.obtenerMesas();

        //Creaciónd de formulario
        JPanel panelExterior = new JPanel(new BorderLayout());
        JPanel panelMesas = new JPanel(new GridLayout(mesas.size()/3, 3));
        panelMesas.setOpaque(false);
        panelMesas.setSize(new Dimension(500, 500));



        for( Mesa m : mesas){
            JButton botonMesa = UtilidadesComponentes.crearBotonMesa(m);
            panelMesas.add(botonMesa);
        }

        //Estilos y Posicionamiento de paneles de formulario
        panelExterior.setBorder(new EmptyBorder(100,100,100,100));
        panelExterior.setOpaque(false);
        panelExterior.add(panelMesas,BorderLayout.CENTER);
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








}
