package herramientas.constantes;

public enum Mapa {

    MAPA1("/Mapas/Mapa1.txt",1),
    MAPA2("/Mapas/Mapa2.txt",2),
    MAPA3("/Mapas/Mapa3.txt",3),
    MAPA_GUARDADO("recursos/Mapas/MapaGuardado/datosDeNivel.txt",0);


    private String direccionDeMapa;
    private int numeroDeMapa;

    Mapa(String direccionDeMapa, int numeroDeMapa) {
        this.direccionDeMapa = direccionDeMapa;
        this.numeroDeMapa = numeroDeMapa;
    }



    public String getDireccionDeMapa() {
        return direccionDeMapa;
    }

    public int getNumeroDeMapa() {
        return numeroDeMapa;
    }
}
