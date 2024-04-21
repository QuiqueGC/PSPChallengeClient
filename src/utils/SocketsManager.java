package utils;

import data_classes.User;
import data_classes.WindowsProcess;
import p_s_p_challenge.PSPChallenge;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public abstract class SocketsManager {

    public static String ipServer;
    public static final int PORT = 5002;
    public static Socket socket;



    /**
     * Intenta establecer conexión con el servidor
     *
     * @return boolean que indica si se ha conectado o no
     */
    public static boolean establishConnection() {
        boolean isConnected = false;
        try {
            SocketsManager.socket = new Socket(ipServer, PORT);
            System.out.println("Conexión establecida con el servidor");
            JOptionPane.showMessageDialog(null, "Te has conectado satisfactoriamente al servidor", "Conexión establecia", JOptionPane.INFORMATION_MESSAGE);
            isConnected = true;

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "No se ha podido establecer la conexión", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return isConnected;
    }


    /**
     * Envía la petición en forma de string
     *
     * @param petition string con la petición
     */
    public static void sendPetition(String petition) {
        try {

            new ObjectOutputStream(socket.getOutputStream()).writeObject(petition);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema con el servidor. La aplicación se cerrará", "Información", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
            System.out.println("excepción IOE");
        }
    }

    /**
     * Envía un objeto de tipo User al server
     *
     * @param user User que enviará al server
     */
    public static void sendUser(User user) {
        try {

            new ObjectOutputStream(socket.getOutputStream()).writeObject(user);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema con el servidor. La aplicación se cerrará", "Información", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
            System.out.println("excepción IOE");
        }
    }

    /**
     * Recibe el usuario del server
     *
     * @return User que devuelve el server
     */
    public static User getUserFromServer() {
        User user = null;
        try {
            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            user = (User) ois.readObject();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema con el servidor. La aplicación se cerrará", "Información", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
            System.out.println(e);
        }
        return user;
    }

    /**
     * Recibe una respuesta del server para poder mostrarla en un diálogo
     *
     * @return String con la respuesta del server
     */
    public static String getString() {
        String response = "";
        try {
            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            response = (String) ois.readObject();

        } catch (Exception e) {

            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema con el servidor. La aplicación se cerrará", "Información", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }

        return response;
    }

    /**
     * Envía una respuesta al cliente
     *
     * @param response String con la respuesta
     */
    public static void sendString(String response) {
        try {
            new ObjectOutputStream(socket.getOutputStream()).writeObject(response);
        } catch (IOException ex) {
            System.out.println("excepción IOE");
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema con el servidor. La aplicación se cerrará", "Información", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }


    /**
     * Envía al server la lista de programas
     *
     * @param programs ArrayList de String que se enviará
     */
    public static void sendPrograms(ArrayList<String> programs) {
        try {

            new ObjectOutputStream(socket.getOutputStream()).writeObject(programs);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema con el servidor. La aplicación se cerrará", "Información", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
            System.out.println("excepción IOE");
        }
    }

    /**
     * Envía la lista de procesos al server
     *
     * @param processes ArrayList con los procesos a enviar
     */
    public static void sendProcesses(ArrayList<WindowsProcess> processes) {
        try {

            new ObjectOutputStream(socket.getOutputStream()).writeObject(processes);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema con el servidor. La aplicación se cerrará", "Información", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
            System.out.println("excepción IOE");
        }
    }

    public static void sendLoggedInState() {
        System.out.println("VALOR DEL BOOLEAN -> " + PSPChallenge.isLoggedIn);
        try {

            new DataOutputStream(socket.getOutputStream()).writeBoolean(PSPChallenge.isLoggedIn);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un problema con el servidor. La aplicación se cerrará", "Información", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
            System.out.println("excepción IOE");
            System.out.println("FALLO ENVIANDO BOOLEAN");
        }
    }


    public static void closeServer() {
        try {
            socket.close();
            System.out.println("Conexión cerrada con servidor");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
