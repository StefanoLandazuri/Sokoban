package sokoban;

import interfacesGraficas.Nivel;
import principal.Main;
import principal.PanelDelJuego;

import javax.swing.*;




public class VerificadorDeColisiones {

    private final PanelDelJuego panelDelJuego;
    private final Jugador jugador;
    private final Caja[] cajas;
    private final Meta[] metas;
    private final Pared[] paredes;

    public VerificadorDeColisiones(PanelDelJuego panelDelJuego, Jugador jugador, Caja[] cajas, Meta[] metas, Pared[] paredes) {
        this.panelDelJuego = panelDelJuego;
        this.jugador = jugador;
        this.cajas = cajas;
        this.metas = metas;
        this.paredes = paredes;
    }

    protected void verificarPosicionDeLaCaja(int numeroDeMovimento) { //Mandamos un numero de movimento para que verifique solo por esa direccion

        for (Caja caja : cajas) {
            // Para actualizar por arriba y por abajo
            if (estanJugadorYCajaEnElMismoX(caja)) {
                switch (numeroDeMovimento) {
                    case 1: {
                        if (estanJugadorYCajaEnElMismoY(caja)) {
                            cajaLlegoAMeta();
                            caja.actualizarPorArriba();
                        }
                    }
                    break;
                    case 2: {
                        if (estanJugadorYCajaEnElMismoY(caja)) {
                            cajaLlegoAMeta();
                            caja.actualizarPorAbajo();
                        }
                    }
                }
            }
            //Para actualizar por izquierda y derecha

            if (estanJugadorYCajaEnElMismoY(caja)) {
                switch (numeroDeMovimento) {
                    case 3: {
                        if (estanJugadorYCajaEnElMismoX(caja)) {
                            cajaLlegoAMeta();
                            caja.actualizarDerecha();

                        }
                    }
                    break;
                    case 4: {
                        if (estanJugadorYCajaEnElMismoX(caja)) {
                            cajaLlegoAMeta();
                            caja.actualizarPorIzquierda();
                        }
                    }
                    break;
                }

            }
        }
    }


    protected void verificarColisionDeLaCajas(int numeroDeMovimento) {

        for (int i = 0; i < cajas.length - 1; i++) {

            if (cajas[i + 1] != null) {

                if (estanCajasEnColisionEnX(cajas[i + 1])) {
                    switch (numeroDeMovimento) {
                        case 1:
                            if (estanCajasEnColisionEnY(cajas[i + 1])) {

                                cajas[i].retrocederPorAbajo();
                                cajas[i + 1].quedarseQuietoY();
                                jugador.retrocederPorAbajo();

                            }
                        case 2:
                            if (estanCajasEnColisionEnY(cajas[i + 1])) {
                                cajas[i + 1].quedarseQuietoY();
                                cajas[i].retrocederPorArriba();
                                jugador.retrocederPorArriba();
                            }
                    }
                    if (estanCajasEnColisionEnY(cajas[i + 1])) {
                        switch (numeroDeMovimento) {
                            case 3:
                                if (estanCajasEnColisionEnX(cajas[i + 1])) {
                                    cajas[i + 1].quedarseQuietoX();
                                    cajas[i].retrocederPorIzquierda();
                                    jugador.retrocederPorIzquierda();
                                }
                            case 4:
                                if (estanCajasEnColisionEnX(cajas[i + 1])) {
                                    cajas[i + 1].quedarseQuietoX();
                                    cajas[i].retrocederPorDerecha();
                                    jugador.retrocederPorDerecha();


                                }
                        }
                    }

                }
            }
        }
    }


    protected void verificarColisionJugadorYPared(int numeroDeMovimento) {
        for (Pared pared : paredes) {
            if (estaJudadorYParedEnELMismoX(pared)) {
                switch (numeroDeMovimento) {
                    case 1: {
                        if (estaJudadorYParedEnELMismoY(pared)) {
                            //jugador.noColosiona = false;
                            jugador.retrocederPorAbajo();

                        }
                    }
                    break;
                    case 2: {
                        if (estaJudadorYParedEnELMismoY(pared)) {
                            //jugador.noColosiona = false;
                            jugador.retrocederPorArriba();
                        }
                    }
                }
            }
            if (estaJudadorYParedEnELMismoY(pared)) {
                switch (numeroDeMovimento) {
                    case 3: {
                        if (estaJudadorYParedEnELMismoX(pared)) {
                            // jugador.noColosiona = false;
                            jugador.retrocederPorIzquierda();
                        }
                    }
                    break;
                    case 4: {
                        if (estaJudadorYParedEnELMismoX(pared)) {
                            //jugador.noColosiona = false;

                            jugador.retrocederPorDerecha();
                        }
                    }
                    break;
                }

            }
        }
    }

