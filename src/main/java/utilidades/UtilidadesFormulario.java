package utilidades;

import modelos.TipoCampoFormulario;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UtilidadesFormulario {


    public UtilidadesFormulario() {
    }


    public static void crearCampoFormulario(JPanel panel, String nombre, TipoCampoFormulario tipoCampoFormulario){
        switch (tipoCampoFormulario){
            case TEXTO -> crearInputTexto(panel,nombre);
            case NUMERO -> crearCampoNumerico(panel,nombre);
        }
    }


    public static JLabel crearEtiquetaFormulario(String nombre){
        JLabel etiqueta = new JLabel(nombre, SwingConstants.CENTER);
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setHorizontalTextPosition(2);
        return  etiqueta;
    }

    public static JTextField crearInputTexto(JPanel panel, String nombre){
        JLabel etiqueta = crearEtiquetaFormulario(nombre);
        panel.add(etiqueta);
        JTextField jTextField = new JTextField(6);
        etiqueta.setLabelFor(jTextField);
        panel.add(jTextField);
        return  jTextField;
    }

    public static JSpinner crearCampoNumerico(JPanel panel,String nombre){
        JLabel etiqueta = crearEtiquetaFormulario(nombre);
        panel.add(etiqueta);
        SpinnerNumberModel modelSpinner = new SpinnerNumberModel(0, 0, 10, 1);
        JSpinner jSpinner = new JSpinner(modelSpinner);
        etiqueta.setLabelFor(jSpinner);
        panel.add(jSpinner);
        return  jSpinner;
    }


    public static void crearGridBotones(JPanel panel,List<JButton> botones){
        JPanel panelBotones = new JPanel(new GridLayout(1,botones.size()));
        botones.forEach(panelBotones::add);
        panel.add(panelBotones);
    }


}
