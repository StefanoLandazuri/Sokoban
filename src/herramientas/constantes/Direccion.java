package herramientas.constantes;

public enum Direccion {

    DIRECCION_GUARDADO_JUGADOR("recursos/Mapas/MapaGuardado/"+"datosDeJugador.txt"),
    DIRECCION_GUARDADO_NIVEL("recursos/Mapas/MapaGuardado/"+"datosDeNivel.txt"),
    DIRECCION_GUARDADO_CAJAS("recursos/Mapas/MapaGuardado/"+"cajasGuardadas.txt"),

    DIRECCION_IMAGEN_JUGADOR("/Jugador/Jugador.png");

    private String dirección;

    Direccion(String dirección) {

        this.dirección = dirección;
    }

    public String getDirección() {
        return dirección;
    }
}