    protected void verificarColisionDeCajasYPared(int numeroDeMovimento) {
        for (Pared pared : paredes) {
            for (Caja caja : cajas) {
                if (estaCajaEnColisionConParedEnX(pared, caja)) {
                    switch (numeroDeMovimento) {
                        case 1:
                            if (estaCajaEnColisionConParedEnY(pared, caja)) {
                                caja.retrocederPorAbajo();
                                jugador.retrocederPorAbajo();
                            }

                            break;

                        case 2:
                            if (estaCajaEnColisionConParedEnY(pared, caja)) {
                                caja.retrocederPorArriba();
                                jugador.retrocederPorArriba();
                            }
                            break;
                    }
                    if (estaCajaEnColisionConParedEnY(pared, caja)) {

                        switch (numeroDeMovimento) {
                            case 3:
                                if (estaCajaEnColisionConParedEnX(pared, caja)) {
                                    caja.retrocederPorIzquierda();
                                    jugador.retrocederPorIzquierda();
                                }

                            case 4:
                                if (estaCajaEnColisionConParedEnX(pared, caja)) {
                                    caja.retrocederPorDerecha();
                                    jugador.retrocederPorDerecha();
                                }

                        }

                    }


                }


            }

        }

    }


    private boolean estaCajaEnColisionConParedEnX(Pared pared, Caja caja) {

        return caja.getPosicion().getX() == pared.getPosicion().getX();
    }

    private boolean estaCajaEnColisionConParedEnY(Pared pared, Caja caja) {
        return caja.getPosicion().getY() == pared.getPosicion().getY();

    }


    private boolean estanCajasEnColisionEnX(Caja otraCaja) {
        for (Caja caja : cajas) {
            if (caja != otraCaja) {
                if (caja.getPosicion().getX() == otraCaja.getPosicion().getX()) {
                    return true;
                }

            }

        }
        return false;
    }

    private boolean estanCajasEnColisionEnY(Caja otraCaja) {
        for (Caja caja : cajas) {
            if (caja != otraCaja) {
                if (caja.getPosicion().getY() == otraCaja.getPosicion().getY()) {
                    return true;
                }

            }

        }
        return false;

    }

    private boolean estaJudadorYParedEnELMismoX(Pared pared) {
        return pared.getPosicion().getX() == jugador.getPosicion().getX();
    }

    private boolean estaJudadorYParedEnELMismoY(Pared pared) {
        return pared.getPosicion().getY() == jugador.getPosicion().getY();
    }

    private boolean estanJugadorYCajaEnElMismoY(Caja caja) {
        return caja.getPosicion().getY() == jugador.getPosicion().getY();
    }

    private boolean estanJugadorYCajaEnElMismoX(Caja caja) {
        return caja.getPosicion().getX() == jugador.getPosicion().getX();
    }

    protected void cajaLlegoAMeta() {
        //El contador siempre en 0 para que comprueba siempre la cantidad de las cjas en metas
        int contadorDeCajasEnMetas = 0;
        for (Caja caja : cajas) {
            for (Meta meta : metas)
                if ((caja.getPosicion().getX() == meta.getPosicion().getX()) && (caja.getPosicion().getY() == meta.getPosicion().getY())) {
                    contadorDeCajasEnMetas++;
                }
        }
        if (contadorDeCajasEnMetas == metas.length) {
            //quito un movimiento
            int puntuacionFinal = (int) calcularPuntuacionFinal();
            Nivel.numeroDeNivelACrear++;

            if (Nivel.numeroDeNivelACrear < 4) {
                JOptionPane.showMessageDialog(panelDelJuego, "SIGUIENTE NIVEL");
                Nivel.crearNivel(Main.ventana, Nivel.numeroDeNivelACrear);
            } else {
                JOptionPane.showMessageDialog(panelDelJuego, "HA TERMINANDO EL JUEGO \nPuntuación: " + puntuacionFinal);
                Nivel.setContadorDeMovimientos();
                Main.crearMenu();
            }

        }
    }

    private double calcularPuntuacionFinal() {
        double numeroMov = Nivel.getContadorDeMovimientos();

        return  Math.round(55.55*(Math.abs(-0.05 *Math.pow(numeroMov,2)+6*numeroMov)))+4;
    }


}
