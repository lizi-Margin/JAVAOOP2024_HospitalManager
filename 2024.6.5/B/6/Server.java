import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server is listening on port 5000");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            new Thread(new ClientHandler(socket)).start();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            receiveFile(socket, "received_from_client.txt");
            sendFile(socket, "server_file.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receiveFile(Socket socket, String fileName) throws IOException {
        InputStream in = socket.getInputStream();
        FileOutputStream fileOut = new FileOutputStream(fileName);

        byte[] buffer = new byte[1024];
        int bytesRead;
        System.out.println(1);
        while ((bytesRead = in.read(buffer)) != -1) {
            if (buffer[bytesRead] == 0) break;
            fileOut.write(buffer, 0, bytesRead);
        }

        fileOut.close();
        System.out.println("File received and saved as " + fileName);
    }

    private void sendFile(Socket socket, String fileName) throws IOException {
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
        System.out.println("File sent to client");
    }
}

