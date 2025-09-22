package herramientas;

import herramientas.constantes.Direccion;
import sokoban.Caja;
import sokoban.Jugador;
import sokoban.Posicion;

import java.io.*;
import java.util.ArrayList;

public abstract class HerramientaDeGuardado {

    public static void guardarDatosDelNivel(int numeroDeNivelACrear, int contadorDeMovimientos) {

        try {
            File archivo = new File(Direccion.DIRECCION_GUARDADO_NIVEL.getDirección());
            FileWriter escribir = new FileWriter(archivo);

            escribir.write(""+numeroDeNivelACrear);
            escribir.write("\n"+contadorDeMovimientos);

            escribir.close();
        } //Si existe un problema al escribir cae aqui
        catch (Exception e) {
            System.out.println("Error al escribir");
        }

    }
    public static void guardarDatosDePosicionDelJugador(Jugador jugador) {

        File archivo = new File(Direccion.DIRECCION_GUARDADO_JUGADOR.getDirección());
        try {
            FileOutputStream flujoDeSalida = new FileOutputStream(archivo);
            ObjectOutputStream manejadorDeEscritura = new ObjectOutputStream(flujoDeSalida);
            manejadorDeEscritura.writeObject(jugador.getPosicion());
            System.out.println("Se guardaron los datos");
            manejadorDeEscritura.close();
        } catch (IOException e) {
            System.out.println("no se registraron los datos del Jugador");
            e.printStackTrace();
        }

    }

    public static void guardarDatosDeCajas(Caja[] cajas) {
        File archivo = new File(Direccion.DIRECCION_GUARDADO_CAJAS.getDirección());
        try {
            FileOutputStream flujoDeSalida = new FileOutputStream(archivo);
            ObjectOutputStream manejadorDeEscritura = new ObjectOutputStream(flujoDeSalida);
            manejadorDeEscritura.writeObject(cajas);
            System.out.println("Se guardaron los datos");
            manejadorDeEscritura.close();
        } catch (IOException e) {
            System.out.println("no se registraron los datos de las Cajas");
            e.printStackTrace();
        }

    }


    public static Caja[] leerDatosDeCajas() {
    File archivo = new File(Direccion.DIRECCION_GUARDADO_CAJAS.getDirección());
        Caja[] cajas = null;
        try {
            FileInputStream flujoDeEntrada = new FileInputStream(archivo);
            ObjectInputStream manejadorDeLectura = new ObjectInputStream(flujoDeEntrada);
            cajas = (Caja[]) manejadorDeLectura.readObject();

            manejadorDeLectura.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cajas;
    }


    public static ArrayList<Integer> leerDatosDeNivel() {

        ArrayList<Integer> datosNivel = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Direccion.DIRECCION_GUARDADO_NIVEL.getDirección()));
            String linea;
            while ((linea = reader.readLine()) != null){
                datosNivel.add(Integer.parseInt(linea));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return datosNivel;
    }

    public static Posicion leerDatosDePosicionDeJugador() {
        File archivo = new File(Direccion.DIRECCION_GUARDADO_JUGADOR.getDirección());
        Posicion posicion = null;
        try {
            FileInputStream flujoDeEntrada = new FileInputStream(archivo);
            ObjectInputStream manejadorDeLectura = new ObjectInputStream(flujoDeEntrada);
            posicion = (Posicion) manejadorDeLectura.readObject();
            manejadorDeLectura.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return posicion;

    }


}
