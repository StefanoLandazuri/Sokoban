package sokoban;

import herramientas.constantes.ConstantesDelJuego;

import java.io.Serializable;

import static herramientas.constantes.ConstantesDelJuego.DIMENSION_DE_SPRITE;

public class Posicion implements Serializable {

    private int x;
    private int y;

    public Posicion(int x, int y) {
        this.x = x * DIMENSION_DE_SPRITE.getValor();
        this.y = y * DIMENSION_DE_SPRITE.getValor();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return ""+ x/DIMENSION_DE_SPRITE.getValor() + "\n"+y/DIMENSION_DE_SPRITE.getValor();

    }
}
