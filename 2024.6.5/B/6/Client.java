import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws IOException {
        String serverAddress = "localhost";
        int serverPort = 5000;
        Socket socket = new Socket(serverAddress, serverPort);
        System.out.println("Connected to server");

        sendFile(socket, "client_file.txt");
        receiveFile(socket, "received_from_server.txt");

        socket.close();
    }

    private static void sendFile(Socket socket, String fileName) throws IOException {
        File file = new File(fileName);
        FileInputStream fileIn = new FileInputStream(file);
        OutputStream out = socket.getOutputStream();

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fileIn.read(buffer)) != -1) {
            
            out.write(buffer, 0, bytesRead);
        }
        out.write(0);

        fileIn.close();
        out.flush();
        System.out.println("File sent to server");
    }

    private static void receiveFile(Socket socket, String fileName) throws IOException {
        InputStream in = socket.getInputStream();
        FileOutputStream fileOut = new FileOutputStream(fileName);

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            if (buffer[bytesRead] == 0) break;
            fileOut.write(buffer, 0, bytesRead);
        }

        fileOut.close();
        System.out.println("File received and saved as " + fileName);
    }
}
