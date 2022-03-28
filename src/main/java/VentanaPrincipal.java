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


    public VentanaPrincipal(){
        super("TABERNA LA ESQUINITA ");

        //PANEL DE FONDO
        JPanel panelPrincipal = crearPanelImagenFondo();


        //BOTONES
        JPanel panelBotones = new JPanel(new BorderLayout());
        panelBotones.setBorder(new EmptyBorder(100,100,100,100));
        panelBotones.setOpaque(false);
        botonAccederCamarero = crearBotonCamarero();
        botonAccederCocinero = crearBotonCocinero();
        panelBotones.add(botonAccederCocinero,BorderLayout.EAST);
        panelBotones.add(botonAccederCamarero,BorderLayout.WEST);
        panelPrincipal.add(panelBotones,BorderLayout.CENTER);




        //ASPECTO
        setContentPane(panelPrincipal);//Panel de Fondo
        setVisible(true); //VISIBILIDAD
        setDefaultCloseOperation(EXIT_ON_CLOSE); //EXIT ON CLOSE
        setBackground(Color.DARK_GRAY); //COLOR
        setLocationRelativeTo(null); //POSICION CENTRADA
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
        ImageIcon imagenCamarero = new ImageIcon(ruta);
        boton.setIcon(imagenCamarero);
        boton.addActionListener(new AccionAbrirMenuCamarero());
        return boton;
    }


    private  JButton crearBotonCocinero(){
        JButton boton = new JButton("Cocinero");
        String ruta = new File("").getAbsolutePath() + "\\imagenes\\cocinero.png" ;
        ImageIcon imagenCamarero = new ImageIcon(ruta);
        boton.setIcon(imagenCamarero);
        boton.addActionListener(new AccionAbrirMenuCocinero());
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


}




