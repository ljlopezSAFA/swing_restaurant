package utilidades;

import modelos.TipoCampoFormulario;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;
import java.util.List;

public class UtilidadesFormulario {


    public UtilidadesFormulario() {
    }


    public static void crearCampoFormulario(JPanel panel, String nombre, TipoCampoFormulario tipoCampoFormulario,
                                            List<?> items){
        switch (tipoCampoFormulario){
            case TEXTO -> crearInputTexto(panel,nombre);
            case NUMERO -> crearCampoNumerico(panel,nombre);
            case COMBO -> crearCampoCombo(panel,nombre,items);
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
        jTextField.setName(nombre);
        panel.add(jTextField);
        return  jTextField;
    }

    public static JFormattedTextField crearCampoNumerico(JPanel panel,String nombre){
        JLabel etiqueta = crearEtiquetaFormulario(nombre);
        panel.add(etiqueta);
        JFormattedTextField numerico = new JFormattedTextField(new NumberFormatter(NumberFormat.getInstance()));
        etiqueta.setLabelFor(numerico);
        numerico.setName(nombre);
        panel.add(numerico);
        return  numerico;
    }

    public static  JComboBox crearCampoCombo(JPanel panel , String nombre, List<?> items){
        JLabel etiqueta = crearEtiquetaFormulario(nombre);
        panel.add(etiqueta);
        JComboBox comboBox = new JComboBox();
        items.forEach(comboBox::addItem);
        etiqueta.setLabelFor(comboBox);
        comboBox.setName(nombre);
        comboBox.setSize(200, comboBox.getPreferredSize().height);
        panel.add(comboBox);
        return  comboBox;
    }


    public static void crearGridBotones(JPanel panel,List<JButton> botones){
        JPanel panelBotones = new JPanel(new GridLayout(1,botones.size()));
        botones.forEach(panelBotones::add);
        panel.add(panelBotones);
    }

    public static Object obtenerTextoComponente(JPanel formulario , String nombre){
        Object valor = "";

        for(Component c: formulario.getComponents()){
            if(c instanceof JTextField){
                JTextField input = (JTextField) c;
                if(input.getName().equals(nombre)) {
                    valor = input.getText();
                }
            }else if(c instanceof JComboBox){
                JComboBox comboBox = (JComboBox) c;
                if(comboBox.getName().equals(nombre)) {
                    valor = comboBox.getModel().getSelectedItem();
                }
            }

        }

        return  valor;
    }

    public static void ponerValorComponente(JPanel formulario , String nombreComponente, Object valor){

        for(Component c: formulario.getComponents()){
            if(c instanceof JTextField){
                JTextField input = (JTextField) c;
                if(input.getName().equals(nombreComponente)) {
                    input.setText(valor.toString());
                }
            }else if(c instanceof JComboBox){
                JComboBox comboBox = (JComboBox) c;
                if(comboBox.getName().equals(nombreComponente)) {
                    comboBox.getModel().setSelectedItem(valor);
                }
            }
        }
    }




}
