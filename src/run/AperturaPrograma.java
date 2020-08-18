package run;

import java.io.IOException;
import java.net.ServerSocket;
import javax.swing.JOptionPane;

public class AperturaPrograma {

    private static ServerSocket SERVER_SOCKET;

    public static void main(String[] args) {

        try {
            SERVER_SOCKET = new ServerSocket(9800);
            Index index = new Index();
            index.setVisible(true);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Esta aplicación ya se está ejecutando en este equipo.");
            System.exit(0);
        }
    }
}
