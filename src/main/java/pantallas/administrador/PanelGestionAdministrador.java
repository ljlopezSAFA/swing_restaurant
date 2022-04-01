package pantallas.administrador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PanelGestionAdministrador extends JFrame {

    private static final ImageIcon imagenPanelAdministracion = new ImageIcon(getRutaImagenFondo());
    private JButton botonMesas;
    private JButton botonEmpleados;
    private JButton botonProductos;


    public PanelGestionAdministrador() {

        JPanel panelPrincipal = crearPanelImagenFondo();


        //BOTONES
        JPanel panelBotonesAdministrador = new JPanel();
        panelBotonesAdministrador.setBorder(new EmptyBorder(100,100,100,100));
        panelBotonesAdministrador.setOpaque(false);
        botonMesas = crearBotonMesas();
        botonEmpleados = crearBotonEmpleados();
        botonProductos = crearBotonProductos();
        panelBotonesAdministrador.add(botonMesas);
        panelBotonesAdministrador.add(botonEmpleados);
        panelBotonesAdministrador.add(botonProductos);
        panelPrincipal.add(panelBotonesAdministrador,BorderLayout.WEST);


        //ASPECTO
        setContentPane(panelPrincipal);//Panel de Fondo
        setBackground(Color.DARK_GRAY); //COLOR
        setLocationRelativeTo(null); //POSICION CENTRADA
        pack();
        setSize(1250, 800); //TAMAÑO DE VENTANA
        setResizable(false);
        setLocationRelativeTo(null); //POSICION CENTRADA
        setVisible(true); //VISIBILIDAD

    }


    private JPanel crearPanelImagenFondo(){
        JPanel panel = new JPanel(new BorderLayout()){
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagenPanelAdministracion.getImage(), 0, 0, null);
            }
        };

        return panel;

    }

    private static String getRutaImagenFondo(){
        String ruta = new File("").getAbsolutePath();
        return ruta + "\\imagenes\\adminFondo.jpg";
    }

    private  JButton crearBotonMesas(){
        JButton boton = new JButton("Mesas");
        String ruta = new File("").getAbsolutePath() + "\\imagenes\\mesa.png" ;
        ImageIcon icono = new ImageIcon(ruta);
        Image imagenLimitadaTamanyo = icono.getImage().getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
        icono.setImage(imagenLimitadaTamanyo);
        boton.setIcon(icono);
        boton.setFocusPainted(false);
        //boton.addActionListener();
        return boton;
    }

    private  JButton crearBotonEmpleados(){
        JButton boton = new JButton("Empleados");
        String ruta = new File("").getAbsolutePath() + "\\imagenes\\tarjeta_empleado.png" ;
        ImageIcon icono = new ImageIcon(ruta);
        Image imagenLimitadaTamanyo = icono.getImage().getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
        icono.setImage(imagenLimitadaTamanyo);
        boton.setIcon(icono);
        boton.setFocusPainted(false);
        boton.addActionListener(new AccionAbrirFormularioEmpleados());
        return boton;
    }

    private  JButton crearBotonProductos(){
        JButton boton = new JButton("Productos");
        String ruta = new File("").getAbsolutePath() + "\\imagenes\\paella.png" ;
        ImageIcon icono = new ImageIcon(ruta);
        Image imagenLimitadaTamanyo = icono.getImage().getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
        icono.setImage(imagenLimitadaTamanyo);
        boton.setIcon(icono);
        boton.setFocusPainted(false);
        //boton.addActionListener();
        return boton;
    }


    class AccionAbrirFormularioEmpleados implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            new FormularioEmpleado();
        }
    }





}
