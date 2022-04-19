package pantallas.cliente;

import bbdd.ProductoBD;
import modelos.Producto;
import modelos.TipoProducto;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CartaRestaurante extends JFrame {

    private static final ImageIcon fondoPantalla = new ImageIcon(getRutaImagenFondo()) ;

    public CartaRestaurante(){
        super("TABERNA LA ESQUINITA - Carta");

        //PANEL DE FONDO
        JPanel panelPrincipal = crearPanelImagenFondo();


        //PANEL POR PESTAÑAS
        JTabbedPane pestanyas = new JTabbedPane();
        pestanyas.setOpaque(false);

        //Obtenemos los productos
        List<Producto> listaProductos = ProductoBD.obtenerProductos();
        Map<TipoProducto, List<Producto>> productosPorTipo = listaProductos.stream().collect(Collectors.groupingBy(Producto::getTipoProducto));

        //Creamos las pestanyas por tipoProducto
        for(TipoProducto tipoProducto : productosPorTipo.keySet()){
            JPanel panelpestana = crearPanelImagenFondo();
            panelpestana.setLayout(new BorderLayout());
            JPanel panelProductos = new JPanel(new GridLayout(0,2));
            panelProductos.setBorder(BorderFactory.createEmptyBorder(200,100,200,300));
            panelProductos.setOpaque(false);

            for(Producto p : productosPorTipo.get(tipoProducto)){
                JLabel nombreProducto = new JLabel(p.getDescripcion());
                nombreProducto.setForeground(Color.WHITE);
                nombreProducto.setFont(new Font("Comic Sans", Font.PLAIN, 24));
                JLabel precio = new JLabel(p.getPrecio().toString() + "€");
                precio.setForeground(Color.WHITE);
                precio.setFont(new Font("Comic Sans", Font.PLAIN, 24));
                panelProductos.add(nombreProducto);
                panelProductos.add(precio);
            }
            panelpestana.add(panelProductos,BorderLayout.CENTER);
            pestanyas.add(tipoProducto.toString(),panelpestana);

        }


        panelPrincipal.add(pestanyas);



        //ASPECTO
        setContentPane(panelPrincipal);//Panel de Fondo
        setBackground(Color.DARK_GRAY); //COLOR
        setLocationRelativeTo(null); //POSICION CENTRADA
        pack();
        setSize(1200,800); //TAMAÑO DE VENTANA
        setResizable(false);
        setLocationRelativeTo(null); //POSICION CENTRADA
        setVisible(true); //VISIBILIDAD

    }

    private JPanel crearPanelImagenFondo(){
        Image resizableImagen = fondoPantalla.getImage().getScaledInstance(1200,800,0);
        fondoPantalla.setImage(resizableImagen);
        JPanel panel = new JPanel(new BorderLayout()){
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondoPantalla.getImage(), 0, 0, null);
            }
        };
        return panel;

    }


    private static String getRutaImagenFondo(){
        String ruta = new File("").getAbsolutePath();
        return ruta + "\\imagenes\\carta.jpg";
    }
}