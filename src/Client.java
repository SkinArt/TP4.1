import java.net.DatagramSocket;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // Creates a DatagramSocket for sending data
            DatagramSocket clientSocket = new DatagramSocket();

            // Gets the InetAddress object for the server (using localhost here)
            InetAddress serverAddress = InetAddress.getByName("localhost");

            // Server port number to which the client will connect
            int serverPort = 1235;

            // Scanner object for reading input from the console
            Scanner scanner = new Scanner(System.in);
            System.out.println("Donnez votre prénom :\n");

            // Reads a line of text (user's name) from the console
            String message = scanner.nextLine();

            // Converts the message into bytes for sending
            byte[] sendData = message.getBytes();

            // Creates a packet with the data, server address, and port
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);

            // Sends the packet to the server
            clientSocket.send(sendPacket);

            // Buffer for receiving incoming data
            byte[] receiveData = new byte[1024];

            // DatagramPacket for receiving data
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Receives a packet from the server
            clientSocket.receive(receivePacket);

            // Converts the received data into a string
            String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());

            // Prints the server's response to the console
            System.out.println(serverResponse);

        } catch (SocketException e) {
            // Handles SocketException
            e.printStackTrace();
        } catch (UnknownHostException e) {
            // Handles UnknownHostException
            e.printStackTrace();
        } catch (IOException e) {
            // Handles IOException
            e.printStackTrace();
        }
    }
}
