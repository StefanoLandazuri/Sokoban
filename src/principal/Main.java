package principal;

import interfacesGraficas.MenuPrincipal;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static JFrame ventana = new JFrame();
    public static JFrame ventanaPausa = new JFrame();
   // public static boolean esNivelGuardado = false;

    public static void main(String[] args) {
        crearMenu();
    }

    public static void crearMenu() {
        crearVentana();

    }

    private static void crearVentana() {
        ventana.setTitle("Menu del juego");

        ventana.setContentPane(new MenuPrincipal(ventana).pnlMenuPrincipal); // añadimos el panel del menu a la ventana
        ventana.setPreferredSize(new Dimension(450, 450)); // Definimos un tamaño preferencial
        ventana.setLocationRelativeTo(null);// me lo pone en el centro
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Para cerrar la ventana y parar la ejecución del programa

        ventana.getContentPane().setBackground(Color.WHITE); // ponemos color a la ventana que creamos
        ventana.pack();
        ventana.setVisible(true);
    }


}
