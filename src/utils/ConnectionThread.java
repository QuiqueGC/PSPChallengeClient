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
        String response;

        do {
            programs.clear();
            ProgramsManager.extractingListOfPrograms(programs);
            SocketsManager.sendPrograms(programs);

            processes.clear();
            ProcessManager.extractingListOfProcesses(processes);
            SocketsManager.sendProcesses(processes);


            String serverOrder = SocketsManager.getString();
            switch (serverOrder) {
                case "stopProcess":
                    String pid = SocketsManager.getString();
                    response = ProcessManager.stoppingProcess(pid);
                    SocketsManager.sendString(response);
                    break;
                default:
                    break;
            }

        } while (!exit);

    }

}
