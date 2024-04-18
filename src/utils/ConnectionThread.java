package utils;

import java.util.ArrayList;

public class ConnectionThread extends Thread {

    ArrayList<String> programs;

    public ConnectionThread() {
        programs = new ArrayList<>();
    }

    @Override
    public void run() {
        super.run();
        ProgramsManager.extractingListOfPrograms(programs);

        SocketsManager.sendPrograms(programs);


    }

}
