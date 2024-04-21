package j_panels;

import p_s_p_challenge.PSPChallenge;
import utils.SpellBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelUser extends JPanel {

    JButton profileButton;
    JButton logoutButton;
    JLabel logoutLoading;

    public PanelUser() {

        SpellBook.creatingStandardPanelForFrame(this);

        addingButtons();

        addingLoadingLabel();

        PSPChallenge.frame.setTitle("Panel de control de usuario");
    }

    private void addingLoadingLabel() {
        logoutLoading = new JLabel();
        logoutLoading.setSize(200, 200);
        logoutLoading.setLocation(230, 150);
        logoutLoading.setFont(new Font("Serif", Font.PLAIN, 26));
        logoutLoading.setForeground(Color.white);
        this.add(logoutLoading);
    }


    protected void addingButtons() {

        addingProfileButton(this);

        addingLogoutButton();
    }

    private void addingProfileButton(JPanel paneUser) {
        profileButton = new JButton();
        profileButton.setText("Editar perfil");
        profileButton.setSize(200, 50);
        profileButton.setLocation(
                20,
                50);
        this.add(profileButton);
        profileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                PSPChallenge.frame.setContentPane(new PanelEditProfile(paneUser));
            }
        });
    }

    private void addingLogoutButton() {
        logoutButton = new JButton();
        logoutButton.setText("Cerrar sesión");
        logoutButton.setSize(150, 20);
        logoutButton.setLocation(
                445,
                420);
        this.add(logoutButton);
        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                PSPChallenge.isLoggedIn = false;
                logoutButton.setEnabled(false);
                logoutLoading.setText("Cerrando sesión...");

                //JOptionPane.showMessageDialog(null, "Cerrando sesión", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
