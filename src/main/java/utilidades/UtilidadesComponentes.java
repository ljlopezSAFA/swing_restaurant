package utilidades;
import bbdd.MesaBD;
import modelos.Mesa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class UtilidadesComponentes {

    public UtilidadesComponentes() {
    }

    public static  JButton crearBotonMesa(Mesa mesa){
            JButton boton = new JButton(mesa.toString());
            boton.setName(String.valueOf(mesa.getId()));
            String ruta = new File("").getAbsolutePath() + "\\imagenes\\mesa_incono.png" ;
            ImageIcon icono = new ImageIcon(ruta);
            Image imagenLimitadaTamanyo = icono.getImage().getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
            icono.setImage(imagenLimitadaTamanyo);
            boton.setIcon(icono);
            boton.setFocusPainted(false);
            boton.setBackground(mesa.isOcupada()? Color.RED : Color.GREEN);
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MesaBD.ocuparDesocuparMesa(mesa);
                    mesa.setOcupada(!mesa.isOcupada());
                    boton.setBackground(mesa.isOcupada()? Color.RED : Color.GREEN);
                    boton.repaint();
                }
            });
            return boton;

    }


    public static void anyadirEspacioBlanco(JPanel panel){
        panel.add(Box.createRigidArea(new Dimension(5, 0)));
    }
}
