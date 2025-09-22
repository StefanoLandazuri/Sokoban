package sokoban;

public  class SpriteMovible extends Sprite implements Movible {

    @Override
    public void actualizarDerecha() {
        posicion.setX(posicion.getX() + dimensionDeSprite);


    }

    @Override
    public void actualizarPorIzquierda() {
        posicion.setX(posicion.getX() - dimensionDeSprite);

    }

    @Override
    public void actualizarPorAbajo() {
        posicion.setY(posicion.getY() + dimensionDeSprite);
    }

    @Override
    public void actualizarPorArriba() {
        posicion.setY(posicion.getY() - dimensionDeSprite);
    }

    @Override
    public void retrocederPorDerecha() {
        posicion.setX(posicion.getX() + dimensionDeSprite);
    }

    @Override
    public void retrocederPorIzquierda() {
        posicion.setX(posicion.getX() - dimensionDeSprite);
    }

    @Override
    public void retrocederPorAbajo() {
        posicion.setY(posicion.getY() + dimensionDeSprite);
    }

    @Override
    public void retrocederPorArriba() {
        posicion.setY(posicion.getY() - dimensionDeSprite);
    }
}



