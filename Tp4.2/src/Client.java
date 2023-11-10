import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
public class Client {
    public static void main(String[] args) {
        try {
            // Create a DatagramSocket for sending and receiving data
            DatagramSocket clientSocket = new DatagramSocket();
            // Resolve the server address (localhost in this case)
            InetAddress serverAddress = InetAddress.getByName("localhost");
            // Define the server port to send data to
            int serverPort = 1236;
            // Prepare the message to be sent to the server
            String heureMessage = "quelle heure est-il";
            // Convert the message to bytes
            byte[] sendheureMessage = heureMessage.getBytes();
            // Create a DatagramPacket to send the message to the server
            DatagramPacket sendPacketHeure = new DatagramPacket(sendheureMessage, sendheureMessage.length, serverAddress, serverPort);
            // Send the packet to the server
            clientSocket.send(sendPacketHeure);
            // Buffer to store the response from the server
            byte[] receiveHeure = new byte[1024];
            // DatagramPacket to receive the server's response
            DatagramPacket receivePacket1 = new DatagramPacket(receiveHeure, receiveHeure.length);
            // Receive the response from the server
            clientSocket.receive(receivePacket1);
            // Convert the server's response to a String
            String serverResponse1 = new String(receivePacket1.getData(), 0, receivePacket1.getLength());
            // Print the server's response
            System.out.println(serverResponse1);
            // Close the socket
            clientSocket.close();
        } catch (SocketException e) {
            // Print any SocketException that may occur
            e.printStackTrace();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
