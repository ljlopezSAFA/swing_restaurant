package pantallas;

import pantallas.administrador.PanelGestionAdministrador;
import pantallas.camarero.PanelGestionCamarero;
import pantallas.cocinero.PanelGestionCocinero;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class VentanaPrincipal extends JFrame {

    private static final ImageIcon fondoPantalla = new ImageIcon(getRutaImagenFondo()) ;
    private JButton botonAccederCamarero;
    private JButton botonAccederCocinero;
    private JButton botonAccederAdministrador;
    private JButton botonAccederCliente;


    public VentanaPrincipal(){
        super("TABERNA LA ESQUINITA ");

        //PANEL DE FONDO
        JPanel panelPrincipal = crearPanelImagenFondo();


        //BOTONES
        JPanel panelBotones = new JPanel(new BorderLayout());
        panelBotones.setBorder(new EmptyBorder(100,100,100,100));
        panelBotones.setOpaque(false);

        JPanel subPanel1 = new JPanel(new BorderLayout());
        subPanel1.setOpaque(false);
        botonAccederCamarero = crearBotonCamarero();
        botonAccederCocinero = crearBotonCocinero();
        subPanel1.add(botonAccederCocinero,BorderLayout.EAST);
        subPanel1.add(botonAccederCamarero,BorderLayout.WEST);

        JPanel subPanel2 = new JPanel(new BorderLayout());
        subPanel2.setOpaque(false);
        botonAccederAdministrador = crearBotonAdministrador() ;
        botonAccederCliente = crearBotonCliente();
        subPanel2.add(botonAccederCliente,BorderLayout.EAST);
        subPanel2.add(botonAccederAdministrador,BorderLayout.WEST);


        panelBotones.add(subPanel1,BorderLayout.NORTH);
        panelBotones.add(subPanel2,BorderLayout.SOUTH);
        panelPrincipal.add(panelBotones,BorderLayout.CENTER);




        //ASPECTO
        setContentPane(panelPrincipal);//Panel de Fondo
        setVisible(true); //VISIBILIDAD
        setDefaultCloseOperation(EXIT_ON_CLOSE); //EXIT ON CLOSE
        setBackground(Color.DARK_GRAY); //COLOR
        //setLocationRelativeTo(null); //POSICION CENTRADA
        setSize(new Dimension(700,400)); //TAMAÑO DE VENTANA


    }


    private JPanel crearPanelImagenFondo(){
        JPanel panel = new JPanel(){
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondoPantalla.getImage(), 0, 0, null);
            }
        };

        return panel;


    }


    private static String getRutaImagenFondo(){
        String ruta = new File("").getAbsolutePath();
        return ruta + "\\imagenes\\taberna.jpg";
    }

    private  JButton crearBotonCamarero(){
        JButton boton = new JButton("Camarero");
        String ruta = new File("").getAbsolutePath() + "\\imagenes\\camarero.png" ;
        ImageIcon imagen = new ImageIcon(ruta);
        Image imagenLimitadaTamanyo = imagen.getImage().getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH);
        imagen.setImage(imagenLimitadaTamanyo);
        boton.setIcon(imagen);
        boton.addActionListener(new AccionAbrirMenuCamarero());
        return boton;
    }


    private  JButton crearBotonCocinero(){
        JButton boton = new JButton("Cocinero");
        String ruta = new File("").getAbsolutePath() + "\\imagenes\\cocinero.png" ;
        ImageIcon imagen = new ImageIcon(ruta);
        Image imagenLimitadaTamanyo = imagen.getImage().getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH);
        imagen.setImage(imagenLimitadaTamanyo);
        boton.setIcon(imagen);
        boton.addActionListener(new AccionAbrirMenuCocinero());
        return boton;
    }

    private JButton crearBotonCliente(){
        JButton boton = new JButton("Cliente");
        String ruta = new File("").getAbsolutePath() + "\\imagenes\\cliente.png";
        ImageIcon imageIcon = new ImageIcon(ruta);
        ImageIcon imagen = new ImageIcon(ruta);
        Image imagenLimitadaTamanyo = imagen.getImage().getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH);
        imagen.setImage(imagenLimitadaTamanyo);
        boton.setIcon(imagen);
        return boton;
    }

    private JButton crearBotonAdministrador(){
        JButton boton = new JButton("Administrador");
        String ruta = new File("").getAbsolutePath() + "\\imagenes\\administrador.png";
        ImageIcon imagen = new ImageIcon(ruta);
        Image imagenLimitadaTamanyo = imagen.getImage().getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH);
        imagen.setImage(imagenLimitadaTamanyo);
        boton.setIcon(imagen);
        boton.addActionListener(new AccionAbrirMenuAdministrador());
        return boton;
    }


    class AccionAbrirMenuCamarero implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
           new PanelGestionCamarero();
        }
    }


    class AccionAbrirMenuCocinero implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            new PanelGestionCocinero();
        }
    }

    class AccionAbrirMenuAdministrador implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            new PanelGestionAdministrador();
        }
    }


}




