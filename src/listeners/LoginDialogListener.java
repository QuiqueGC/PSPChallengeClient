package listeners;

import data_classes.User;
import utils.SocketsManager;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Listener para el botón de login de usuario
 */
public class LoginDialogListener extends MouseAdapter {

    private final JTextField NAME_FIELD;
    private final JPasswordField PASSWD_FIELD;
    private final JDialog DIALOG;


    public LoginDialogListener(JTextField nameField, JPasswordField paswdField, JDialog dialog) {
        this.NAME_FIELD = nameField;
        this.PASSWD_FIELD = paswdField;
        this.DIALOG = dialog;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        String name = NAME_FIELD.getText().trim();
        String passwd = String.valueOf(PASSWD_FIELD.getPassword()).trim();

        if (name.isEmpty()) {

            JOptionPane.showMessageDialog(null, "No has introducido nombre de usuario.", "Error", JOptionPane.ERROR_MESSAGE);

        } else if (passwd.isEmpty()) {

            JOptionPane.showMessageDialog(null, "No has introducido contraseña.", "Error", JOptionPane.ERROR_MESSAGE);

        } else {

            SocketsManager.sendPetition("login");
            SocketsManager.sendUser(new User(name, passwd, 2));
            String response = SocketsManager.getResponse();
            JOptionPane.showMessageDialog(null, response, "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
