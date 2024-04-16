package listeners;

import data_classes.User;
import j_panels.PanelUser;
import p_s_p_challenge.PSPChallenge;
import utils.BlowFishManager;

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
    private User foundUser;


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

            lookingForUser(name);

            if (foundUser != null) {

                loginUser(passwd);

            } else {

                JOptionPane.showMessageDialog(null, "El nombre de usuario no está registrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void lookingForUser(String name) {
        // TODO: 16/04/2024 buscar usuario en el server (tiene que devolver un user)
        //  para asignarle el valor a foundUser

    }

    private void loginUser(String passwd) {

        // TODO: 16/04/2024 hacer login con el server
        if (BlowFishManager.checkingPasswd(passwd, foundUser)) {

            PSPChallenge.actualUser = foundUser;

            DIALOG.dispose();

            PSPChallenge.frame.setContentPane(new PanelUser());


        } else {

            JOptionPane.showMessageDialog(null, "La contraseña no coincide con la del usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
