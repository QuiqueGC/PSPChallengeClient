package utils;

import data_classes.WindowsProcess;

import java.util.ArrayList;

public class ConnectionThread extends Thread {

    ArrayList<String> programs;
    ArrayList<WindowsProcess> processes;
    boolean exit;

    public ConnectionThread() {
        programs = new ArrayList<>();
        processes = new ArrayList<>();
        exit = false;
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


        } while (!exit);

    }


    /**
     * Recibe la orden del server de detener algún proceso
     */
    private void receiveOrderFromServer() {
        String response;
        String serverOrder = SocketsManager.getString();

        if (serverOrder.equals("stopProcess")) {
            String pid = SocketsManager.getString();
            response = ProcessManager.stoppingProcess(pid);
            SocketsManager.sendString(response);
        }
    }

}
