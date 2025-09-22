package interfacesGraficas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import herramientas.constantes.ConstantesDelJuego;
import principal.Main;
import principal.PanelDelJuego;
import herramientas.HerramientaDeGuardado;

import static principal.Main.ventana;

public class Nivel extends JPanel {
    public static int numeroDeNivelACrear;
    private JPanel pnlFondo;
    private JButton btnMenu;
    private JPanel pnlBotones;
    protected JButton btnReinicio;
    private JLabel lblMovimiento;
    private JPanel pnlMovimiento;
    private JPanel pnlJuego;
    private JButton btnArriba;
    private JButton btnDerecha;
    private JButton btnIzquierda;
    private JButton btnAbajo;
    private JButton bntPausa;

    private static int contadorDeMovimientos = 1;


    private static PanelDelJuego panelGeneral;
    protected static boolean esNivelGuardado;
    //Mandale el mapa
    private MenuPausa menu;

    public Nivel() {
        panelGeneral = new PanelDelJuego();
        reemplazarPanel();
        menu = new MenuPausa(panelGeneral);
        pnlMovimiento.setVisible(false); //oculatar botones de movimiento
        btnMenu.setVisible(false);
        btnMenu.addActionListener(actionEvent -> {
            Main.ventana.dispose(); //regresa al menu principal
            Main.crearMenu();
        });

        controlarMovimientos();

        crearBotonReinicio();

        pausaElJuego();
    }

    private void pausaElJuego() {
        bntPausa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ventana.setVisible(false);
                crearVentanaDePausa(Main.ventanaPausa);

            }
        });
    }

    private void controlarMovimientos() {
        // movimieno arriba
        btnArriba.addActionListener(e -> panelGeneral.actualizarArriba());
        //Movimiento izquierda
        btnIzquierda.addActionListener(e -> panelGeneral.actualizarIzquierda());
        //movimiento abajo
        btnAbajo.addActionListener(e -> panelGeneral.actualizarAbajo());
        //movimiento derecha
        btnDerecha.addActionListener(e -> panelGeneral.actualizarDerecha());
    }

    public static void crearNivel(JFrame ventana,int numeroDeNivelACrear,int numeroDeMovimientos) {

        ArrayList<Integer> datosNivel = HerramientaDeGuardado.leerDatosDeNivel();
        Nivel.numeroDeNivelACrear = datosNivel.get(0);
        setContadorDeMovimientos(datosNivel.get(1));
        crearVentanaDeNiveles(ventana);


    }


    private void crearBotonReinicio() {
        AtomicInteger temporalContadorDeMovimiento = new AtomicInteger();
        btnReinicio.addActionListener(actionEvent -> {
            crearNivel(Main.ventana, numeroDeNivelACrear);
            reiniciarContadorDeMovimientos(temporalContadorDeMovimiento);
        });

        btnReinicio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                controlarMovimientoEnPantalla(ke);

            }

            private void controlarMovimientoEnPantalla(KeyEvent e) {
                super.keyPressed(e);

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        incrementarMovimientosEnPantalla();
                        btnArriba.doClick();
                        break;
                    case KeyEvent.VK_DOWN:
                        incrementarMovimientosEnPantalla();
                        btnAbajo.doClick();
                        break;
                    case KeyEvent.VK_RIGHT:
                        incrementarMovimientosEnPantalla();
                        btnDerecha.doClick();
                        break;
                    case KeyEvent.VK_LEFT:
                        incrementarMovimientosEnPantalla();
                        btnIzquierda.doClick();
                        break;

            }
            }

        });
    }

    private void reiniciarContadorDeMovimientos(AtomicInteger temporalContadorDeMovimiento) {
        switch (numeroDeNivelACrear) {
            case 1:
                Nivel.setContadorDeMovimientos();
                break;
            case 2:
            case 3:
                temporalContadorDeMovimiento.set(Nivel.getContadorDeMovimientos());
                Nivel.setContadorDeMovimientos(temporalContadorDeMovimiento.get());

                break;
        }
    }

    private void incrementarMovimientosEnPantalla() {
        lblMovimiento.setText("Movimientos: " + contadorDeMovimientos);
        contadorDeMovimientos++;
    }


    private void reemplazarPanel() {
        pnlJuego.setLayout(null);//Desactivo el diseño original del panel del juego

        //establezco valores para el panel

        panelGeneral.setSize(ConstantesDelJuego.ANCHO_PANEL_DE_JUEGO.getValor(),ConstantesDelJuego.ALTO_PANEL_DE_JUEGO.getValor());
        panelGeneral.setLocation(0, 0);
        //reemplazo el panel del juego (pnlJuego) por el panel del juego runnable (panelDelJuego)
        pnlJuego.removeAll();
        pnlJuego.add(panelGeneral, BorderLayout.CENTER);
        pnlJuego.revalidate();
        pnlJuego.repaint();
    }
    private void crearVentanaDePausa(JFrame ventanaOpciones) {

        ventanaOpciones.setTitle("Pausa");
        ventanaOpciones.setContentPane(new MenuPausa(panelGeneral).pnlMenuOpciones);
        ventanaOpciones.setPreferredSize(new Dimension(450, 350)); // Definimos un tamaño preferencial
        ventanaOpciones.setLocationRelativeTo(null);// me lo pone en el centro
        ventanaOpciones.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Para cerrar la ventana y parar la ejecución del programa
        ventanaOpciones.getContentPane().setBackground(Color.DARK_GRAY); // ponemos color a la ventana que creamos
        ventanaOpciones.pack();
        ventanaOpciones.setVisible(true);
    }
    public static void crearNivel(JFrame ventana, int numeroDeNivelACrear) {
        Nivel.numeroDeNivelACrear = numeroDeNivelACrear;
        crearVentanaDeNiveles(ventana);

    }

    private static void crearVentanaDeNiveles(JFrame ventana) {
        ventana.setTitle("SOKOBAN");
        ventana.setContentPane(new Nivel().pnlFondo);
        ventana.setLocationRelativeTo(null); //que salga en el medio
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ventana.setResizable(false); // Que no se pueda extender ni cambiar el tamaño de la ventana
        ventana.setPreferredSize(new Dimension(ConstantesDelJuego.ANCHO_PANEL_DE_JUEGO.getValor(), ConstantesDelJuego.ALTO_PANEL_DE_JUEGO.getValor()+ ConstantesDelJuego.ALTO_DE_PANEL_DE_BOTONES.getValor()));
        //Vamos a crear el fondo
        ventana.pack();
        ventana.setVisible(true);
    }

    public static int getContadorDeMovimientos() {
        return contadorDeMovimientos;
    }

    public static void setContadorDeMovimientos() {
        Nivel.contadorDeMovimientos = 1;
    }
    public static void setContadorDeMovimientos(int contadorDeMovimientos) {
        Nivel.contadorDeMovimientos = contadorDeMovimientos;
    }

    public static void setNumeroDeNivelACrear(int numeroDeNivelACrear) {
        Nivel.numeroDeNivelACrear = numeroDeNivelACrear;
    }

    public static boolean isEsNivelGuardado() {
        return esNivelGuardado;
    }

    public static void setEsNivelGuardado(boolean esNivelGuardado) {
        Nivel.esNivelGuardado = esNivelGuardado;
    }
}
