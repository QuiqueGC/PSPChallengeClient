package utils;

import data_classes.WindowsProcess;
import p_s_p_challenge.PSPChallenge;

import javax.swing.*;
import java.util.ArrayList;

public class ConnectionThread extends Thread {

    ArrayList<String> programs;
    ArrayList<WindowsProcess> processes;
    boolean exit;


    public ConnectionThread() {
        programs = new ArrayList<>();
        processes = new ArrayList<>();
        exit = false;
        PSPChallenge.changeUserProfileOrder = "";
    }

    @Override
    public void run() {
        super.run();

        do {
            programs.clear();
            ProgramsManager.extractingListOfPrograms(programs);
            SocketsManager.sendPrograms(programs);

            processes.clear();
            ProcessManager.extractingListOfProcesses(processes);
            SocketsManager.sendProcesses(processes);

            receiveOrderFromServer();

            sendOrderToServer();


        } while (!exit);

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
