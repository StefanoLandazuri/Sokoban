package sokoban;

import herramientas.constantes.ConstantesDelJuego;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract  class Sprite extends JLabel implements Serializable {

    protected transient BufferedImage imagen;
    public int dimensionDeSprite = ConstantesDelJuego.DIMENSION_DE_SPRITE.getValor();
    protected Posicion posicion;

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }


    public void dibujar(Graphics2D g2){
        g2.drawImage(imagen,posicion.getX(),posicion.getY(),dimensionDeSprite,dimensionDeSprite,null);
    }

    public Posicion getPosicion() {
        return posicion;
    }
}
