package utils;


import data_classes.WindowsProcess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/**
 * Almacena todos los métodos referentes a la lectura y/o escritura de ficheros
 */
public class FilesRW {


    public static void takingProcessesFromFile(ArrayList<WindowsProcess> processesList) {
        String fileURL = "src/resources/files/processes.txt";
        String line;
        String[] data;

        processesList.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(fileURL))) {

            while ((line = br.readLine()) != null) {

                data = line.split(";");

                processesList.add(new WindowsProcess(data[0], data[1], data[2], data[4].concat(data[5])));
            }

        } catch (FileNotFoundException e) {

            System.out.println("NO SE HA ENCONTRADO EL FICHERO");

        } catch (IOException e) {

            System.out.println("ERROR DURANTE LA LECTURA DEL FICHERO");

        } catch (ArrayIndexOutOfBoundsException e) {

            System.out.println("INFORMACIÓN MAL GUARDADA EN EL FICHERO");
        }
    }




    public static void writtingProcessesInFile(Path path, ArrayList<String> processesList) {
        for (String string : processesList) {

            try {
                Files.writeString(path, string + System.lineSeparator(), StandardOpenOption.APPEND);

            } catch (IOException ee) {

                System.out.println("ERROR EN LA ESCRITURA DEL FICHERO");
            }
        }
    }

    /**
     * Crea un nuevo fichero en la ruta establecida
     *
     * @param path la ruta en la que crear
     */
    public static void creatingFile(Path path) {
        try {
            Files.createFile(path);

        } catch (IOException ex) {

            System.out.println(ex);
        }
    }

    public static void deleteFile(Path path) {
        try {

            Files.delete(path);

        } catch (IOException ex) {

            throw new RuntimeException(ex);
        }
    }
}
