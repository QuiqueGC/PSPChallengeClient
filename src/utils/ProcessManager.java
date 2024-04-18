package utils;

import data_classes.WindowsProcess;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public abstract class ProcessManager {
    private static WindowsProcess winProcSelected;


    public static void extractingListOfProcesses(ArrayList<WindowsProcess> processes) {
        ArrayList<String> executingProgramsList = new ArrayList<>();
        //pongo este índice para que no me coja las líneas que no necesito
        int index = 0;

        try {
            Process process = Runtime.getRuntime().exec("taskList");
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String resultOfExecution;

            while ((resultOfExecution = br.readLine()) != null) {

                resultOfExecution = resultOfExecution.replaceAll("\s+", ";");
                if (index > 2) {
                    executingProgramsList.add(resultOfExecution);
                }
                index++;
            }

        } catch (IOException e) {
            System.out.println(e);
        }
        manipulatingData(processes, executingProgramsList);
    }

    private static void manipulatingData(ArrayList<WindowsProcess> processes, ArrayList<String> executingProgramsList) {
        Path path = Paths.get("src/resources/files/processes.txt");

        FilesRW.creatingFile(path);

        FilesRW.writtingProcessesInFile(path, executingProgramsList);

        FilesRW.takingProcessesFromFile(processes);

        FilesRW.deleteFile(path);
    }


    public static void checkingAndStoppingProcess() {

        if (winProcSelected != null &&
                winProcSelected.getType().equals("Console")) {

            stoppingProcess();

        } else if (winProcSelected == null) {

            JOptionPane.showMessageDialog(null, "No has seleccionado ningún proceso.", "Error", JOptionPane.ERROR_MESSAGE);

        } else if (!winProcSelected.getType().equals("Console")) {

            JOptionPane.showMessageDialog(null, "Sólo puedes detener los procesos de tipo 'Console'.", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    private static void stoppingProcess() {

        try {
            Runtime.getRuntime().exec("taskkill /F /PID ".concat(winProcSelected.getPID()));

            JOptionPane.showMessageDialog(null, "Proceso detenido exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);


        } catch (IOException ex) {

            System.out.println("Problema ejecutando el comando");
        }
    }
}
