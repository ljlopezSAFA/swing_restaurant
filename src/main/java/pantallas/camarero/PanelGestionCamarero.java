package pantallas.camarero;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PanelGestionCamarero extends JFrame {

    private static final ImageIcon fondoPantalla = new ImageIcon(getRutaImagenFondo()) ;
    private JButton botonrComanda;
    private JButton botonCuenta;
    private JButton botonAforo;



    public PanelGestionCamarero(){
        super("TABERNA LA ESQUINITA - Panel Gestión Camarero");

        //PANEL DE FONDO
        JPanel panelPrincipal = crearPanelImagenFondo();


        //BOTONES
        JPanel panelBotonesCamarero = new JPanel();
        panelBotonesCamarero.setBorder(new EmptyBorder(100,100,100,100));
        panelBotonesCamarero.setOpaque(false);
        botonrComanda = crearBotonComanda();
        botonCuenta = crearBotonCuenta();
        botonAforo = crearBotonAforo();
        panelBotonesCamarero.add(botonrComanda,BorderLayout.EAST);
        panelBotonesCamarero.add(botonCuenta,BorderLayout.WEST);
        panelBotonesCamarero.add(botonAforo,BorderLayout.WEST);
        panelPrincipal.add(panelBotonesCamarero,BorderLayout.WEST);




        //ASPECTO
        setContentPane(panelPrincipal);//Panel de Fondo
        setBackground(Color.DARK_GRAY); //COLOR
        setLocationRelativeTo(null); //POSICION CENTRADA
        pack();
        setSize(1250,800); //TAMAÑO DE VENTANA
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
        return ruta + "\\imagenes\\panel_camarero_2.jpg";
    }


    private  JButton crearBotonComanda(){
        JButton boton = new JButton("Comandas");
        String ruta = new File("").getAbsolutePath() + "\\imagenes\\cubiertos.png" ;
        ImageIcon icono = new ImageIcon(ruta);
        Image imagenLimitadaTamanyo = icono.getImage().getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
        icono.setImage(imagenLimitadaTamanyo);
        boton.setIcon(icono);
        boton.setFocusPainted(false);
        boton.addActionListener(new ActionListener() {
                   @Override
                    public void actionPerformed(ActionEvent e) {
                        new PanelComanda();
                    }
                });
        return boton;
    }

    private  JButton crearBotonCuenta(){
        JButton boton = new JButton("Cuentas");
        String ruta = new File("").getAbsolutePath() + "\\imagenes\\factura.png" ;
        ImageIcon icono = new ImageIcon(ruta);
        Image imagenLimitadaTamanyo = icono.getImage().getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
        icono.setImage(imagenLimitadaTamanyo);
        boton.setIcon(icono);
        boton.setFocusPainted(false);
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PanelCuentas();
            }
        });
        return boton;
    }

    private  JButton crearBotonAforo(){
        JButton boton = new JButton("Aforo");
        String ruta = new File("").getAbsolutePath() + "\\imagenes\\aforo.png" ;
        ImageIcon icono = new ImageIcon(ruta);
        Image imagenLimitadaTamanyo = icono.getImage().getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
        icono.setImage(imagenLimitadaTamanyo);
        boton.setIcon(icono);
        boton.setFocusPainted(false);
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PanelAforo();
            }
        });
        return boton;
    }


}
