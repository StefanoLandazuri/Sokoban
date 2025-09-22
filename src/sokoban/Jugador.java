package sokoban;

import herramientas.constantes.Direccion;

import javax.imageio.ImageIO;
import java.util.Objects;


public class Jugador extends SpriteMovible {


    public Jugador(Posicion posicionDelJugador) {
        posicion = posicionDelJugador;
        obtenerImagen();
    }

    private void obtenerImagen() {
        try {
            imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(Direccion.DIRECCION_IMAGEN_JUGADOR.getDirección())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
