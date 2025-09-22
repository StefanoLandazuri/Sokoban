package principal;

import sokoban.GestorDeSprites;
import herramientas.constantes.ConstantesDelJuego;

import javax.swing.*;
import java.awt.*;


public class PanelDelJuego extends JPanel {


    protected GestorDeSprites gestorDeSprites = new GestorDeSprites(this);


    public PanelDelJuego() {
        this.setLayout(null);
        this.setBackground(new Color(100, 158, 61)); // Utilizamos RGB para establecer el color del fondo
        this.setDoubleBuffered(true); //  bufer de pintura fuera de la pantalla, para renderizar mejor
        this.setPreferredSize(new Dimension(ConstantesDelJuego.ANCHO_PANEL_DE_JUEGO.getValor(), ConstantesDelJuego.ALTO_PANEL_DE_JUEGO.getValor()));
        this.setFocusable(true);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pincel = (Graphics2D) g;
        gestorDeSprites.dibujarFondo(pincel);
        gestorDeSprites.dibujarSpritesMovibles(pincel);
        pincel.dispose();

    }


    public void actualizarDerecha() {
        gestorDeSprites.actualizarDerecha();
        repaint();
    }

    public void actualizarArriba() {
        gestorDeSprites.actualizarPorArriba();
        repaint();
    }

    public void actualizarAbajo() {
        gestorDeSprites.actualizarPorAbajo();
        repaint();
    }

    public void actualizarIzquierda() {
        gestorDeSprites.actualizarPorIzquierda();
        repaint();
    }

    public GestorDeSprites getGestorDeSprites() {
        return gestorDeSprites;
    }


}
