package pantallas.administrador;

import modelos.TipoCampoFormulario;
import utilidades.UtilidadesFormulario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FormularioEmpleado extends JFrame {

    private static final ImageIcon imagenPanelAdministracion = new ImageIcon(getRutaImagenFondo());


    public FormularioEmpleado() {
        super("Empleados");

        JPanel panelPrincipal = crearPanelImagenFondo();


        //Formulario
        JPanel panelExteriorFormulario = new JPanel(new BorderLayout());
        JPanel formulario = new JPanel(new GridLayout(0,1));
        formulario.setOpaque(false);
        formulario.setSize(new Dimension(300, 300));
        formulario.setBackground(Color.LIGHT_GRAY);
        UtilidadesFormulario.crearCampoFormulario(formulario,"id", TipoCampoFormulario.NUMERO);
        UtilidadesFormulario.crearCampoFormulario(formulario,"codigo", TipoCampoFormulario.TEXTO);
        UtilidadesFormulario.crearCampoFormulario(formulario,"nombre", TipoCampoFormulario.TEXTO);
        UtilidadesFormulario.crearCampoFormulario(formulario,"apellidos", TipoCampoFormulario.TEXTO);
        formulario.add(Box.createRigidArea(new Dimension(5, 0)));
        UtilidadesFormulario.crearGridBotones(formulario,crearBotonesFormulario());
        panelExteriorFormulario.setBorder(new EmptyBorder(100,100,100,100));
        panelExteriorFormulario.setOpaque(false);
        panelExteriorFormulario.add(formulario,BorderLayout.CENTER);
        panelPrincipal.add(panelExteriorFormulario, BorderLayout.CENTER);




        //ASPECTO
        setContentPane(panelPrincipal);//Panel de Fondo
        setBackground(Color.DARK_GRAY); //COLOR
        setLocationRelativeTo(null); //POSICION CENTRADA
        pack();
        setSize(500, 750); //TAMAÑO DE VENTANA
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
        return ruta + "\\imagenes\\formularios.jpg";
    }


    private List<JButton> crearBotonesFormulario(){
        List<JButton> listadoBotones = new ArrayList<>();
        JButton buscar = new JButton("Buscar");
        JButton guardar = new JButton("Guardar");
        JButton eliminar  = new JButton("Eliminar");
        listadoBotones.add(buscar);
        listadoBotones.add(guardar);
        listadoBotones.add(eliminar);
        return listadoBotones;
    }

}
