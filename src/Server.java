import java.net.DatagramSocket;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class Server {

    public static void main(String[] args)  {
        // Try-catch block to handle potential IOExceptions
        try {
            // Creates a DatagramSocket to listen on the specified port
            DatagramSocket serverSocket = new DatagramSocket(1235);
            System.out.println("Le serveur est en attente de connexions...");

            // Infinite loop to keep server running
            while (true) {
                // Buffer for receiving incoming data
                byte[] receiveData = new byte[1024];

                // DatagramPacket for receiving data
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Receives a packet from the client
                serverSocket.receive(receivePacket);

                // Extracts data from the packet
                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

                // Retrieves client's address and port from the packet
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Creates a welcome message for the client
                String welcomeMessage = "Bienvenu " + clientMessage;

                // Converts the welcome message into byte array
                byte[] sendData = welcomeMessage.getBytes();

                // Prepares packet to send response to the client
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);

                // Sends the packet to the client
                serverSocket.send(sendPacket);
            }
        } catch (IOException e) {
            // Prints the stack trace of the exception
            e.printStackTrace();
        }
    }
}
