package listeners;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * listener que extiende WindowAdapter para el botón de cierre de ventana
 */
public class FrameWindowListener extends WindowAdapter {
    JFrame frame;

    public FrameWindowListener(JFrame frame) {
        this.frame = frame;
    }


    /**
     * evento con la apertura de ventana
     *
     * @param e the event to be processed
     */
    @Override
    public void windowOpened(WindowEvent e) {
        super.windowOpened(e);

        showConnectionDialog();

    }

    private void showConnectionDialog() {
        String ip;
        do {
            ip = JOptionPane.showInputDialog(null, "Introduce IP del servidor", "Conectarse", JOptionPane.OK_CANCEL_OPTION);
        } while (ip == null || ip.equals(""));
        // TODO: 16/04/2024 conectar con el server
    }

    /**
     * evento para el cierre de la ventana
     *
     * @param e the event to be processed
     */
    @Override
    public void windowClosing(WindowEvent e) {
        super.windowClosing(e);

        int confirmado = JOptionPane.showConfirmDialog(null,
                "Seguro del cierre?",
                "mensaje",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirmado == JOptionPane.YES_OPTION) {

            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        } else {

            frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        }
    }
}
