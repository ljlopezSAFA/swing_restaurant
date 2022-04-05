package pantallas.cliente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class PanelGestionCliente extends JFrame {

    private static final ImageIcon fondoPantalla = new ImageIcon(getRutaImagenFondo()) ;
    private JButton botonrVerCarta;




    public PanelGestionCliente(){
        super("TABERNA LA ESQUINITA - Panel Gestión Cliente");

        //PANEL DE FONDO
        JPanel panelPrincipal = crearPanelImagenFondo();


        //BOTONES
        JPanel panelBotonesCamarero = new JPanel();
        panelBotonesCamarero.setBorder(new EmptyBorder(100,100,100,100));
        panelBotonesCamarero.setOpaque(false);
        botonrVerCarta = crearVerCarta();
        panelBotonesCamarero.add(botonrVerCarta,BorderLayout.CENTER);
        panelPrincipal.add(panelBotonesCamarero,BorderLayout.WEST);




        //ASPECTO
        setContentPane(panelPrincipal);//Panel de Fondo
        setBackground(Color.DARK_GRAY); //COLOR
        setLocationRelativeTo(null); //POSICION CENTRADA
        pack();
        setSize(1200,800); //TAMAÑO DE VENTANA
        setResizable(false);
        setLocationRelativeTo(null); //POSICION CENTRADA
        setVisible(true); //VISIBILIDAD


    }


    private JPanel crearPanelImagenFondo(){
        JPanel panel = new JPanel(new BorderLayout()){
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondoPantalla.getImage(), 0, 0, null);
            }
        };
        return panel;

    }


    private static String getRutaImagenFondo(){
        String ruta = new File("").getAbsolutePath();
        return ruta + "\\imagenes\\panel_clientes.jpg";
    }


    private  JButton crearVerCarta(){
        JButton boton = new JButton("Ver Carta");
        String ruta = new File("").getAbsolutePath() + "\\imagenes\\vercarta.png" ;
        ImageIcon icono = new ImageIcon(ruta);
        Image imagenLimitadaTamanyo = icono.getImage().getScaledInstance(120, 120,  Image.SCALE_SMOOTH);
        icono.setImage(imagenLimitadaTamanyo);
        boton.setIcon(icono);
        boton.setFocusPainted(false);
        //boton.addActionListener();
        return boton;
    }



}
