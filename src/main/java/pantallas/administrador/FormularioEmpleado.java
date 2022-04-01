package pantallas.administrador;

import bbdd.UtilidadesBD;
import modelos.Empleado;
import modelos.TipoCampoFormulario;
import utilidades.UtilidadesFormulario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        UtilidadesFormulario.crearGridBotones(formulario,crearBotonesFormulario(formulario));
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


    private List<JButton> crearBotonesFormulario(JPanel formulario){
        List<JButton> listadoBotones = new ArrayList<>();

        //BUSCAR
        JButton buscar = new JButton("Buscar");
        buscar.addActionListener(new ActionListener( ) {
            public void actionPerformed(ActionEvent e) {
                Integer idEmpleado = Integer.valueOf(UtilidadesFormulario.obtenerTextoComponente(formulario,"id"));
                Empleado empleado = UtilidadesBD.obtenerPorId(idEmpleado);
                if(empleado!= null) {
                    cambiarDatosFormulario(formulario, String.valueOf(empleado.getId()), empleado.getCodigoEmpleado(), empleado.getNombre(), empleado.getApellidos());
                }
                else{
                    cambiarDatosFormulario(formulario, "", "", "", "");
                }
            }
        });


        //Guardar
        JButton guardar = new JButton("Guardar");
        guardar.addActionListener(new ActionListener( ) {
            public void actionPerformed(ActionEvent e) {
                Empleado empleado = new Empleado();
                empleado.setId(Integer.parseInt(UtilidadesFormulario.obtenerTextoComponente(formulario,"id")));
                empleado.setNombre(UtilidadesFormulario.obtenerTextoComponente(formulario,"nombre"));
                empleado.setApellidos(UtilidadesFormulario.obtenerTextoComponente(formulario,"apellidos"));
                empleado.setCodigoEmpleado(UtilidadesFormulario.obtenerTextoComponente(formulario,"codigo"));
                UtilidadesBD.crearActualizarEmpleado(empleado);
                cambiarDatosFormulario(formulario,"","","","");
            }
        });

        //Eliminar
        JButton eliminar  = new JButton("Eliminar");
        eliminar.addActionListener(new ActionListener( ) {
            public void actionPerformed(ActionEvent e) {
                Empleado empleado = new Empleado();
                empleado.setId(Integer.parseInt(UtilidadesFormulario.obtenerTextoComponente(formulario,"id")));
                UtilidadesBD.eliminarEmpleado(empleado);
                cambiarDatosFormulario(formulario,"","","","");
            }
        });

        listadoBotones.add(buscar);
        listadoBotones.add(guardar);
        listadoBotones.add(eliminar);
        return listadoBotones;
    }

    private void cambiarDatosFormulario(JPanel formulario, String id, String codigo, String nombre, String apellidos) {
        UtilidadesFormulario.ponerValorComponente(formulario, "id", id);
        UtilidadesFormulario.ponerValorComponente(formulario, "codigo", codigo);
        UtilidadesFormulario.ponerValorComponente(formulario, "nombre", nombre);
        UtilidadesFormulario.ponerValorComponente(formulario, "apellidos", apellidos);
    }


}
