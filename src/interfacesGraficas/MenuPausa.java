package interfacesGraficas;

import principal.Main;
import principal.PanelDelJuego;
import herramientas.HerramientaDeGuardado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPausa {

    private JButton btnReaundar;
    private JButton btnGuardar;
    protected JPanel pnlMenuOpciones;
    private JLabel lblImagenFondo;
    private JButton btnMenuPrincipal;
   private PanelDelJuego panelGeneral;

    public MenuPausa(PanelDelJuego panelGeneral) {
        this.panelGeneral = panelGeneral;

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                HerramientaDeGuardado.guardarDatosDelNivel(Nivel.numeroDeNivelACrear,Nivel.getContadorDeMovimientos());
                HerramientaDeGuardado.guardarDatosDePosicionDelJugador(panelGeneral.getGestorDeSprites().getGestorDeSpritesMovibles().getJugador());
                HerramientaDeGuardado.guardarDatosDeCajas(panelGeneral.getGestorDeSprites().getGestorDeSpritesMovibles().getCajas());

            }
        });
        btnReaundar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.ventanaPausa.dispose();
                Main.ventana.setVisible(true);
            }
        });

        btnMenuPrincipal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.ventanaPausa.dispose(); //regresa al menu principal
                Main.crearMenu();
            }
        });
    }

}
