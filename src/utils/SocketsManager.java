package utils;

import data_classes.User;

import javax.swing.*;
import java.net.Socket;

public abstract class SocketsManager {

    public static String ipServer;
    public static final int PORT = 5000;
    public static boolean isOpen = true;
    public static Socket socket;
    public static User userTryingLogin;


    public static void closeServer(Socket socket) {
        try {
            socket.close();
            System.out.println("Conexión cerrada con servidor");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

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

}
