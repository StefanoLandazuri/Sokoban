package sokoban;


public class Caja extends SpriteMovible{


    public void quedarseQuietoX() {

        retrocederPorIzquierda();
        retrocederPorDerecha();

    }
    public void quedarseQuietoY() {
        retrocederPorArriba();
        retrocederPorAbajo();
    }


}
