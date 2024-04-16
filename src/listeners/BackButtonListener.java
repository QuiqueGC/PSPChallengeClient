package listeners;

import j_panels.PanelUser;
import p_s_p_challenge.PSPChallenge;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Listener genérico para el botón "volver"
 */
public class BackButtonListener extends MouseAdapter {

    public BackButtonListener() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        PSPChallenge.frame.setContentPane(new PanelUser());
        PSPChallenge.frame.setTitle("Panel de control de usuario");
    }
}
