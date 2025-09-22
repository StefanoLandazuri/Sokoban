package sokoban;

import herramientas.constantes.ConstantesDelJuego;
import herramientas.constantes.Direccion;
import herramientas.constantes.Mapa;
import interfacesGraficas.Nivel;
import principal.PanelDelJuego;
import herramientas.HerramientaDeGuardado;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Objects;

public class GestorDeSprites {
    protected static PanelDelJuego panelDelJuego;

    private final int anchoDeCuadricula = ConstantesDelJuego.ANCHO_DE_CUADRICULA.getValor();
    private final int altoDeCuadricula = ConstantesDelJuego.ALTO_DE_CUADRICULA.getValor();

    protected Sprite[] sprites;
    private final int[][] numeroDeBloqueEnMapa;
    private final int dimensionDelSprite = ConstantesDelJuego.DIMENSION_DE_SPRITE.getValor();

    //Cajas
    protected Caja[] cajas;
    private int numeroDeCajas = 0;

    //Metas
    protected Meta[] metas;
    private int numeroDeMetas = 0;

    //Paredes
    protected Pared[] paredes;
    private int numeroDeParedes = 0;


    //Datos del jugador
    protected Jugador jugador;

    private GestorDeSpritesMovibles gestorDeSpritesMovibles;


    public GestorDeSprites(PanelDelJuego panelDelJuego) {
        this.panelDelJuego = panelDelJuego;
        sprites = new Sprite[31];

        //sacar todos los numero como constantes
        numeroDeBloqueEnMapa = new int[anchoDeCuadricula][altoDeCuadricula];
        determinarPosiciones();
        gestorDeSpritesMovibles = new GestorDeSpritesMovibles(panelDelJuego, jugador, cajas, metas, paredes);
    }


    private void determinarPosiciones() {
        //Crear el mapa dependiendo al nuumero de nivel
        switch (Nivel.numeroDeNivelACrear) {
            case 1: {
                cargarMapa(Mapa.MAPA1.getDireccionDeMapa());
            }
            break;
            case 2: {
                cargarMapa(Mapa.MAPA2.getDireccionDeMapa());
            }
            break;
            case 3: {
                cargarMapa(Mapa.MAPA3.getDireccionDeMapa());
            }
            break;
        }
        cargarBloques();
        extraerCantidadDeCadaTipoDeSprite();
        determinarPosicionDeCadaSprite();
        //El metodo se ejecuta solo si se da a cargar

        cargarJugadorGuardado();
        posicionDeCajasGuardadas();


    }

    private void determinarPosicionDeCadaSprite() {
        metas = new Meta[numeroDeMetas];
        int contadorDeMetas = 0;

        cajas = new Caja[numeroDeCajas];
        int contadorDeCajas = 0;

        paredes = new Pared[numeroDeParedes];
        int contadorDeParedes = 0;


        for (int x = 0; x < anchoDeCuadricula; x++) {
            for (int y = 0; y < altoDeCuadricula; y++) {
                int numBloque = numeroDeBloqueEnMapa[x][y];

                if (sprites[numBloque] instanceof Meta) {
                    metas[contadorDeMetas] = new Meta();
                    metas[contadorDeMetas].setPosicion(new Posicion(x, y));
                    contadorDeMetas++;
                } else if (sprites[numBloque] instanceof Caja) {  //Extraemos el vector solo de paredes con su posicion
                    cajas[contadorDeCajas] = new Caja();
                    cajas[contadorDeCajas].imagen = sprites[numBloque].imagen;
                    cajas[contadorDeCajas].setPosicion(new Posicion(x, y));
                    contadorDeCajas++;

                } else if (sprites[numBloque] instanceof Pared) { //Extraemos el vector solo de paredes con su posicion
                    paredes[contadorDeParedes] = new Pared();
                    paredes[contadorDeParedes].setPosicion(new Posicion(x, y));
                    contadorDeParedes++;
                }
            }
        }

    }


    private void extraerCantidadDeCadaTipoDeSprite() {
        for (int x = 0; x < anchoDeCuadricula; x++) {
            for (int y = 0; y < altoDeCuadricula; y++) {
                int numBloque = numeroDeBloqueEnMapa[x][y];
                if (numBloque == ConstantesDelJuego.NUMERO_DE_JUGADOR.getValor()) {
                    jugador = new Jugador(new Posicion(x, y));

                } else if (sprites[numBloque] instanceof Caja) { // Extraemos de las cajas
                    numeroDeCajas++;
                } else if (sprites[numBloque] instanceof Meta) { // Extraemos numero de
                    numeroDeMetas++;
                } else if (sprites[numBloque] instanceof Pared) {
                    numeroDeParedes++;
                }

            }
        }
    }

    private void cargarJugadorGuardado() {
        if (Nivel.isEsNivelGuardado()) {
            jugador = new Jugador(HerramientaDeGuardado.leerDatosDePosicionDeJugador());
        }
    }

    private void posicionDeCajasGuardadas() {
        if (Nivel.isEsNivelGuardado()) {
            Caja[] cajasGuardadas = HerramientaDeGuardado.leerDatosDeCajas();
            for (int i = 0; i < cajas.length; i++) {
                cajas[i].setPosicion(cajasGuardadas[i].getPosicion());
            }
            Nivel.setEsNivelGuardado(false);
        }

    }


