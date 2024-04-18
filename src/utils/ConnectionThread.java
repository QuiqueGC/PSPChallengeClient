package utils;

import data_classes.WindowsProcess;

import java.util.ArrayList;

public class ConnectionThread extends Thread {

    ArrayList<String> programs;
    ArrayList<WindowsProcess> processes;

    public ConnectionThread() {
        programs = new ArrayList<>();
        processes = new ArrayList<>();
    }

    @Override
    public void run() {
        super.run();

        programs.clear();
        ProgramsManager.extractingListOfPrograms(programs);
        SocketsManager.sendPrograms(programs);

        processes.clear();
        ProcessManager.extractingListOfProcesses(processes);
        SocketsManager.sendProcesses(processes);


    }

}
