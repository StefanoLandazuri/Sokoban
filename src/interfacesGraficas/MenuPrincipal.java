package interfacesGraficas;

import principal.Main;
import herramientas.constantes.Mapa;
import herramientas.HerramientaDeGuardado;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class MenuPrincipal {
    public JPanel pnlMenuPrincipal;
    private JLabel lblMenu;
    private JButton btnInicio;
    private JButton btnSeleccionarNivel;
    private JButton btnCreditos;
    private JLabel lblImagen;
    private JButton btnCargarPartida;
    private JButton btnSalir;
    private JButton btnInstruccion;

    public MenuPrincipal(JFrame ventana) {

        btnInicio.addActionListener(actionEvent -> {
            JOptionPane.showMessageDialog(pnlMenuPrincipal, "Se ha iniciado el juego");
            crearNivel(ventana);

        });


        btnSeleccionarNivel.addActionListener(actionEvent -> {

            Main.ventana.setContentPane(new SelectorNivel().pnlSelectorNivel);
            Main.ventana.pack();
            Main.ventana.setVisible(true);

        });

        btnCreditos.addActionListener(actionEvent -> {
            JOptionPane.showMessageDialog(pnlMenuPrincipal, "Grupo 7");
        });

        btnCargarPartida.addActionListener(actionEvent -> {
            Nivel.setEsNivelGuardado(true);
            // Main.esNivelGuardado = true;
            ArrayList<Integer> datosNivel = HerramientaDeGuardado.leerDatosDeNivel();
            crearNivel(ventana, datosNivel.get(0), datosNivel.get(1));

        });
        btnSalir.addActionListener(actionEvent -> {
            int opcion = JOptionPane.showConfirmDialog(Main.ventana,
                    "¿Estas seguro de que quieres salir?",
                    "Salir",
                    JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }

        });
        btnInstruccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(pnlMenuPrincipal,
                        "                                                                                 Instrucciones\n" +
                                "1. El jugador debe LLEVAR la caja a LA META.\n" +
                                "2. No se pueden empujar más de dos cajas a la vez.\n" +
                                "3. Para reiniciar el juego, aplastar el botón reiniciar y mantenerlo aplastado por 2 segundos antes de volver a jugar.\n" +
                                "4. La última caja en llegar a la meta debe ser empujada una vez más para pasar al siguiente nivel.\n" +
                                "5. Al usar el botón de pausa se debe mantener aplastado el botón de reanudar por 2 segundos para volver a jugar.\n");
            }
        });
    }

    private void crearNivel(JFrame ventana) {
        Nivel.crearNivel(ventana, Mapa.MAPA1.getNumeroDeMapa());
    }

    private void crearNivel(JFrame ventana, int numeroDeNivel, int numeroDeMovimientos) {

        Nivel.crearNivel(ventana, numeroDeNivel, numeroDeMovimientos);
    }

}