    private void cargarMapa(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < anchoDeCuadricula && row < altoDeCuadricula) {
                String line = br.readLine();
                while (col < anchoDeCuadricula) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    numeroDeBloqueEnMapa[col][row] = num;

                    col++;
                }
                if (col == anchoDeCuadricula) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private void cargarBloques() {

        try {

            //indices del 0 - 7
            cargarCajas();
            //inidices 8-15
            cargarPisos();
            //indices 16-19
            cargarParedes();
            //20-27
            cargarMetas();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void dibujarFondo(Graphics2D g2) {

        //TODO: corregir que bloques mismos vamos poner
        for (int y = 0; y < anchoDeCuadricula; y++) {
            for (int x = 0; x < altoDeCuadricula; x++) {
                int numBloque = numeroDeBloqueEnMapa[x][y];

                if (sprites[numBloque] instanceof Meta) {
                    g2.drawImage(sprites[numBloque].imagen, x * dimensionDelSprite + 16, y * dimensionDelSprite + 16, null);

                } else if (sprites[numBloque] instanceof Pared) {
                    g2.drawImage(sprites[numBloque].imagen, x * dimensionDelSprite, y * dimensionDelSprite, null);
                } else if (sprites[numBloque] instanceof Piso || numBloque == ConstantesDelJuego.NUMERO_DE_JUGADOR.getValor()) {
                    g2.drawImage(sprites[14].imagen, x * dimensionDelSprite, y * dimensionDelSprite, null);
                } else {
                    g2.drawImage(sprites[14].imagen, x * dimensionDelSprite, y * dimensionDelSprite, null);
                }

            }
        }

    }

    private void cargarCajas() throws IOException {
        sprites[0] = new Caja();
        sprites[0].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/Crate_Beige.png")));

        sprites[1] = new Caja();
        sprites[1].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/Crate_Black.png")));

        sprites[2] = new Caja();
        sprites[2].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/Crate_Blue.png")));

        sprites[3] = new Caja();
        sprites[3].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/Crate_Brown.png")));

        sprites[4] = new Caja();
        sprites[4].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/Crate_Gray.png")));

        sprites[5] = new Caja();
        sprites[5].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/Crate_Purple.png")));

        sprites[6] = new Caja();
        sprites[6].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/Crate_Red.png")));

        sprites[7] = new Caja();
        sprites[7].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/Crate_Yellow.png")));

    }

    private void cargarPisos() throws IOException {
        sprites[8] = new Piso();
        sprites[8].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/Ground_Concrete.png")));

        sprites[9] = new Piso();
        sprites[9].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/Ground_Dirt.png")));

        sprites[10] = new Piso();
        sprites[10].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/Ground_Grass.png")));

        sprites[11] = new Piso();
        sprites[11].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/Ground_Sand.png")));

        sprites[12] = new Piso();
        sprites[12].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/GroundGravel_Concrete.png")));

        sprites[13] = new Piso();
        sprites[13].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/GroundGravel_Dirt.png")));

        sprites[14] = new Piso();
        sprites[14].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/GroundGravel_Grass.png")));

        sprites[15] = new Piso();
        sprites[15].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/GroundGravel_Sand.png")));

    }

    private void cargarParedes() throws IOException {

        sprites[16] = new Pared();
        sprites[16].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/WallRound_Beige.png")));

        sprites[17] = new Pared();
        sprites[17].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/WallRound_Black.png")));

        sprites[18] = new Pared();
        sprites[18].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/WallRound_Brown.png")));

        sprites[19] = new Pared();
        sprites[19].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/WallRound_Gray.png")));

        sprites[30] = new SombraDeJugador();
        sprites[30].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(Direccion.DIRECCION_IMAGEN_JUGADOR.getDirección())));

    }

    private void cargarMetas() throws IOException {

        sprites[20] = new Meta();
        sprites[20].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/EndPoint_Beige.png")));

        sprites[21] = new Meta();
        sprites[21].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/EndPoint_Black.png")));

        sprites[22] = new Meta();
        sprites[22].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/EndPoint_Blue.png")));

        sprites[23] = new Meta();
        sprites[23].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/EndPoint_Brown.png")));

        sprites[24] = new Meta();
        sprites[24].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/EndPoint_Gray.png")));

        sprites[25] = new Meta();
        sprites[25].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/EndPoint_Purple.png")));

        sprites[26] = new Meta();
        sprites[26].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/EndPoint_Red.png")));

        sprites[27] = new Meta();
        sprites[27].imagen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SpritesBloques/EndPoint_Yellow.png")));


    }


    public void actualizarDerecha() {
        gestorDeSpritesMovibles.actualizarDerecha();
    }


    public void actualizarPorArriba() {
        gestorDeSpritesMovibles.actualizarPorArriba();
    }

    public void actualizarPorAbajo() {

        gestorDeSpritesMovibles.actualizarPorAbajo();

    }


    public void actualizarPorIzquierda() {
        gestorDeSpritesMovibles.actualizarPorIzquierda();

    }

    public void dibujarSpritesMovibles(Graphics2D g2) {
        gestorDeSpritesMovibles = new GestorDeSpritesMovibles(panelDelJuego, jugador, cajas, metas, paredes);
        gestorDeSpritesMovibles.dibujarSpritesMovibles(g2);
    }

    public GestorDeSpritesMovibles getGestorDeSpritesMovibles() {
        return gestorDeSpritesMovibles;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}
