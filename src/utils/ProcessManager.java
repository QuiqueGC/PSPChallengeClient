package utils;

import data_classes.WindowsProcess;

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

    public static String stoppingProcess(String processPID) {
        String response;
        try {
            Runtime.getRuntime().exec("taskkill /F /PID ".concat(processPID));

            response = "Proceso detenido exitosamente";
            //JOptionPane.showMessageDialog(null, "Proceso detenido exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            response = "No se ha podido detener el proceso";
            //System.out.println("Problema ejecutando el comando");
        }
        return response;
    }
}
