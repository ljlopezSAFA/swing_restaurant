import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PanelGestionCamarero extends JFrame {

    private static final ImageIcon fondoPantalla = new ImageIcon(getRutaImagenFondo()) ;



    public PanelGestionCamarero(){
        super("TABERNA LA ESQUINITA - Panel Gestión Camarero");

        //PANEL DE FONDO
        JPanel panelPrincipal = crearPanelImagenFondo();


        //BOTONES
//        JPanel panelBotones = new JPanel(new BorderLayout());
//        panelBotones.setBorder(new EmptyBorder(100,100,100,100));
//        panelBotones.setOpaque(false);
//        botonAccederCamarero = crearBotonCamarero();
//        botonAccederCocinero = crearBotonCocinero();
//        panelBotones.add(botonAccederCocinero,BorderLayout.EAST);
//        panelBotones.add(botonAccederCamarero,BorderLayout.WEST);
//        panelPrincipal.add(panelBotones,BorderLayout.CENTER);




        //ASPECTO
        setContentPane(panelPrincipal);//Panel de Fondo
        setVisible(true); //VISIBILIDAD
        setBackground(Color.DARK_GRAY); //COLOR
       setLocationRelativeTo(null); //POSICION CENTRADA
        setSize(new Dimension(1250,800)); //TAMAÑO DE VENTANA


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
        return ruta + "\\imagenes\\panel_camarero_2.jpg";


    }



}
