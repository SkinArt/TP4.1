import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Server {
    public static void main(String[] args) {
        try {
            // Create a DatagramSocket to listen on the specified port
            DatagramSocket serverSocket = new DatagramSocket(1236);
            // Print a message indicating that the server is waiting for connections
            System.out.println("Le serveur est en attente de connexions...");
            // Infinite loop to keep the server running and listening for packets
            while (true) {
                // Buffer to store the incoming data
                byte[] receiveHeure = new byte[1024];
                // DatagramPacket for receiving data
                DatagramPacket receivePacketHeure = new DatagramPacket(receiveHeure, receiveHeure.length);
                // Receive a packet from a client
                serverSocket.receive(receivePacketHeure);
                // Convert the received data into a String
                String clientMessageHeure = new String(receivePacketHeure.getData(), 0, receivePacketHeure.getLength());
                // Print the received message to the console
                System.out.println(clientMessageHeure);
                // Create a response message with the current server time
                String heureResponse ="Heure:  "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                // Convert the response message to bytes
                byte[] sendHeure= heureResponse.getBytes();
                // Get the address and port of the client that sent the packet
                InetAddress clientAddress = receivePacketHeure.getAddress();
                int clientPort = receivePacketHeure.getPort();
                // Create a DatagramPacket to send the response to the client
                DatagramPacket sendPacket1 = new DatagramPacket(sendHeure, sendHeure.length, clientAddress, clientPort);
                // Send the response packet to the client
                serverSocket.send(sendPacket1);
            }
        } catch (IOException e) {
            // Print any IOException that may occur
            e.printStackTrace();
        }
    }
}
