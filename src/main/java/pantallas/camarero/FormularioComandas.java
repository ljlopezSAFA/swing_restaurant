package pantallas.camarero;

import modelos.Producto;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class FormularioComandas extends JFrame {

    public FormularioComandas(){
        super("TABERNA LA ESQUINITA ");

        //PANEL PRINCIPAL
        JPanel panelPrincipal = new JPanel();

        //PANEL FORMULARIOtablaComanda
        JPanel formulario1 = new JPanel();
        formulario1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JPanel formulario2 = new JPanel();
        formulario2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        //MESAS Y CAMAREROS
        JLabel etiqueteCamaarero = new JLabel("Selecciona Camarero");
        JComboBox comboCamarero = new JComboBox();
        rellenarComboCamarero(comboCamarero);
        formulario1.add(etiqueteCamaarero);
        formulario1.add(comboCamarero);
        JLabel etiquetaMesas = new JLabel("Selecciona Mesa");
        JComboBox comboMesas = new JComboBox();
        rellenarComboMesas(comboMesas);
        formulario1.add(etiquetaMesas);
        formulario1.add(comboMesas);
        JSeparator separador = new JSeparator();
        separador.setOrientation(SwingConstants.HORIZONTAL);
        formulario1.add(separador);


        //PRODUCTOS
        JLabel etiquetaProducto = new JLabel("Selecciona Producto");
        JComboBox comboProducto = new JComboBox();
        rellenarComboProductos(comboProducto);
        JLabel etiquetaLabel = new JLabel("Cantidad");
        SpinnerModel model = new SpinnerNumberModel(1, 1, 100, 1);
        JSpinner numeroProductos = new JSpinner(model);
        JButton botonAñadir = new JButton("Añadir");
        formulario2.add(etiquetaProducto);
        formulario2.add(comboProducto);
        formulario2.add(etiquetaLabel);
        formulario2.add(numeroProductos);
        formulario2.add(botonAñadir);




        panelPrincipal.add(formulario1);
        panelPrincipal.add(formulario2);





        //TABLA COMANDA
        Object[] columnas = {"Producto",
                "Cantidad",
                "Importe por unidad"};
        Object[][] datos = {{"","",""}};
        JTable tablaComanda = new JTable(datos , columnas);

        //PANEL TABLA COMANDA
        JScrollPane scrollPane = new JScrollPane(tablaComanda);
        tablaComanda.setFillsViewportHeight(true);

        panelPrincipal.add(scrollPane);

        

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


    private void rellenarComboCamarero(JComboBox comboBox){
        comboBox.addItem("Camarero 1");
        comboBox.addItem("Camarero 2");
    }

    private void rellenarComboMesas(JComboBox comboBox){
        comboBox.addItem("Mesa 1");
        comboBox.addItem("Mesa 2");
        comboBox.addItem("Mesa 3");
        comboBox.addItem("Mesa 4");

    }

    private void rellenarComboProductos(JComboBox comboBox){
        comboBox.addItem("Coca Cola");
        comboBox.addItem("Fanta");
        comboBox.addItem("Calamares");
        comboBox.addItem("Solomillo al Whisky");

    }




}
