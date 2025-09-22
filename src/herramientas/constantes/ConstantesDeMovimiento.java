package herramientas.constantes;

public enum ConstantesDeMovimiento {
    ARRIBA(1),
    ABAJO(2),
    DERECHA(3),
    IZQUIERDA(4);

    private final int valor;

    ConstantesDeMovimiento(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
