package pantallas.administrador;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PanelGestionAdministrador extends JFrame {

    private static final ImageIcon imagenPanelAdministracion = new ImageIcon(getRutaImagenFondo());


    public PanelGestionAdministrador(){

        JPanel panelPrincipal =crearPanelImagenFondo() ;


        setSize(new Dimension(1250,800));
        setContentPane(panelPrincipal);
        setVisible(true);
    }




    private JPanel crearPanelImagenFondo(){
        JPanel panel = new JPanel(){
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



}
