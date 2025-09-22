package interfacesGraficas;

import herramientas.constantes.Mapa;
import principal.Main;

import javax.swing.*;

public class SelectorNivel {
    private JButton btnNivel1;
    private JButton btnNivel2;
    private JButton btnNivel3;
    public JPanel pnlSelectorNivel;

    public SelectorNivel() {

        seleccionarNivel();
    }

    private void seleccionarNivel() {
        btnNivel1.addActionListener(actionEvent -> Nivel.crearNivel(Main.ventana, Mapa.MAPA1.getNumeroDeMapa()));
        btnNivel2.addActionListener(actionEvent -> Nivel.crearNivel(Main.ventana, Mapa.MAPA2.getNumeroDeMapa()));
        btnNivel3.addActionListener(actionEvent -> Nivel.crearNivel(Main.ventana, Mapa.MAPA3.getNumeroDeMapa()));
    }


}
