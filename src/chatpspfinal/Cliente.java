package chatpspfinal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.SocketFactory;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author Miguel
 */
public class Cliente implements Runnable {

    //Declaramos las variables necesarias para la conexion y comunicacion

    SSLSocket clienteSocket;
    SSLSocketFactory SocketFactory;
    InetSocketAddress addr;
    DataInputStream in;
    DataOutputStream out;
    int puerto = 5556;
    String host = "localhost";
    String mensajes = "";
    JEditorPane panel;

    //Constructor recibe como parametro el panel donde se mostraran los mensajes
    public Cliente(JEditorPane panel) {
        
        
        
         System.setProperty("javax.net.ssl.keyStore", "src/chatpspfinal/ClientKey.jks");
            System.setProperty("javax.net.ssl.keyStorePassword", "clientpass");
            System.setProperty("javax.net.ssl.trustStore", "src/chatpspfinal/clientTrustedCerts.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "clientpass");
        
        
        
        this.panel = panel;
        try {
            System.out.println("Obteniendo   sockets cliente");
            SocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            System.out.println("Creando el socket cliente");
            clienteSocket = (SSLSocket) SocketFactory.createSocket();

            System.out.println("Estableciendo la conexion");
            addr = new InetSocketAddress("localhost", 5556);
            clienteSocket.connect(addr);
 

            
            in = new DataInputStream(clienteSocket.getInputStream());
            out = new DataOutputStream(clienteSocket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            //Ciclo infinito que escucha mensajes del servidor y los muestra en el panel
            while (true) {
                mensajes += in.readUTF();
                panel.setText(mensajes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Funcion sirve para enviar mensajes al servidor
    public void enviarMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
