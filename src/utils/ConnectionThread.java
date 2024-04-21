package utils;

import data_classes.WindowsProcess;
import j_panels.PanelMain;
import p_s_p_challenge.PSPChallenge;

import javax.swing.*;
import java.util.ArrayList;

public class ConnectionThread extends Thread {

    ArrayList<String> programs;
    ArrayList<WindowsProcess> processes;
    boolean exit, adminLogout;


    public ConnectionThread() {
        programs = new ArrayList<>();
        processes = new ArrayList<>();
        exit = false;
    }

    @Override
    public void run() {
        super.run();

        do {
            if (PSPChallenge.isLoggedIn) {

                programs.clear();
                System.out.println("ENVÍA PROGRAMAS");
                ProgramsManager.extractingListOfPrograms(programs);
                SocketsManager.sendPrograms(programs);

                processes.clear();
                System.out.println("ENVÍA PROCESOS");
                ProcessManager.extractingListOfProcesses(processes);
                SocketsManager.sendProcesses(processes);

                System.out.println("RECIBE PETICIÓN DEL SERVER");
                receiveOrderFromServer();

                System.out.println("ENVÍA PETICIÓN AL SERVER");
                sendOrderToServer();

                System.out.println("ENVÍA EL ESTADO DEL LOGED");
                sendLoggedState();

                if (!PSPChallenge.isLoggedIn) {
                    PSPChallenge.frame.setContentPane(new PanelMain());
                    exit = true;
                }

                System.out.println("RECIBE SI EL ADMIN SIGUE LOGEADO");
                adminLogout = SocketsManager.getAdminConnection();
            }

        } while (!exit && !adminLogout);

        if (adminLogout) {
            System.out.println("HA SALIDO DEL BUCLE POR LOGOUT DEL ADMIN");
            PSPChallenge.isLoggedIn = false;
            SocketsManager.closeConnection();
            JOptionPane.showMessageDialog(null, "El servidor se ha cerrado. La aplicación se cerrará", "Información", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    private void sendLoggedState() {
        SocketsManager.sendLoggedInState();
    }

    /**
     * envía la petición de modificación de usuario al server
     */
    private void sendOrderToServer() {
        SocketsManager.sendString(PSPChallenge.changeUserProfileOrder);
        if (PSPChallenge.changeUserProfileOrder.equals("changeUser")) {
            SocketsManager.sendUser(PSPChallenge.actualUser);
            String response = SocketsManager.getString();
            JOptionPane.showMessageDialog(null, response, "Información", JOptionPane.INFORMATION_MESSAGE);
        }
        PSPChallenge.changeUserProfileOrder = "";
    }


    /**
     * Recibe la orden del server de detener algún proceso
     */
    private void receiveOrderFromServer() {
        String serverOrder = SocketsManager.getString();

        if (serverOrder.equals("stopProcess")) {
            String response;
            String pid = SocketsManager.getString();
            response = ProcessManager.stoppingProcess(pid);
            SocketsManager.sendString(response);
        }
    }

}
