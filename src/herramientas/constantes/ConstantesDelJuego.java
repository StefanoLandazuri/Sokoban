package herramientas.constantes;



public enum ConstantesDelJuego  {


    ALTO_DE_PANEL_DE_BOTONES(65), //Para la ventana del juego
    DIMENSION_DE_SPRITE(64),
    NUMERO_DE_JUGADOR(29),
    ALTO_DE_CUADRICULA(10),
    ANCHO_DE_CUADRICULA(10),
    ANCHO_PANEL_DE_JUEGO(640),
    ALTO_PANEL_DE_JUEGO(640);


    private final int valor;

    ConstantesDelJuego(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
