package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public abstract class ProgramsManager {

    private static ArrayList<String> installedPrograms;

    public void extractingListOfPrograms() {
        try {

            Process process = Runtime.getRuntime().exec("wmic product get name");

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String resultOfExecution;
            while ((resultOfExecution = br.readLine()) != null) {
                installedPrograms.add(resultOfExecution);
            }

        } catch (IOException e) {

            System.out.println(e);
        }
    }
}
