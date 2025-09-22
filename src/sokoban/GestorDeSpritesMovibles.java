package sokoban;

import herramientas.constantes.ConstantesDeMovimiento;

import principal.PanelDelJuego;


import java.awt.*;


public class GestorDeSpritesMovibles  {


    private  final VerificadorDeColisiones verificadorDeColisiones;
    private Jugador jugador;
    private Caja[] cajas;


    public GestorDeSpritesMovibles(PanelDelJuego panelDelJuego, Jugador jugador, Caja[] cajas, Meta[] metas, Pared[] paredes) {
        this.jugador = jugador;
        this.cajas = cajas;

        verificadorDeColisiones = new VerificadorDeColisiones(panelDelJuego, jugador, cajas, metas, paredes);
    }

    public void dibujarSpritesMovibles(Graphics2D g2) {

        for (Caja caja : cajas) {
            caja.dibujar(g2);
        }
        jugador.dibujar(g2);
    }


    public void actualizarPorArriba() {
        jugador.actualizarPorArriba();
        verificadorDeColisiones.verificarPosicionDeLaCaja(ConstantesDeMovimiento.ARRIBA.getValor());
        verificadorDeColisiones.verificarColisionJugadorYPared(ConstantesDeMovimiento.ARRIBA.getValor());
        verificadorDeColisiones.verificarColisionDeCajasYPared(ConstantesDeMovimiento.ARRIBA.getValor());
        verificadorDeColisiones.verificarColisionDeLaCajas(ConstantesDeMovimiento.ARRIBA.getValor());


    }

    public void actualizarPorAbajo() {

        jugador.actualizarPorAbajo();
        verificadorDeColisiones.verificarPosicionDeLaCaja(ConstantesDeMovimiento.ABAJO.getValor());
        verificadorDeColisiones.verificarColisionJugadorYPared(ConstantesDeMovimiento.ABAJO.getValor());
        verificadorDeColisiones.verificarColisionDeCajasYPared(ConstantesDeMovimiento.ABAJO.getValor());
        verificadorDeColisiones.verificarColisionDeLaCajas(ConstantesDeMovimiento.ABAJO.getValor());


    }

    public void actualizarDerecha() {

        jugador.actualizarDerecha();
        verificadorDeColisiones.verificarPosicionDeLaCaja(ConstantesDeMovimiento.DERECHA.getValor());
        verificadorDeColisiones.verificarColisionJugadorYPared(ConstantesDeMovimiento.DERECHA.getValor());
        verificadorDeColisiones.verificarColisionDeCajasYPared(ConstantesDeMovimiento.DERECHA.getValor());
        verificadorDeColisiones.verificarColisionDeLaCajas(ConstantesDeMovimiento.DERECHA.getValor());

    }

    public void actualizarPorIzquierda() {
        jugador.actualizarPorIzquierda();
        verificadorDeColisiones.verificarPosicionDeLaCaja(ConstantesDeMovimiento.IZQUIERDA.getValor());
        verificadorDeColisiones.verificarColisionJugadorYPared(ConstantesDeMovimiento.IZQUIERDA.getValor());
        verificadorDeColisiones.verificarColisionDeCajasYPared(ConstantesDeMovimiento.IZQUIERDA.getValor());
        verificadorDeColisiones.verificarColisionDeLaCajas(ConstantesDeMovimiento.IZQUIERDA.getValor());

    }

    public Jugador getJugador() {
        return jugador;
    }

    public Caja[] getCajas() {
        return cajas;
    }

}
